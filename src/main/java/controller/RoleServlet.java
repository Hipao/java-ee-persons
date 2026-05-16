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
 * GET без параметров — список + форма добавления.
 * GET ?action=edit&id=X — режим редактирования (форма заполнена).
 * GET ?action=delete&id=X — удаление + redirect.
 * POST — INSERT (если id пустой) или UPDATE (если id есть).
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
        String action = request.getParameter("action");
        String idStr = request.getParameter("id");

        if ("delete".equals(action) && idStr != null) {
            try {
                dao.delete(Long.valueOf(idStr));
            } catch (DAOException | NumberFormatException e) {
                getServletContext().log("RoleServlet.delete failed", e);
                request.setAttribute("error", "Не удалось удалить должность: " + e.getMessage());
            }
            response.sendRedirect(request.getContextPath() + "/role");
            return;
        }

        if ("edit".equals(action) && idStr != null) {
            try {
                Role editRole = dao.findById(Long.valueOf(idStr));
                request.setAttribute("editRole", editRole);
            } catch (DAOException | NumberFormatException e) {
                getServletContext().log("RoleServlet.findById failed", e);
                request.setAttribute("error", "Не удалось загрузить должность: " + e.getMessage());
            }
        }

        renderList(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String idStr = request.getParameter("id");
        String namerole = request.getParameter("namerole");

        if (namerole == null || namerole.isBlank()) {
            request.setAttribute("error", "Введите наименование должности");
            renderList(request, response);
            return;
        }

        try {
            if (idStr != null && !idStr.isBlank()) {
                // UPDATE
                Role role = new Role(Long.valueOf(idStr), namerole.trim());
                dao.update(role);
            } else {
                // INSERT
                dao.insert(new Role(namerole.trim()));
            }
        } catch (DAOException | NumberFormatException e) {
            getServletContext().log("RoleServlet.save failed", e);
            request.setAttribute("error", "Не удалось сохранить должность: " + e.getMessage());
            renderList(request, response);
            return;
        }
        response.sendRedirect(request.getContextPath() + "/role");
    }

    private void renderList(HttpServletRequest request, HttpServletResponse response)
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
}
