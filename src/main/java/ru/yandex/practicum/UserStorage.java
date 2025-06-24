package ru.yandex.practicum;

import ru.yandex.practicum.storage.MemoryStorage;
import ru.yandex.practicum.storage.Storage;
import ru.yandex.practicum.user.Details;
import ru.yandex.practicum.user.User;

import java.util.Scanner;

public class UserStorage {
    private final Scanner scanner = new Scanner(System.in);
    private final Storage storage = new MemoryStorage();

    public static void main(String[] args) {
        new UserStorage().loop();
    }

    public void loop() {
        while (true) {
            final String action = getAction();
            if ("1".equals(action)) {
                addUser();
            }
            if ("2".equals(action)) {
                searchUser();
            }
            if ("3".equals(action)) {
                break;
            }
        }
    }

    private void addUser() {
        System.out.println("Введите электронную почту пользователя => ");
        final String email = scanner.nextLine();

        System.out.println("Введите имя пользователя => ");
        final String firstName = scanner.nextLine();

        System.out.println("Введите фамилию пользователя => ");
        final String lastName = scanner.nextLine();

        final Details details = Details.builder()
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .build();

        final User user = User.builder().details(details).build();
        storage.put(user);
        System.out.println("Пользователь добавлен");
    }

    private void searchUser() {
        System.out.println("Введите электронную почту => ");
        final String email = scanner.nextLine();
        final User user = storage.get(email);
        if (user == null) {
            System.out.println("Пользователь не найден");
            return;
        }
        System.out.println("Найден пользователь: " + user);
    }

    private String getAction() {
        System.out.println("Выберите действие:");
        System.out.println("1 - добавление пользователя");
        System.out.println("2 - поиск пользователя по электронной почте");
        System.out.println("3 - выход");
        return scanner.nextLine();
    }
}