package controller;

import domain.Person;
import domain.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

/**
 * Сервлет раздела «Сотрудники».
 * Лабораторные работы № 2 и № 3.
 *
 * @author Демидко М. Д., группа ПИZ-331
 */
@WebServlet("/HelloPersonsServlet")
public class PersonsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /** Демо-набор сотрудников для проверки доменных классов Role и Person (ЛР_3). */
    private static final List<Person> DEMO_PERSONS;

    static {
        Role director = new Role(1L, "Директор");
        Role engineer = new Role(2L, "Главный инженер");
        Role developer = new Role(3L, "Программист");
        Role accountant = new Role(4L, "Бухгалтер");

        DEMO_PERSONS = Arrays.asList(
                new Person(1L, "Иван", "Петров", "+7 (900) 123-45-67", "ivan.petrov@example.com",
                        director.getId(), director),
                new Person(2L, "Мария", "Сидорова", "+7 (900) 234-56-78", "maria.s@example.com",
                        engineer.getId(), engineer),
                new Person(3L, "Алексей", "Иванов", "+7 (900) 345-67-89", "a.ivanov@example.com",
                        developer.getId(), developer),
                new Person(4L, "Елена", "Кузнецова", "+7 (900) 456-78-90", "e.kuznetsova@example.com",
                        developer.getId(), developer),
                new Person(5L, "Дмитрий", "Смирнов", "+7 (900) 567-89-01", "d.smirnov@example.com",
                        accountant.getId(), accountant)
        );
    }

    public PersonsServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String ctx = request.getContextPath();

        try (PrintWriter w = response.getWriter()) {
            w.println("<!DOCTYPE html>");
            w.println("<html lang=\"ru\"><head>");
            w.println("<meta charset=\"UTF-8\">");
            w.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
            w.println("<link rel=\"stylesheet\" href=\"" + ctx + "/css/bootstrap.min.css\">");
            w.println("<link rel=\"stylesheet\" href=\"" + ctx + "/css/bootstrap-icons.min.css\">");
            w.println("<link rel=\"stylesheet\" href=\"" + ctx + "/css/style.css\">");
            w.println("<title>Сотрудники · Управление персоналом</title>");
            w.println("</head><body>");

            w.println("<header class=\"app-header\"><div class=\"container\">");
            w.println("<a href=\"" + ctx + "/\" class=\"navbar-brand\">");
            w.println("<div class=\"logo\"><img src=\"" + ctx + "/images/persons.png\" alt=\"Логотип\"></div>");
            w.println("<div><h1>Управление персоналом</h1>");
            w.println("<p class=\"subtitle\">Java EE · Веб-приложение</p></div></a>");
            w.println("</div></header>");

            w.println("<main class=\"container\">");
            w.println("<section class=\"hero\">");
            w.println("<h2><i class=\"bi bi-people-fill text-primary\"></i> Сотрудники</h2>");
            w.println("<p>Демонстрация работы классов <code>domain.Person</code> и <code>domain.Role</code> (ЛР_3).</p>");
            w.println("</section>");

            w.println("<div class=\"table-responsive shadow-sm rounded-3 bg-white p-3\">");
            w.println("<table class=\"table table-hover align-middle mb-0\">");
            w.println("<thead><tr><th>#</th><th>ID</th><th>ФИО</th><th>Должность</th><th>Телефон</th><th>Email</th></tr></thead>");
            w.println("<tbody>");
            int n = 1;
            for (Person person : DEMO_PERSONS) {
                w.println("<tr>");
                w.println("<td>" + (n++) + "</td>");
                w.println("<td>" + person.getId() + "</td>");
                w.println("<td>" + person.getFullName() + "</td>");
                w.println("<td><span class=\"badge bg-secondary\">" + person.getRole() + "</span></td>");
                w.println("<td>" + person.getPhone() + "</td>");
                w.println("<td><a href=\"mailto:" + person.getEmail() + "\">" + person.getEmail() + "</a></td>");
                w.println("</tr>");
            }
            w.println("</tbody></table></div>");

            w.println("<div class=\"text-center mt-4\">");
            w.println("<a href=\"" + ctx + "/\" class=\"btn btn-outline-primary\">");
            w.println("<i class=\"bi bi-arrow-left\"></i> На главную</a></div>");
            w.println("</main>");

            w.println("<footer class=\"app-footer\"><div class=\"container\">");
            w.println("<div>© 2026 · Демидко М. Д. · группа ПИZ-331 · РГЭУ (РИНХ)</div>");
            w.println("</div></footer>");

            w.println("</body></html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
