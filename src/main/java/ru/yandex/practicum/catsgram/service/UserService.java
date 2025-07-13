package ru.yandex.practicum.catsgram.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exception.ConditionsNotMetException;
import ru.yandex.practicum.catsgram.exception.DuplicatedDataException;
import ru.yandex.practicum.catsgram.exception.NotFoundException;
import ru.yandex.practicum.catsgram.model.User;

import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// Указываем, что класс UserService - является бином
@Service
public class UserService {

    // Ключ - ID пользователя, значение - User (объект пользователя)
    private final Map<Long, User> users = new HashMap<>();

    public Collection<User> findAll() {
        return users.values();
    }

    public Optional<User> findUserById(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    public User findUserByIdOrThrow(Long id) {
        Optional<User> user = findUserById(id);
        return user.orElseThrow(() -> new NotFoundException("Пользователь с id=" + id + " не найден"));
    }

    public User create(User user) {
        // Если email не указан, генерируем ConditionsNotMetException
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new ConditionsNotMetException("Имейл должен быть указан");
        }
        // Проверяем, есть ли уже пользователь с таким email
        boolean isEmailDuplicated = users.values().stream()
                .anyMatch(u -> u.getEmail().equalsIgnoreCase(user.getEmail()));
        if (isEmailDuplicated) {
            throw new DuplicatedDataException("Этот имейл уже используется");
        }

        user.setId(getNextId());
        user.setRegistrationDate(Instant.now());

        users.put(user.getId(), user);
        return user; // Возвращаем созданного пользователя с присвоенными ID и датой
    }

    public User update(User user) {
        if (user.getId() == null) {
            throw new ConditionsNotMetException("Id должен быть указан");
        }

        if (!users.containsKey(user.getId())) {
            throw new ConditionsNotMetException("Пользователь с id=" + user.getId() + " не найден");
        }

        // Получаем существующего пользователя, чтобы сравнить email и обновить поля
        User existingUser = users.get(user.getId());

        if (user.getEmail() != null && !user.getEmail().equalsIgnoreCase(existingUser.getEmail())) {
            boolean isEmailDuplicated = users.values().stream()
                    .anyMatch(u -> !u.getId().equals(user.getId()) && u.getEmail().equalsIgnoreCase(user.getEmail()));
            if (isEmailDuplicated) {
                throw new DuplicatedDataException("Этот имейл уже используется");
            }
        }

        if (user.getEmail() != null) {
            existingUser.setEmail(user.getEmail());
        }
        if (user.getUsername() != null) {
            existingUser.setUsername(user.getUsername());
        }
        if (user.getPassword() != null) {
            existingUser.setPassword(user.getPassword());
        }

        users.put(existingUser.getId(), existingUser);
        return existingUser; // Возвращаем обновленного пользователя
    }

    private long getNextId() {
        long currentMaxId = users.keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        return ++currentMaxId;
    }
}