# Перенос проекта на новый ПК / ноут

## 1. Что нужно

| Программа | Зачем |
|---|---|
| **JDK 21** (Adoptium Temurin) | для компиляции и запуска Java |
| **Apache Tomcat 10.0.27** | веб-сервер для Java EE приложения |
| **PostgreSQL 16** | база данных |
| **Eclipse IDE for Enterprise Java** | если препод попросит показать в IDE (необязательно для запуска) |
| **Git** | клонирование репозитория с GitHub |

Все ставится **одним скриптом** — `setup-on-new-pc.bat`.

## 2. Шаги на новом ПК

### Вариант A — автоматический (рекомендую)

1. Перенеси на новый ПК флешкой только три файла:
   - `setup-on-new-pc.bat`
   - `clone-and-run.bat`
   - `start.bat`, `stop.bat`, `deploy.bat`
   - `README-on-new-pc.md` (этот файл)

2. Открой PowerShell **от имени администратора**, перейди в папку с этими файлами и выполни:
   ```
   .\setup-on-new-pc.bat
   ```
   Скрипт через `winget` поставит JDK 21, Git, GitHub CLI, PostgreSQL 16,
   скачает и распакует Tomcat 10 и Eclipse в `D:\dev\`.
   Займёт 15-20 минут (Eclipse — ~500MB).

3. После завершения выполни:
   ```
   .\clone-and-run.bat
   ```
   Скрипт клонирует репозиторий https://github.com/Hipao/java-ee-persons
   в `D:\work\java-ee-labs\persons\` и развернёт базу `persons_jee`.

4. Запусти приложение:
   ```
   .\start.bat
   ```
   Откроется браузер на http://localhost:8080/persons/

### Вариант B — вручную

Если автоматический скрипт не сработал:

1. **JDK 21:** https://adoptium.net/temurin/releases/?version=21
2. **PostgreSQL 16:** https://www.postgresql.org/download/windows/
   - При установке задай пароль `postgres` для суперпользователя
   - Порт 5432
3. **Apache Tomcat 10.0.27:** https://archive.apache.org/dist/tomcat/tomcat-10/v10.0.27/bin/apache-tomcat-10.0.27-windows-x64.zip
   - Распакуй в `D:\dev\apache-tomcat-10.0.27\`
4. **Eclipse JEE 2024-12:** https://www.eclipse.org/downloads/packages/release/2024-12/r
   - Выбери "Eclipse IDE for Enterprise Java and Web Developers"
   - Распакуй в `D:\dev\eclipse\`
5. **Git:** https://git-scm.com/download/win

6. Клонировать репо:
   ```
   git clone https://github.com/Hipao/java-ee-persons.git D:\work\java-ee-labs\persons
   ```

7. Развернуть БД:
   ```
   "C:\Program Files\PostgreSQL\16\bin\psql.exe" -U postgres -h localhost -c "CREATE DATABASE persons_jee;"
   "C:\Program Files\PostgreSQL\16\bin\psql.exe" -U postgres -h localhost -d persons_jee -f D:\work\java-ee-labs\persons\src\main\java\config\init_db.sql
   ```
   (введёшь пароль `postgres`)

8. Запустить Tomcat:
   ```
   D:\work\java-ee-labs\deploy.bat   # один раз чтобы скомпилировать
   D:\work\java-ee-labs\start.bat
   ```

## 3. Проверка что работает

После запуска должны открываться:
- http://localhost:8080/persons/ — главная страница
- http://localhost:8080/persons/role — список должностей из БД
- http://localhost:8080/persons/person — список сотрудников из БД

Если страницы открываются и данные из БД видны — всё работает.

## 4. Защита перед преподавателем

1. На ноуте перед защитой запусти **start.bat** (или ярлык «Java EE — Старт»)
2. В браузере покажи преподу:
   - Главную страницу
   - Раздел Должности (создание, редактирование, удаление)
   - Раздел Сотрудники (то же + связь с должностью)
3. Если попросит показать в IDE — открой `D:\dev\eclipse\eclipse.exe`,
   workspace `D:\work\java-ee-labs`, File → Import → Existing Projects → выбрать `persons`

## 5. Что в проекте

- `src/main/java/controller/` — сервлеты (RoleServlet, PersonsServlet)
- `src/main/java/domain/` — POJO модели (Role, Person)
- `src/main/java/dao/` — слой доступа к данным (DAO pattern)
- `src/main/java/exception/` — DAOException
- `src/main/java/config/config.properties` — настройки БД
- `src/main/java/config/init_db.sql` — скрипт для повторного развёртывания БД
- `src/main/webapp/` — JSP, CSS, JS, изображения
- `src/main/webapp/WEB-INF/lib/` — библиотеки (Jakarta JSTL, PostgreSQL JDBC)

Git ветки `dev1..dev7` соответствуют отдельным лабам.
