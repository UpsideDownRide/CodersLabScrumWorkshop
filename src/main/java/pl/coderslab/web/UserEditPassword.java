package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserEditPassword", value = "/app/user/edit/password")
public class UserEditPassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/app-edit-password.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newPassword = request.getParameter("newPassword");


        ServletContext servletContext = getServletContext();
        Admin loggedUser = (Admin) servletContext.getAttribute("User");
        int userId = loggedUser != null ? loggedUser.getId() : 0;


        AdminDao adminDao = new AdminDao();
        Admin admin = new Admin();
        admin.setId(userId);
        admin.setFirstName(loggedUser.getFirstName());
        admin.setLastName(loggedUser.getLastName());
        admin.setPassword(newPassword);
        admin.setEmail(loggedUser.getEmail());
        admin.setSuperadmin(0);
        admin.setEnable(0);
        adminDao.update(admin);

        response.sendRedirect("/dashboard.jsp");
    }
}
