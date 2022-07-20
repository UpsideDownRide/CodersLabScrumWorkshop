package pl.coderslab.web.app.recipe.plan;

import pl.coderslab.dao.NotAvailableException;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.Plan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


@WebServlet(name = "DeleteServlet", value = "/app/recipe/plan/delete")
public class DeleteServlet extends HttpServlet {
	
	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String recipeId = request.getParameter("id");
		request.setAttribute("recipeId",recipeId);
		request.getServletContext().getRequestDispatcher("/confirmDeleteRecipeFromPlan.jsp").forward(request, response);
		
	}
	
	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RecipePlanDao recipePlanDao = new RecipePlanDao();
		String recipeId = request.getParameter("recipeId");
		try{
			int _recipeId = Integer.parseInt(recipeId);
			recipePlanDao.delete(_recipeId);
		}catch(NumberFormatException e){
			e.printStackTrace();
		}
		
		response.sendRedirect("/app/plan/details");
	}
}
