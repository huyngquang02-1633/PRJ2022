/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAL.OrderCustomerEmployee;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import DAO.OrderDAO;

/**
 *
 * @author ADMIN
 */
public class AdminOrderFilterByDateControll extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("pagingStatus", 4);

        String fromDate = req.getParameter("txtStartOrderDate");
        String toDate = req.getParameter("txtEndOrderDate");

        if (fromDate.isEmpty() && toDate.isEmpty()) {
            req.getRequestDispatcher("../admin/order").forward(req, resp);         
        }

        req.getSession().setAttribute("txtStartOrderDate", fromDate);
        req.getSession().setAttribute("txtEndOrderDate", toDate);

        ArrayList<OrderCustomerEmployee> list = new OrderDAO().PagingOrderInRange(fromDate, toDate, 1);
        req.setAttribute("listO", list);

        int count = new OrderDAO().getTatolOrderInRange(fromDate, toDate);
        int endPage = count / 10;
        if (count % 10 != 0) {
            endPage++;
        }
        req.setAttribute("endP", endPage);
        req.setAttribute("tag", 1);

        req.getRequestDispatcher("../order.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("AccAdminSession") == null) {
            resp.getWriter().print("Access denied");
        } else {
            String fromDate = req.getParameter("fromdate");
            String toDate = req.getParameter("todate");

            int index = Integer.parseInt(req.getParameter("index"));
            req.setAttribute("tag", index);

            //chia trang 
            int count = new OrderDAO().getTatolOrderInRange(fromDate, toDate);
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            ArrayList<OrderCustomerEmployee> list = new OrderDAO().PagingOrderInRange(fromDate, toDate, index);
            req.setAttribute("listO", list);

            req.setAttribute("endP", endPage);
            req.setAttribute("tag", index);

            req.getRequestDispatcher("../order.jsp").forward(req, resp);
        }
    }

}
