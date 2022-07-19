package pl.coderslab.web.app.recipe.plan;

import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.DayName;
import pl.coderslab.model.Plan;
import pl.coderslab.model.Recipe;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddServlet", value = "/app/recipe/plan/add")
public class AddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = request.getServletContext();
        Admin user = (Admin) context.getAttribute("User");
        int userId = user != null ? user.getId() : 0;

        PlanDao planDao = new PlanDao();
        List<Plan> plans = planDao.findAllByUser(userId);

        RecipeDao recipeDao = new RecipeDao();
        List<Recipe> recipes = recipeDao.findAllByUser(userId);

        DayNameDao dayNameDao = new DayNameDao();
        List<DayName> days = dayNameDao.findAll();

        request.setAttribute("plans", plans);
        request.setAttribute("recipes", recipes);
        request.setAttribute("days", days);

        request.getServletContext().getRequestDispatcher("/addRecipeToPlan.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String planId = request.getParameter("choosePlan");
        String mealName = request.getParameter("name");
        String displayOrder = request.getParameter("number");
        String recipeId = request.getParameter("recipe");
        String dayId = request.getParameter("day");
        //RecipePlanDao recipePlanDao = new RecipePlanDao();
    }
}
