package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Dashboard", value = "/dashboard")
public class Dashboard extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PlanDao planDao = new PlanDao();
        RecipeDao recipeDao = new RecipeDao();
        HttpSession session = request.getSession();
        Admin loggedUser = (Admin) session.getAttribute("User");
        int id = loggedUser.getId();

        request.setAttribute("admin", loggedUser.getFirstName());
        request.setAttribute("plans", planDao.planCount(id));
        request.setAttribute("recipes",recipeDao.amountOfRecipesOfUser(id));
        // dodac szczegoly planu jak beda zrobione
        getServletContext().getRequestDispatcher("/dashboard.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
