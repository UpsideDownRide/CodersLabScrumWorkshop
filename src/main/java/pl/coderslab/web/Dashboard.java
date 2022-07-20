package pl.coderslab.web;

import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.DayName;
import pl.coderslab.model.Plan;
import pl.coderslab.model.RecipePlanDetails;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@WebServlet(name = "Dashboard", value = "/dashboard")
public class Dashboard extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PlanDao planDao = new PlanDao();
        RecipeDao recipeDao = new RecipeDao();
        DayNameDao dayNameDao = new DayNameDao();
        HttpSession session = request.getSession();
        Admin loggedUser = (Admin) session.getAttribute("User");
        int id = loggedUser.getId();

        request.setAttribute("admin", loggedUser.getFirstName());
        request.setAttribute("plans", planDao.planCount(id));
        request.setAttribute("recipes",recipeDao.amountOfRecipesOfUser(id));

        RecipePlanDao recipePlanDao = new RecipePlanDao();
        try {
            Plan plan = planDao.lastAddedPlan(id);
            List<RecipePlanDetails> recipePlanDetails = recipePlanDao.findRecipePlanDetails(plan.getId());
            request.setAttribute("plan", plan);
            Map<String, List<RecipePlanDetails>> recipePlanDetailsByDay = recipePlanDetails
                    .stream()
                    .collect(groupingBy(RecipePlanDetails::getDayName));
            request.setAttribute("recipePlanDetailsByDay", recipePlanDetailsByDay);
            getServletContext().getRequestDispatcher("/dashboard.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid recipe plan id");
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
