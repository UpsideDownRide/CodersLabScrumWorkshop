package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Recipe;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RecipeDetails", value = "/app/recipe/details")
public class RecipeDetails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        Admin loggedUser = (Admin) servletContext.getAttribute("User");
        int id = loggedUser.getId();
        RecipeDao recipeDao = new RecipeDao();
        Recipe recipe = recipeDao.readByAdminId(id);
        request.setAttribute("name", recipe.getIngredients());
        request.setAttribute("description",recipe.getDescription());
        request.setAttribute("preparationTime",recipe.getPreparationTime());
        request.setAttribute("preparation",recipe.getPreparation());
        request.setAttribute("ingredients",recipe.getIngredients());
        getServletContext().getRequestDispatcher("/app-recipe-details.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
