package ru.yandex.practicum.catsgram.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.yandex.practicum.catsgram.dto.ImageDto;
import ru.yandex.practicum.catsgram.dto.ImageUploadResponse;
import ru.yandex.practicum.catsgram.model.Image;

import java.nio.file.Path;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ImageMapper {

    // Создает объект Image из данных, полученных при загрузке
    public static Image mapToImage(long postId, Path filePath, String originalFileName) {
        Image image = new Image();
        image.setOriginalFileName(originalFileName);
        image.setFilePath(filePath.toString());
        image.setPostId(postId);
        return image;
    }

    // Преобразует Image в ImageDto для отдачи клиенту вместе с содержимым файла
    public static ImageDto mapToImageDto(Image image, byte[] data) {
        ImageDto dto = new ImageDto();
        dto.setId(image.getId());
        dto.setPostId(image.getPostId());
        dto.setFileName(image.getOriginalFileName());
        dto.setData(data);
        return dto;
    }

    // Преобразует Image в ImageUploadResponse для ответа на запрос загрузки
    public static ImageUploadResponse mapToImageUploadResponse(Image image) {
        ImageUploadResponse dto = new ImageUploadResponse();
        dto.setId(image.getId());
        dto.setPostId(image.getPostId());
        dto.setFileName(image.getOriginalFileName());
        dto.setFilePath(image.getFilePath());
        return dto;
    }
}
