/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAL.Account;
import DAL.AccountCustomer;
import DAL.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import DAO.AccountDAO;

/**
 *
 * @author ADMIN
 */
public class AccountProfile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("AccSession") != null) {
            Account a = (Account) req.getSession().getAttribute("AccSession");
            Account account = new AccountDAO().getAccountCustomersByAccountID(a.getAccountID()).getAccount();
            Customer customer = new AccountDAO().getAccountCustomersByAccountID(a.getAccountID()).getCustomer();
            req.setAttribute("customer", customer);
            req.setAttribute("account", account);

            req.getRequestDispatcher("../profile.jsp").forward(req, resp);
        } else {

            resp.getWriter().print("Access denied");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String companyName = req.getParameter("txtCompanyName");
        String contactName = req.getParameter("txtContactName");
        String companyTitle = req.getParameter("txtCompanyTitle");
        String address = req.getParameter("txtAddress");
        String customerId = req.getParameter("txtCustomerID");

        if (companyName.isEmpty() || contactName.isEmpty() || companyTitle.isEmpty() || address.isEmpty()) {
            req.setAttribute("msgInfo", "Customer Infomation Not  Null");

            Account a = (Account) req.getSession().getAttribute("AccSession");
            Account account = new AccountDAO().getAccountCustomersByAccountID(a.getAccountID()).getAccount();
            Customer customer = new AccountDAO().getAccountCustomersByAccountID(a.getAccountID()).getCustomer();
            req.setAttribute("customer", customer);
            req.setAttribute("account", account);

            req.getRequestDispatcher("../profile.jsp").forward(req, resp);
        } else {

            Customer customer = new Customer();
            customer.setCustomerID(customerId);
            customer.setCompanyName(companyName);
            customer.setContactName(contactName);
            customer.setContactTitle(companyTitle);
            customer.setAddress(address);

            new AccountDAO().EditInfo(customer);
            req.setAttribute("updatemsg", "Update infomation successfull");

            req.getRequestDispatcher("../home").forward(req, resp);

        }
    }

}
