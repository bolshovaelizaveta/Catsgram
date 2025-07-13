# Catsgram

## Описание проекта

**Catsgram** — это учебный проект веб-приложения на **Java** с использованием **Spring Boot**, демонстрирующий принципы построения **RESTful API**. Сервис позволяет управлять данными о пользователях и их постах (публикациях).

Цель проекта — освоение базовых концепций бэкенд-разработки и создания API.

![Catsgram для настоящих кото-фанатов!](https://pictures.s3.yandex.net/resources/218_1663626678.png)

## Ключевые технологии

* **Java 11+**
* **Spring Boot** (используется версия 3.2.2)
* **Maven** (версия 4.0.0)
* **Lombok**

## Демонстрируемые возможности

*   Проектирование и реализация **RESTful API** для ресурсов `User`, `Post` и `Image`.
*   Создание **Spring Boot @RestController** для обработки HTTP-запросов (GET, POST, PUT).
*   Использование **@RequestBody** для десериализации JSON.
*   Обработка переменных пути (**@PathVariable**) для доступа к конкретным ресурсам.
*   Работа с параметрами запроса (**@RequestParam**) для реализации пагинации и сортировки.
*   Загрузка файлов на сервер (**MultipartFile**).
*   Отправка бинарных данных (файлов) с сервера с помощью **ResponseEntity**.
*   Обработка ошибок через **пользовательские исключения** (`ConditionsNotMetException`, `DuplicatedDataException`, `NotFoundException`, `ImageFileException`).
*   Временное хранение данных в **оперативной памяти** (`HashMap`).
*   Взаимодействие между сервисами (`ImageService` вызывает `PostService`).
*   Использование **@Value** для чтения конфигурации из `application.properties`.

## Как запустить

1.  Клонируйте репозиторий:
    ```bash
    git clone https://github.com/bolshovaelizaveta/Catsgram.git
    cd Catsgram
    ```
2.  Перед первым запуском создайте файл `src/main/resources/application.properties` и укажите в нем путь к директории для хранения изображений:
    ```properties
    # Укажите свой путь. Используйте / вместо \.
    catsgram.image-directory=C:/your/path/to/images
    ```
3.  Соберите и запустите приложение:
    ```bash
    mvn spring-boot:run
    ```
    Приложение будет доступно на `http://localhost:8080`

## Эндпоинты API

Все запросы отправлять на `http://localhost:8080`

*   **Пользователи (`/users`):**
    *   `GET /users`: Получить список всех пользователей.
    *   `GET /users/{userId}`: Получить пользователя по его ID.
    *   `POST /users`: Создать нового пользователя.
        *   _Пример тела:_ `{ "username": "TestUser", "email": "test@example.com", "password": "password" }`
    *   `PUT /users`: Обновить данные существующего пользователя.
        *   _Пример тела:_ `{ "id": 1, "username": "UpdatedUser", "email": "new.test@example.com" }`

*   **Посты (`/posts`):**
    *   `GET /posts`: Получить список постов с возможностью сортировки и пагинации.
        *   _Параметры запроса:_ `?sort=desc/asc` (сортировка), `?size=10` (кол-во), `?from=0` (смещение).
        *   _Пример:_ `/posts?sort=asc&size=5&from=10`
    *   `GET /posts/{postId}`: Получить пост по его ID.
    *   `POST /posts`: Создать новый пост.
        *   _Пример тела:_ `{ "authorId": 1, "description": "Мой первый пост!" }`

*   **Изображения:**
    *   `POST /posts/{postId}/images`: Загрузить одно или несколько изображений для поста.
        *   _Тип запроса: `multipart/form-data`. Ключ для файлов: `image`._
    *   `GET /images/{imageId}`: Скачать изображение по его ID.
        *   _Возвращает файл (бинарные данные), а не JSON._

*   **Домашняя страница (`/home`):**
    *   `GET /home`: Получить приветственное сообщение.
