package ru.yandex.practicum.catsgram.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.dal.UserRepository;
import ru.yandex.practicum.catsgram.dto.NewUserRequest;
import ru.yandex.practicum.catsgram.dto.UpdateUserRequest;
import ru.yandex.practicum.catsgram.dto.UserDto;
import ru.yandex.practicum.catsgram.exception.ConditionsNotMetException;
import ru.yandex.practicum.catsgram.exception.DuplicatedDataException;
import ru.yandex.practicum.catsgram.exception.NotFoundException;
import ru.yandex.practicum.catsgram.mapper.UserMapper;
import ru.yandex.practicum.catsgram.model.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDto createUser(NewUserRequest request) {
        if (request.getEmail() == null || request.getEmail().isBlank()) {
            throw new ConditionsNotMetException("Имейл должен быть указан");
        }

        Optional<User> alreadyExistUser = userRepository.findByEmail(request.getEmail());
        if (alreadyExistUser.isPresent()) {
            throw new DuplicatedDataException("Данный имейл уже используется");
        }

        User user = UserMapper.mapToUser(request);
        user = userRepository.save(user);
        return UserMapper.mapToUserDto(user);
    }

    public UserDto updateUser(long userId, UpdateUserRequest request) {
        User userToUpdate = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));

        User updatedUser = UserMapper.updateUserFields(userToUpdate, request);
        userRepository.update(updatedUser);
        return UserMapper.mapToUserDto(updatedUser);
    }

    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(long userId) {
        return userRepository.findById(userId)
                .map(UserMapper::mapToUserDto)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден с ID: " + userId));
    }

    public Optional<User> findUserById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return userRepository.findById(id);
    }
}