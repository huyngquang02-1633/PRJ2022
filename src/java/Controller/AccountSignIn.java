/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAL.Account;
import DAL.Category;
import DAL.Customer;
import DAL.Products;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import DAO.AccountDAO;
import DAO.CategoryDAO;
import DAO.ProductDAO;

/**
 *
 * @author ADMIN
 */
public class AccountSignIn extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getSession().getAttribute("AccSession") != null) {
            req.getSession().removeAttribute("AccSession");
            req.getSession().removeAttribute("customer");
            req.getSession().removeAttribute("account");

            req.getRequestDispatcher("/home").forward(req, resp);
        } else if (req.getSession().getAttribute("AccAdminSession") != null) {
            req.getSession().removeAttribute("AccAdminSession");

            req.getRequestDispatcher("/home").forward(req, resp);
        } else {
            req.getRequestDispatcher("../signin.jsp").forward(req, resp);
            //resp.sendRedirect("../signin.jsp");

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("txtEmail");
        req.setAttribute("txtEmail", email);
        String pass = req.getParameter("txtPass");


        if (email.equals("")) {
            req.setAttribute("msgEmailNull", "Email is required");
        }
        if (pass.equals("")) {
            req.setAttribute("msgPassNull", "Password is required");
        }
        
        if (email.equals("") || pass.equals("")) {
            req.getRequestDispatcher("../signin.jsp").forward(req, resp);
        } else {
            Account acc = new AccountDAO().getAccount(email, pass);

            if (acc != null && acc.getRole() == 2) {
                // Cap session cho account
                req.getSession().setAttribute("AccSession", acc);

//                Account account = new AccountDAO().getAccountCustomersByAccountID(acc.getAccountID()).getAccount();
//                Customer customer = new AccountDAO().getAccountCustomersByAccountID(acc.getAccountID()).getCustomer();
//                req.getSession().setAttribute("account", account);
//                req.getSession().setAttribute("customer", customer);

                req.getRequestDispatcher("../home").forward(req, resp);

            } else if (acc != null && acc.getRole() == 1) {
                req.getSession().setAttribute("AccAdminSession", acc);
                resp.sendRedirect("../admin/dashboard");
            } else{
                req.setAttribute("msgexist", "Incorrect password or account");
                req.getRequestDispatcher("../signin.jsp").forward(req, resp);
            }
        }
    }

}
