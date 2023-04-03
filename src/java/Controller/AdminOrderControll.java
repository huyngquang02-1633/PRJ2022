/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAL.OrderCustomerEmployee;
import DAL.Orders;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import DAO.OrderDAO;

/**
 *
 * @author ADMIN
 */
public class AdminOrderControll extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("AccAdminSession") == null) {
            resp.getWriter().print("Access denied");
        } else {
            req.getSession().removeAttribute("txtStartOrderDate");
            req.getSession().removeAttribute("txtEndOrderDate");

            req.getSession().setAttribute("pagingStatus", 3);

            //chia trang 
            int count = new OrderDAO().getTotalOrderCustomerEmployee();
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            req.setAttribute("endP", endPage);

            if (req.getParameter("index") == null) {
                ArrayList<OrderCustomerEmployee> list = new OrderDAO().pagingOrderCustomerEmployee(1);
                req.setAttribute("listO", list);
                req.setAttribute("tag", 1);

            } else {
                int index = Integer.parseInt(req.getParameter("index"));
                ArrayList<OrderCustomerEmployee> list = new OrderDAO().pagingOrderCustomerEmployee(index);
                req.setAttribute("listO", list);
                req.setAttribute("tag", index);
            }

            req.getRequestDispatcher("../order.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("txtStartOrderDate");
        req.getSession().removeAttribute("txtEndOrderDate");

        req.getSession().setAttribute("pagingStatus", 3);

        //chia trang 
        int count = new OrderDAO().getTotalOrderCustomerEmployee();
        int endPage = count / 10;
        if (count % 10 != 0) {
            endPage++;
        }
        req.setAttribute("endP", endPage);

        if (req.getParameter("index") == null) {
            ArrayList<OrderCustomerEmployee> list = new OrderDAO().pagingOrderCustomerEmployee(1);
            req.setAttribute("listO", list);
            req.setAttribute("tag", 1);

        } else {
            int index = Integer.parseInt(req.getParameter("index"));
            ArrayList<OrderCustomerEmployee> list = new OrderDAO().pagingOrderCustomerEmployee(index);
            req.setAttribute("listO", list);
            req.setAttribute("tag", index);
        }

        req.getRequestDispatcher("../order.jsp").forward(req, resp);
    }

}
