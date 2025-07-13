package ru.yandex.practicum.catsgram.service;

import org.springframework.beans.factory.annotation.Value;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.yandex.practicum.catsgram.exception.ConditionsNotMetException;
import ru.yandex.practicum.catsgram.exception.ImageFileException;
import ru.yandex.practicum.catsgram.exception.NotFoundException;
import ru.yandex.practicum.catsgram.model.Image;
import ru.yandex.practicum.catsgram.model.ImageData;
import ru.yandex.practicum.catsgram.model.Post;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageService {

    // Внедряем зависимость. Spring сам подставит сюда PostService.
    private final PostService postService;
    private final Map<Long, Image> images = new HashMap<>();

    @Value("${catsgram.image-directory}")
    private String imageDirectory;

    public List<Image> saveImages(long postId, List<MultipartFile> files) {
        // Для каждого файла из списка вызываем наш приватный метод-помощник saveImage
        return files.stream()
                .map(file -> saveImage(postId, file))
                .collect(Collectors.toList());
    }

    private Image saveImage(long postId, MultipartFile file) {
        // Сначала проверяем, существует ли пост
        Post post = postService.findById(postId)
                .orElseThrow(() -> new ConditionsNotMetException("Указанный пост не найден: " + postId));

        // Сохраняем файл на диск
        Path filePath = saveFile(file, post);

        // Создаём запись о файле в Map'е
        long imageId = getNextId();

        Image image = new Image();
        image.setId(imageId);
        image.setPostId(postId);
        image.setOriginalFileName(file.getOriginalFilename());
        image.setFilePath(filePath.toString());

        images.put(imageId, image);
        return image;
    }

    // Метод для генерации ID - нужен для Image
    private long getNextId() {
        long currentMaxId = images.keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        return ++currentMaxId;
    }

    private Path saveFile(MultipartFile file, Post post) {
        try {
            String uniqueFileName = String.format("%d.%s", Instant.now().toEpochMilli(),
                    StringUtils.getFilenameExtension(file.getOriginalFilename()));

            Path uploadPath = Paths.get(imageDirectory, String.valueOf(post.getAuthorId()), String.valueOf(post.getId()));
            Path filePath = uploadPath.resolve(uniqueFileName);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            file.transferTo(filePath);
            return filePath;
        } catch (IOException e) {
            throw new RuntimeException("Не удалось сохранить файл", e);
        }
    }

    // Точка входа для контроллера
    public ImageData getImageData(long imageId) {
        // Проверяем, есть ли вообще информация о таком изображении в нашей Map
        if (!images.containsKey(imageId)) {
            throw new NotFoundException("Изображение с id = " + imageId + " не найдено");
        }
        Image image = images.get(imageId);

        // Читаем файл с диска
        byte[] data = loadFile(image);

        // Упаковываем байты и имя файла в контейнер и возвращаем
        return new ImageData(data, image.getOriginalFileName());
    }

    // Чтение файла
    private byte[] loadFile(Image image) {
        // Получаем путь к файлу из объекта Image
        Path path = Paths.get(image.getFilePath());

        // Проверяем, существует ли файл по этому пути
        if (Files.exists(path)) {
            try {
                // Если да - читаем все байты файла и возвращаем их
                return Files.readAllBytes(path);
            } catch (IOException e) {
                // Если во время чтения произошла ошибка
                throw new ImageFileException("Ошибка чтения файла. Id: " + image.getId(), e);
            }
        } else {
            // Если файла по этому пути просто нет
            throw new ImageFileException("Файл не найден. Id: " + image.getId());
        }
    }
}