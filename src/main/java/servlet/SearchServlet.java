package servlet;

import manager.MovieManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/search")
public class SearchServlet extends HttpServlet {
    private MovieManager movieManager=new MovieManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchValue=req.getParameter("search");
        req.setAttribute("search",movieManager.getAllMovieBySearch(searchValue));
        req.getRequestDispatcher("/search.jsp").forward(req,resp);

    }


}
