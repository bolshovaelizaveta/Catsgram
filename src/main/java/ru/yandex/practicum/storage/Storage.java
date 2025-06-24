package ru.yandex.practicum.storage;

import ru.yandex.practicum.user.User;

// Интерфейс для сохранения и получения данных о пользователе
public interface Storage {

    void put(User user);

    User get(String email);
}