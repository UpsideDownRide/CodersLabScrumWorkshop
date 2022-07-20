package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EditUser", value = "/app/user/edit")
public class EditUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/app-edit-user-data.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        ServletContext servletContext = getServletContext();
        Admin loggedUser = (Admin) servletContext.getAttribute("User");
        int userId = loggedUser != null ? loggedUser.getId() : 0;
        String password = loggedUser.getPassword();

        AdminDao adminDao = new AdminDao();
        Admin admin = new Admin();
        admin.setId(userId);
        admin.setFirstName(firstName);
        admin.setLastName(lastName);
        admin.setPassword(password);
        admin.setEmail(email);
        admin.setSuperadmin(0);
        admin.setEnable(0);
        adminDao.update(admin);

        response.sendRedirect("/dashboard.jsp");

    }
}
