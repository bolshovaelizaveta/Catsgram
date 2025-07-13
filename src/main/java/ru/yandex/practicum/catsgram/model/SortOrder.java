package ru.yandex.practicum.catsgram.model;

public enum SortOrder {
    ASCENDING, DESCENDING;
    // Превращает строку из запроса ("asc", "desc") в безопасный тип (ASCENDING, DESCENDING)
    public static SortOrder from(String order) {
        if (order == null) {
            return null;
        }
        switch (order.toLowerCase()) {
            case "ascending":
            case "asc":
                return ASCENDING;
            case "descending":
            case "desc":
                return DESCENDING;
            default:
                // Если передали что-то непонятное
                throw new IllegalArgumentException("Неизвестный тип сортировки: " + order);
        }
    }
}