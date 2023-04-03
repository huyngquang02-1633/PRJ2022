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

/**
 *
 * @author ADMIN
 */
public class AdminCustomerSearch extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("customerStatus", 2);
        String cid = req.getParameter("txtSearch");
        req.setAttribute("productSearch", cid);
        //chia trang
        int count = new CustomerDAO().getTotalSearchCustomer(cid);
        int endPage = count / 10;
        if (count % 10 != 0) {
            endPage++;
        }
        req.setAttribute("endP", endPage);

        if (req.getParameter("index") == null) {
            ArrayList<Customer> list = new CustomerDAO().PagingSearchCustomers(cid, 1);
            req.setAttribute("listC", list);

            req.setAttribute("tag", 1);
        }

        req.getRequestDispatcher("../customer.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("AccAdminSession") == null) {
            resp.getWriter().print("Access denied");
        } else {
            String cid = req.getParameter("cid");
            req.setAttribute("productSearch", cid);
            int index = Integer.parseInt(req.getParameter("index"));

            //chia trang
            int count = new CustomerDAO().getTotalSearchCustomer(cid);
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            req.setAttribute("endP", endPage);

            ArrayList<Customer> list = new CustomerDAO().PagingSearchCustomers(cid, index);
            req.setAttribute("listC", list);

            req.setAttribute("tag", index);

            req.getRequestDispatcher("../customer.jsp").forward(req, resp);
        }
    }

}
