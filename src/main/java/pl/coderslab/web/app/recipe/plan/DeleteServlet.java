package pl.coderslab.web.app.recipe.plan;

import pl.coderslab.dao.PlanDao;
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
		if(true) {
			Integer id = Integer.parseInt(request.getParameter("id"));
			RecipePlanDao recipePlanDao = new RecipePlanDao();
			recipePlanDao.delete(id);
			request.getServletContext().getRequestDispatcher("/app/plan/details").forward(request, response);
		}
		response.sendRedirect(request.getContextPath() + "/app/plan/list");
	}
	
	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
}
