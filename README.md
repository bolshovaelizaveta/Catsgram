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

* Проектирование и реализация **RESTful API** для ресурсов `User` и `Post`.
* Создание **Spring Boot @RestController** для обработки HTTP-запросов (GET, POST, PUT).
* Использование **@RequestBody** для десериализации JSON.
* Обработка ошибок через **пользовательские исключения** (`ConditionsNotMetException`, `DuplicatedDataException`, `NotFoundException`).
* Временное хранение данных в **оперативной памяти** (`HashMap`).
* Базовая валидация входящих данных.

## Как запустить

1.  Клонируйте репозиторий:
    ```bash
    git clone [https://github.com/bolshovaelizaveta/Catsgram.git](https://github.com/bolshovaelizaveta/Catsgram.git)
    cd Catsgram
    ```
2.  Соберите и запустите приложение:
    ```bash
    mvn spring-boot:run
    ```
    Приложение будет доступно на `http://localhost:8080`

## Эндпоинты API

Все запросы отправлять на `http://localhost:8080`

* **Пользователи (`/users`):**
    * `GET /users`: Получить список всех пользователей.
    * `POST /users`: Создать нового пользователя.
        * _Пример тела:_ `{ "username": "TestUser", "email": "test@example.com", "password": "password" }`
    * `PUT /users`: Обновить данные существующего пользователя.
        * _Пример тела:_ `{ "id": 1, "username": "UpdatedUser", "email": "new.test@example.com" }`

* **Посты (`/posts`):**
    * `GET /posts`: Получить список всех постов.
    * `POST /posts`: Создать новый пост.
        * _Пример тела:_ `{ "authorId": 1, "description": "Мой первый пост!" }`

* **Домашняя страница (`/home`):**
    * `GET /home`: Получить приветственное сообщение.
