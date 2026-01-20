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
import java.util.List;
import java.util.Random;

@WebServlet("/quote")
public class QuoteServlet extends HttpServlet {

    private final TemplateEngine templateEngine =
            ThymeleafConfig.createTemplateEngine();

    private final List<String> quotes = List.of(
            "Programming is like writing a book... except if you miss a single comma the whole thing makes no sense.",
            "It works on my machine.",
            "There are only two hard things in Computer Science: cache invalidation and naming things.",
            "First, solve the problem. Then, write the code.",
            "Experience is the name everyone gives to their mistakes.",
            "Java is to JavaScript what car is to carpet."
    );

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        // Välj ett slumpmässigt citat
        Random random = new Random();
        String randomQuote = quotes.get(random.nextInt(quotes.size()));

        // Skapa Thymeleaf-context
        JakartaServletWebApplication application =
                JakartaServletWebApplication.buildApplication(req.getServletContext());

        IWebExchange exchange =
                application.buildExchange(req, resp);

        WebContext context = new WebContext(exchange);

        // Skicka citatet till mallen
        context.setVariable("quote", randomQuote);

        resp.setContentType("text/html;charset=UTF-8");

        templateEngine.process("quote", context, resp.getWriter());
    }
}
