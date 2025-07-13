package ru.yandex.practicum.catsgram.controller;

import ru.yandex.practicum.catsgram.model.SortOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
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

            @RequestParam(name = "sort", defaultValue = "desc") String sort,

            @RequestParam(name = "from", defaultValue = "0") Integer from,

            // По умолчанию хотим 10 самых свежих постов
            @RequestParam(name = "size", defaultValue = "10") Integer size) {

        // Преобразуем строку в enum
        SortOrder sortOrder = SortOrder.from(sort);

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