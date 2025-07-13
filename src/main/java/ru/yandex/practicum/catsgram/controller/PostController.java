package ru.yandex.practicum.catsgram.controller;

import ru.yandex.practicum.catsgram.exception.ParameterNotValidException;
import ru.yandex.practicum.catsgram.model.SortOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor // Для внедрения зависимости через конструктор
public class PostController {
    private final PostService postService;

    @GetMapping
    public List<Post> findAll(
            @RequestParam(defaultValue = "desc") String sort,
            @RequestParam(defaultValue = "0") Integer from,
            @RequestParam(defaultValue = "10") Integer size) {

        if (size <= 0) {
            throw new ParameterNotValidException("size", "Размер должен быть больше нуля.");
        }
        if (from < 0) {
            throw new ParameterNotValidException("from", "Начало выборки не может быть отрицательным.");
        }

        SortOrder sortOrder;
        try {
            sortOrder = SortOrder.from(sort);
        } catch (IllegalArgumentException e) {
            throw new ParameterNotValidException("sort", "Некорректное значение. Допустимые: asc, desc.");
        }

        return postService.findAll(size, sortOrder, from);
    }


    @GetMapping("/{postId}")
    public Post findPostById(@PathVariable Long postId) {
        return postService.findPostById(postId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@RequestBody Post post) {
        return postService.create(post);
    }

    @PutMapping
    public Post update(@RequestBody Post newPost) {
        return postService.update(newPost);
    }
}