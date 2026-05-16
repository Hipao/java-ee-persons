package controller;

import domain.Person;
import domain.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Контроллер раздела «Сотрудники» — MVC-схема.
 * Подготавливает данные и форвардит на представление /views/person.jsp.
 *
 * @author Демидко М. Д., группа ПИZ-331
 */
@WebServlet("/person")
public class PersonsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /** Демо-набор сотрудников (до подключения БД в ЛР_5). */
    private static final List<Person> DEMO_PERSONS;

    static {
        List<Role> roles = RoleServlet.getDemoRoles();
        DEMO_PERSONS = new ArrayList<>(Arrays.asList(
                new Person(1L, "Иван", "Петров", "+7 (900) 123-45-67", "ivan.petrov@example.com",
                        roles.get(0).getId(), roles.get(0)),
                new Person(2L, "Мария", "Сидорова", "+7 (900) 234-56-78", "maria.s@example.com",
                        roles.get(1).getId(), roles.get(1)),
                new Person(3L, "Алексей", "Иванов", "+7 (900) 345-67-89", "a.ivanov@example.com",
                        roles.get(2).getId(), roles.get(2)),
                new Person(4L, "Елена", "Кузнецова", "+7 (900) 456-78-90", "e.kuznetsova@example.com",
                        roles.get(2).getId(), roles.get(2)),
                new Person(5L, "Дмитрий", "Смирнов", "+7 (900) 567-89-01", "d.smirnov@example.com",
                        roles.get(3).getId(), roles.get(3))
        ));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("persons", DEMO_PERSONS);
        request.setAttribute("roles", RoleServlet.getDemoRoles());
        request.getRequestDispatcher("/views/person.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Обработка POST формы — будет реализована в ЛР_6
        doGet(request, response);
    }
}
