package ru.yandex.practicum.catsgram.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.model.User;
import ru.yandex.practicum.catsgram.service.UserService;

import java.util.Collection;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor // Для внедрения зависимости через конструктор
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public User findUserById(@PathVariable Long userId) {
        return userService.findUserByIdOrThrow(userId);
    }

    @GetMapping
    public Collection<User> findAll() {
        return userService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @PutMapping
    public User update(@RequestBody User user) {
        return userService.update(user);
    }
}