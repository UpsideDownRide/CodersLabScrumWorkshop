package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Plan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;



@WebServlet(name = "PlanAdd", value = "/app/plan/add")
public class PlanAdd extends HttpServlet {
	
	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/addSchedules.jsp").forward(request,response);
		
		
	}
	
	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String planName = request.getParameter("name");
		String planDescription = request.getParameter("description");
		long millis =System.currentTimeMillis();
		Date date = new Date(millis);
		
		Plan plan = new Plan();
		
		plan.setName(planName);
		plan.setDescription(planDescription);
		plan.setCreated(date);
		plan.setAdminId(1);
		
		PlanDao planDao = new PlanDao();
		planDao.create(plan);
		
		response.sendRedirect(request.getContextPath() + "/app/plan/list");
	}
}
