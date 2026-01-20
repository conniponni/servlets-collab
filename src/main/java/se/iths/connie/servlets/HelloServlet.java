package se.iths.connie.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    private final TemplateEngine templateEngine =
            ThymeleafConfig.createTemplateEngine();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String name = req.getParameter("name");

        if (name == null) {
            name = "gäst";
        }

        // --- NYTT SÄTT ATT SKAPA CONTEXT ---
        JakartaServletWebApplication application =
                JakartaServletWebApplication.buildApplication(req.getServletContext());

        IWebExchange exchange =
                application.buildExchange(req, resp);

        WebContext context = new WebContext(exchange);

        context.setVariable("name", name);

        resp.setContentType("text/html;charset=UTF-8");

        templateEngine.process("hello", context, resp.getWriter());
    }
}