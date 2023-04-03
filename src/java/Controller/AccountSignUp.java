/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAL.Account;
import DAL.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import DAO.AccountDAO;

/**
 *
 * @author ADMIN
 */
public class AccountSignUp extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("../signup.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("txtEmail");
        String pass = req.getParameter("txtPass");
        String repass = req.getParameter("txtRePass");
        String companyname = req.getParameter("txtCompanyName");
        String contactname = req.getParameter("txtContactName");
        String contacttitle = req.getParameter("txtContactTitle");
        String address = req.getParameter("txtAddress");

        req.setAttribute("email", email);
        req.setAttribute("companyname", companyname);
        req.setAttribute("contactname", contactname);
        req.setAttribute("contacttitle", contacttitle);
        req.setAttribute("address", address);

        
        if (email.equals("")) {
            req.setAttribute("msgEmail", "");
        }
        if (pass.equals("")) {
            req.setAttribute("msgPass", "");
        }
        if (repass.equals("")) {
            req.setAttribute("msgRePass", "");
        }
        if (companyname.equals("")) {
            req.setAttribute("msgCompanyName", "");
        }
        if (contactname.equals("")) {
            req.setAttribute("msgContactName", "");
        }
        if (contacttitle.equals("")) {
            req.setAttribute("msgContactTitle", "");
        }
        if (address.equals("")) {
            req.setAttribute("msgAddress", "");
        }

        if (pass.equals(repass)) {
            if (email.equals("")) {
                req.getRequestDispatcher("../signup.jsp").forward(req, resp);
            } else {
                Account acc = new AccountDAO().CheckEmailExist(email);
                if (acc == null && !email.isEmpty() && !companyname.isEmpty() && !contactname.isEmpty() && !contacttitle.isEmpty() && !address.isEmpty()) {
                    Customer cust = new Customer();
                    cust.setCompanyName(companyname);
                    cust.setContactName(contactname);
                    cust.setContactTitle(contacttitle);
                    cust.setAddress(address);

                    acc = new Account(email, pass);
                    if (new AccountDAO().CreateAccount(cust, acc) > 0) {
                        resp.sendRedirect("../signin.jsp");
                    } else {
                        req.getRequestDispatcher("../signup.jsp").forward(req, resp);
                    }
                } else if(acc != null){
                    req.setAttribute("msgEmailExist", "Tai khoan da ton tai");
                    req.getRequestDispatcher("../signup.jsp").forward(req, resp);
                }
            }
        } else {
            req.setAttribute("msg2RePass", "Mat khau khong trung khop");
            req.getRequestDispatcher("../signup.jsp").forward(req, resp);
        }

    }
}
