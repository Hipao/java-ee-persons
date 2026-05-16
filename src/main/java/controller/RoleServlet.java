package controller;

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
 * Контроллер раздела «Должности» — MVC-схема.
 * Подготавливает данные и форвардит на представление /views/role.jsp.
 *
 * @author Демидко М. Д., группа ПИZ-331
 */
@WebServlet("/role")
public class RoleServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /** Демо-набор должностей (до подключения БД в ЛР_5). */
    private static final List<Role> DEMO_ROLES = new ArrayList<>(Arrays.asList(
            new Role(1L, "Директор"),
            new Role(2L, "Главный инженер"),
            new Role(3L, "Программист"),
            new Role(4L, "Бухгалтер"),
            new Role(5L, "Менеджер")
    ));

    public static List<Role> getDemoRoles() {
        return DEMO_ROLES;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("roles", DEMO_ROLES);
        request.getRequestDispatcher("/views/role.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Обработка POST формы — будет реализована в ЛР_6
        doGet(request, response);
    }
}
