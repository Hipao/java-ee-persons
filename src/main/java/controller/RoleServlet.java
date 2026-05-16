package controller;

import dao.RoleDbDAO;
import domain.Role;
import exception.DAOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Контроллер раздела «Должности» (MVC).
 * Берёт данные из БД через {@link RoleDbDAO} и форвардит на /views/role.jsp.
 *
 * @author Демидко М. Д., группа ПИZ-331
 */
@WebServlet("/role")
public class RoleServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final RoleDbDAO dao = new RoleDbDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Role> roles;
        try {
            roles = dao.findAll();
        } catch (DAOException e) {
            getServletContext().log("RoleServlet.findAll failed", e);
            roles = Collections.emptyList();
            request.setAttribute("error", "Не удалось получить список должностей: " + e.getMessage());
        }
        request.setAttribute("roles", roles);
        request.getRequestDispatcher("/views/role.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Обработка POST формы реализована в ЛР_6
        doGet(request, response);
    }
}
