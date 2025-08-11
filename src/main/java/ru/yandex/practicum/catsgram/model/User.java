package ru.yandex.practicum.catsgram.model;

import lombok.*;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"email"})
public class User {
    private Long id;
    private String username;
    @NonNull
    private String email;
    private String password;
    private Instant registrationDate;
    private List<Post> posts;
}