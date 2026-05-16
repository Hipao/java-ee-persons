# Persons — Управление персоналом

Демонстрационное веб-приложение на Java EE для лабораторных работ по дисциплине «Java EE».

**Автор:** Демидко М. Д., группа ПИZ-331

## Стек

- Java 21 (Adoptium Temurin)
- Jakarta EE 9 (Servlet 5.0)
- Apache Tomcat 10.0.27
- Bootstrap 5.3.3, jQuery 3.6.4
- PostgreSQL (с ЛР_5)

## Структура проекта

```
src/main/webapp/
├── css/bootstrap.min.css
├── js/
│   ├── jquery-3.6.4.js
│   └── bootstrap.bundle.min.js
├── images/persons.png
├── views/
│   ├── header.jsp
│   └── footer.jsp
└── index.jsp
```

## Git-flow

- `master` — стабильная версия после защиты ЛР
- `dev1`, `dev2`, ... — ветки для каждой ЛР, после защиты мерж в `master`

## Запуск

1. Импорт проекта в Eclipse (Import → Existing Projects)
2. Run on Server → Apache Tomcat v10.0
3. Открыть http://localhost:8081/persons/
