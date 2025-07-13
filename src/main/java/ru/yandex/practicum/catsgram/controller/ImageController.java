package ru.yandex.practicum.catsgram.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.yandex.practicum.catsgram.model.Image;
import ru.yandex.practicum.catsgram.model.ImageData;
import ru.yandex.practicum.catsgram.service.ImageService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    // Этот эндпоинт будет принимать POST-запросы на URL вида /posts/123/images
    @PostMapping("/posts/{postId}/images")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Image> addPostImages(@PathVariable long postId,
                                     // @RequestParam("image") говорит Spring:
                                     // "Ищи в теле запроса часть с именем 'image' - это и будут файлы"
                                     @RequestParam("image") List<MultipartFile> files) {
        // Просто передаем всю работу в сервис
        return imageService.saveImages(postId, files);
    }

    @GetMapping(value = "/images/{imageId}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE) // application/octet-stream - файл для скачивания
    public ResponseEntity<byte[]> downloadImage(@PathVariable long imageId) {
        // Получаем от сервиса контейнер с байтами и именем файла
        ImageData imageData = imageService.getImageData(imageId);

        // HTTP-заголовки
        HttpHeaders headers = new HttpHeaders();
        // Заголовок Content-Disposition
        // Он говорит браузеру: "Это не просто текст, это файл для скачивания (attachment)
        headers.setContentDisposition(
                ContentDisposition.attachment()
                        .filename(imageData.getName())
                        .build()
        );

        // Создаем и возвращаем ResponseEntity (Мы передаём ему: байты, заголовки, HTTP-статус)
        return new ResponseEntity<>(imageData.getData(), headers, HttpStatus.OK);
    }
}