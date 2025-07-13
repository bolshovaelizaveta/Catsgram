package ru.yandex.practicum.catsgram.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data // Генерирует геттеры, сеттеры, toString, equals, hashCode
@AllArgsConstructor // Генерирует конструктор со всеми полями
public class ImageData {
    private final byte[] data; // Здесь будет лежать сама картинка в виде байтов
    private final String name; // А здесь - ее оригинальное имя, например, "my_cat.jpg"
}