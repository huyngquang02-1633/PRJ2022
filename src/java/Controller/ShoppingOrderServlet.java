/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAL.Account;
import DAL.Cart;
import DAL.Customer;
import DAL.Item;
import DAL.Products;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import DAO.AccountDAO;
import DAO.CustomerDAO;
import DAO.OrderDAO;
import DAO.ProductDAO;

/**
 *
 * @author ADMIN
 */
public class ShoppingOrderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String companyName = req.getParameter("txtCompanyName");
        String contactName = req.getParameter("txtContactName");
        String companyTitle = req.getParameter("txtCompanyTitle");
        String address = req.getParameter("txtAddress");
        String customerId = req.getParameter("txtCustomerID");

        if (companyName.isEmpty() || contactName.isEmpty() || companyTitle.isEmpty() || address.isEmpty()) {
            req.setAttribute("msgInfo", "Customer Infomation Not  Null");
            
            req.getRequestDispatcher("../cart.jsp").forward(req, resp);
        } else {

            Customer customer = new Customer(customerId, companyName, contactName, companyTitle, address);
            Customer customerCreate = new Customer(new AccountDAO().randomString(5), companyName, contactName, companyTitle, address);

            Cart cart = (Cart) req.getSession().getAttribute("cart");

            if (cart.getItems().size() > 0) {
                if (req.getParameter("txtCustomerID").isEmpty()) {
                    new AccountDAO().CreateCustomer(customerCreate);
                    new OrderDAO().addOrder(customerCreate, cart);
                } else {
                    new AccountDAO().EditInfo(customer);
                    new OrderDAO().addOrder(customer, cart);
                }
                req.setAttribute("ordermsg", "Order Success");

            } else {
                req.setAttribute("nullmsg", "Order is null!!!");
                req.getRequestDispatcher("../cart.jsp").forward(req, resp);
            }

            req.getSession().removeAttribute("cart");
            req.getSession().setAttribute("total", 0);
            req.getSession().setAttribute("size", 0);

            req.getRequestDispatcher("../home").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("AccSession") == null) {
            resp.sendRedirect("../cart.jsp");
        } else {
            Account a = (Account) req.getSession().getAttribute("AccSession");

            Account account = new AccountDAO().getAccountCustomersByAccountID(a.getAccountID()).getAccount();
            Customer customer = new AccountDAO().getAccountCustomersByAccountID(a.getAccountID()).getCustomer();

            req.setAttribute("account", account);
            req.setAttribute("customer", customer);

            req.getRequestDispatcher("../cart.jsp").forward(req, resp);
        }

    }
}
