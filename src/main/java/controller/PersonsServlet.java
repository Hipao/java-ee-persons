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
 * Берёт данные из БД через {@link PersonDbDAO} и форвардит на /views/person.jsp.
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
        // Обработка POST формы реализована в ЛР_6
        doGet(request, response);
    }
}
