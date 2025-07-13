package ru.yandex.practicum.catsgram.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exception.ConditionsNotMetException;
import ru.yandex.practicum.catsgram.exception.NotFoundException;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.SortOrder;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

// Указываем, что класс PostService - является бином и его нужно добавить в контекст приложения
@Service
@RequiredArgsConstructor // Для внедрения зависимости через конструктор
public class PostService {
    private final Map<Long, Post> posts = new HashMap<>();
    private final UserService userService;

    public List<Post> findAll(Integer size, SortOrder sort, Integer from) {
        Comparator<Post> comparator = Comparator.comparing(Post::getPostDate);
        if (sort == SortOrder.DESCENDING) {
            comparator = comparator.reversed();
        } // Метод sorted() принимает Comparator (сравнение)

        return posts.values().stream()
                .sorted(comparator)
                .skip(from) // Пропускаем первые 'from' элементов
                .limit(size) // Ограничиваем количество элементов до 'size'
                .collect(Collectors.toList()); // Собираем обратно в список
    }

    public Post findPostById(Long postId) {
        return Optional.ofNullable(posts.get(postId))
                .orElseThrow(() -> new NotFoundException("Пост с id=" + postId + " не найден"));
    }

    // Метод возвращает Optional. Для взаимодействия между сервисами
    public Optional<Post> findById(long postId) {
        return Optional.ofNullable(posts.get(postId));
    }

    public Post create(Post post) {
        Long authorId = post.getAuthorId();
        // Метод findUserById вернёт Optional<User>
        if (userService.findUserById(authorId).isEmpty()) {
            throw new ConditionsNotMetException("Автор с id = " + authorId + " не найден");
        }

        if (post.getDescription() == null || post.getDescription().isBlank()) {
            throw new ConditionsNotMetException("Описание не может быть пустым");
        }

        post.setId(getNextId());
        post.setPostDate(Instant.now());
        posts.put(post.getId(), post);
        return post;
    }

    public Post update(Post newPost) {
        if (newPost.getId() == null) {
            throw new ConditionsNotMetException("Id должен быть указан");
        }
        if (posts.containsKey(newPost.getId())) {
            Post oldPost = posts.get(newPost.getId());
            if (newPost.getDescription() == null || newPost.getDescription().isBlank()) {
                throw new ConditionsNotMetException("Описание не может быть пустым");
            }
            oldPost.setDescription(newPost.getDescription());
            return oldPost;
        }
        throw new NotFoundException("Пост с id = " + newPost.getId() + " не найден");
    }

    private long getNextId() {
        long currentMaxId = posts.keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        return ++currentMaxId;
    }
}