/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAL.Account;
import DAL.Customer;
import DAL.OrderDetail;
import DAL.Orders;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import DAO.AccountDAO;
import DAO.OrderDAO;

/**
 *
 * @author ADMIN
 */
public class AccountProfile1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("AccSession") != null) {
            Account a = (Account) req.getSession().getAttribute("AccSession");
            Customer customer = new AccountDAO().getAccountCustomersByAccountID(a.getAccountID()).getCustomer();
            req.setAttribute("customer", customer);

            ArrayList<Orders> order = new OrderDAO().getOrderByCustomerId(customer.getCustomerID());
            req.setAttribute("order", order);

            req.setAttribute("OrderDAO", new OrderDAO());
            req.getRequestDispatcher("../profile1.jsp").forward(req, resp);
        } else {
            resp.getWriter().print("Access denied");
        }
    }

}
