package servlet;
import manager.MovieManager;
import model.Movie;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/singlePost")
public class SinglePostServlet extends HttpServlet {

    MovieManager movieManager = new MovieManager();


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null || "".equals(id)) {
            resp.sendRedirect("/home");
            return;
        }
        int postId = Integer.parseInt(id);
        Movie movieById = movieManager.getMovieById(postId);
        req.setAttribute("movie",movieById);
        req.getRequestDispatcher("/WEB-INF/single_post.jsp").forward(req,resp);

    }
}
