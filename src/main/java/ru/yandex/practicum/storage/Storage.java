package ru.yandex.practicum.storage;

import ru.yandex.practicum.user.User;

public interface Storage {

    void put(User user);

    User get(String email);
}