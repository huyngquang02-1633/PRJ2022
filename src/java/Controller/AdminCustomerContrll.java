/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAL.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import DAO.CustomerDAO;
import DAO.ProductDAO;

/**
 *
 * @author ADMIN
 */
public class AdminCustomerContrll extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("AccAdminSession") == null) {
            resp.getWriter().print("Access denied");
        } else {
            req.getSession().setAttribute("customerStatus", 1);
            //chia trang
            int count = new CustomerDAO().getTotalCustomer();
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            req.setAttribute("endP", endPage);

            if (req.getParameter("index") == null) {
                ArrayList<Customer> list = new CustomerDAO().PagingAllCustomers(1);
                req.setAttribute("listC", list);

                req.setAttribute("tag", 1);
            } else {
                int index = Integer.parseInt(req.getParameter("index"));
                ArrayList<Customer> list = new CustomerDAO().PagingAllCustomers(index);
                req.setAttribute("listC", list);
                req.setAttribute("tag", index);
            }

            req.getRequestDispatcher("../customer.jsp").forward(req, resp);
        }
    }

}
