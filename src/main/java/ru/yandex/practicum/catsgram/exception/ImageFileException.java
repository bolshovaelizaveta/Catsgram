package ru.yandex.practicum.catsgram.exception;

// Наследуемся от RuntimeException, чтобы не пришлось везде писать throws
public class ImageFileException extends RuntimeException {
    public ImageFileException(String message) {
        super(message);
    }

    public ImageFileException(String message, Throwable cause) {
        super(message, cause);
    }
}