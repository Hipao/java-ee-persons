package controller;

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
 * Сервлет раздела «Должности».
 * Лабораторные работы № 2 и № 3.
 *
 * @author Демидко М. Д., группа ПИZ-331
 */
@WebServlet("/HelloRoleServlet")
public class RoleServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /** Демо-набор должностей для проверки доменного класса Role (ЛР_3). */
    private static final List<Role> DEMO_ROLES = Arrays.asList(
            new Role(1L, "Директор"),
            new Role(2L, "Главный инженер"),
            new Role(3L, "Программист"),
            new Role(4L, "Бухгалтер"),
            new Role(5L, "Менеджер")
    );

    public RoleServlet() {
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
            w.println("<title>Должности · Управление персоналом</title>");
            w.println("</head><body>");

            w.println("<header class=\"app-header\"><div class=\"container\">");
            w.println("<a href=\"" + ctx + "/\" class=\"navbar-brand\">");
            w.println("<div class=\"logo\"><img src=\"" + ctx + "/images/persons.png\" alt=\"Логотип\"></div>");
            w.println("<div><h1>Управление персоналом</h1>");
            w.println("<p class=\"subtitle\">Java EE · Веб-приложение</p></div></a>");
            w.println("</div></header>");

            w.println("<main class=\"container\">");
            w.println("<section class=\"hero\">");
            w.println("<h2><i class=\"bi bi-briefcase-fill text-primary\"></i> Должности</h2>");
            w.println("<p>Демонстрация работы класса <code>domain.Role</code> (ЛР_3).</p>");
            w.println("</section>");

            w.println("<div class=\"table-responsive shadow-sm rounded-3 bg-white p-3\">");
            w.println("<table class=\"table table-hover align-middle mb-0\">");
            w.println("<thead><tr><th>#</th><th>ID</th><th>Наименование должности</th></tr></thead>");
            w.println("<tbody>");
            int n = 1;
            for (Role role : DEMO_ROLES) {
                w.println("<tr><td>" + (n++) + "</td><td>" + role.getId() + "</td><td>"
                        + role.getNamerole() + "</td></tr>");
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
