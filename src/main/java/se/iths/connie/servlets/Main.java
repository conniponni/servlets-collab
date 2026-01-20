package se.iths.connie.servlets;

public class Main {
    public static void main(String[] args) throws Exception {
//starta webbservern
        var server = new org.eclipse.jetty.server.Server(8080);
//i denna context kan man registrera servlets
        var context = new org.eclipse.jetty.servlet.ServletContextHandler();
        server.setHandler(context);
//att lägga till servlets
        context.addServlet(RootServlet.class, "/");

        context.addServlet(WordsServlet.class, "/words");

        context.addServlet(HelloServlet.class, "/hello/*");

//starta servern
        server.start();
        server.join();
    }
}


