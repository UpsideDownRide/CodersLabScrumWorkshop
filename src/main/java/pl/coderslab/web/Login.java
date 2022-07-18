package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Login", value = "/login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminDao adminDao = new AdminDao();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Admin userToAuthorize = adminDao.findByEmail((email));
        boolean validPassword = adminDao.validPassword(userToAuthorize, password);
        if (validPassword) {
            request.setAttribute("User", userToAuthorize);
            request.setAttribute("Authorized", true);
            request.getServletContext().getRequestDispatcher("/").forward(request, response);
        } else {
            request.getServletContext().getRequestDispatcher("/login").forward(request, response);
        }
    }
}
