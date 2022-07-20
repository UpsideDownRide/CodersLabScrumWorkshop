package pl.coderslab.web.app.recipe.plan;

import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.DayName;
import pl.coderslab.model.Plan;
import pl.coderslab.model.Recipe;
import pl.coderslab.model.RecipePlan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "AppPlanDetails", value = "/app/plan/details")
public class DetailsServlet extends HttpServlet {
	
	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		PlanDao planDao = new PlanDao();
		Plan plan = planDao.read(Integer.parseInt(id));
		request.setAttribute("plan", plan);
		
		RecipePlanDao recipePlanDao = new RecipePlanDao();
		List<RecipePlan> recipePlans = recipePlanDao.findRecipePlan(Integer.parseInt(id));
		request.setAttribute("recipePlans", recipePlans);
	
		RecipeDao recipeDao = new RecipeDao();
		List<Recipe> recipes = recipeDao.findAll();
		request.setAttribute("recipes", recipes);
		
		DayNameDao dayNameDao = new DayNameDao();
		List<DayName> dayNames = dayNameDao.findDaysByPlanId(Integer.parseInt(id));
		request.setAttribute("days", dayNames);
		
		getServletContext().getRequestDispatcher("/app-details-schedules.jsp").forward(request,response);
	}
	
	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
}
