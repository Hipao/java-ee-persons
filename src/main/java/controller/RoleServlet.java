package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Сервлет-каркас для раздела «Должности».
 * Лабораторная работа № 2 — Создание шаблонов сервлетов.
 *
 * @author Демидко М. Д., группа ПИZ-331
 */
@WebServlet("/HelloRoleServlet")
public class RoleServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public RoleServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter writer = response.getWriter()) {
            writer.println("<!DOCTYPE html>");
            writer.println("<html lang=\"ru\">");
            writer.println("<head>");
            writer.println("    <meta charset=\"UTF-8\">");
            writer.println("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
            writer.println("    <link rel=\"stylesheet\" href=\"" + request.getContextPath() + "/css/bootstrap.min.css\">");
            writer.println("    <link rel=\"stylesheet\" href=\"" + request.getContextPath() + "/css/bootstrap-icons.min.css\">");
            writer.println("    <link rel=\"stylesheet\" href=\"" + request.getContextPath() + "/css/style.css\">");
            writer.println("    <title>Должности · Управление персоналом</title>");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("    <header class=\"app-header\">");
            writer.println("        <div class=\"container\">");
            writer.println("            <a href=\"" + request.getContextPath() + "/\" class=\"navbar-brand\">");
            writer.println("                <div class=\"logo\"><img src=\"" + request.getContextPath() + "/images/persons.png\" alt=\"Логотип\"></div>");
            writer.println("                <div><h1>Управление персоналом</h1>");
            writer.println("                <p class=\"subtitle\">Java EE · Веб-приложение</p></div>");
            writer.println("            </a>");
            writer.println("        </div>");
            writer.println("    </header>");
            writer.println("    <main class=\"container\">");
            writer.println("        <section class=\"hero\">");
            writer.println("            <h2><i class=\"bi bi-briefcase-fill text-primary\"></i> Должности</h2>");
            writer.println("            <p>Привет от сервлета <code>RoleServlet</code>. Здесь будет реализован справочник должностей.</p>");
            writer.println("        </section>");
            writer.println("        <div class=\"text-center mt-4\">");
            writer.println("            <a href=\"" + request.getContextPath() + "/\" class=\"btn btn-outline-primary\">");
            writer.println("                <i class=\"bi bi-arrow-left\"></i> На главную</a>");
            writer.println("        </div>");
            writer.println("    </main>");
            writer.println("    <footer class=\"app-footer\">");
            writer.println("        <div class=\"container\">");
            writer.println("            <div>© 2026 · Демидко М. Д. · группа ПИZ-331 · РГЭУ (РИНХ)</div>");
            writer.println("        </div>");
            writer.println("    </footer>");
            writer.println("</body>");
            writer.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
