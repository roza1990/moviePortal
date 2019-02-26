package servlet;

import manager.GenreManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin")
public class AdminServlet  extends HttpServlet {
private GenreManager genreManager=new GenreManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("genre",genreManager.getAllgenres());
        req.getRequestDispatcher("/WEB-INF/admin.jsp").forward(req,resp);
    }
}
