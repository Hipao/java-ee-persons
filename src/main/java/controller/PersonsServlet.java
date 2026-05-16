package controller;

import dao.PersonDbDAO;
import dao.RoleDbDAO;
import domain.Person;
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
 * Контроллер раздела «Сотрудники» (MVC).
 * GET — список сотрудников из БД.
 * POST — создание нового сотрудника (ЛР_6).
 *
 * @author Демидко М. Д., группа ПИZ-331
 */
@WebServlet("/person")
public class PersonsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final PersonDbDAO personDao = new PersonDbDAO();
    private final RoleDbDAO roleDao = new RoleDbDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Person> persons;
        List<Role> roles;
        try {
            persons = personDao.findAll();
            roles = roleDao.findAll();
        } catch (DAOException e) {
            getServletContext().log("PersonsServlet.findAll failed", e);
            persons = Collections.emptyList();
            roles = Collections.emptyList();
            request.setAttribute("error", "Не удалось получить данные: " + e.getMessage());
        }
        request.setAttribute("persons", persons);
        request.setAttribute("roles", roles);
        request.getRequestDispatcher("/views/person.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String idRoleStr = request.getParameter("idRole");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        if (firstName == null || firstName.isBlank()
                || lastName == null || lastName.isBlank()
                || idRoleStr == null || idRoleStr.isBlank()) {
            request.setAttribute("error", "Заполните обязательные поля: фамилия, имя, должность");
            doGet(request, response);
            return;
        }

        try {
            Long idRole = Long.valueOf(idRoleStr);
            Role role = roleDao.findById(idRole);
            Person person = new Person(firstName.trim(), lastName.trim(),
                    phone != null ? phone.trim() : null,
                    email != null ? email.trim() : null,
                    idRole, role);
            personDao.insert(person);
        } catch (DAOException | NumberFormatException e) {
            getServletContext().log("PersonsServlet.insert failed", e);
            request.setAttribute("error", "Не удалось добавить сотрудника: " + e.getMessage());
            doGet(request, response);
            return;
        }
        response.sendRedirect(request.getContextPath() + "/person");
    }
}
