package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

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
        HttpSession session = request.getSession();

        if (userToAuthorize == null) {
            response.sendRedirect("/login");
            return;
        }

        boolean validPassword = adminDao.validPassword(userToAuthorize, password);
        if (validPassword) {
            session.setAttribute("User", userToAuthorize);
            session.setAttribute("Authorized", true);
            response.sendRedirect("/dashboard");
        } else {
            response.sendRedirect("/login");
        }
    }
}
