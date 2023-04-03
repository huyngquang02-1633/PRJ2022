/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import DAO.OrderDAO;

/**
 *
 * @author ADMIN
 */
public class AdminCancelOrderControll extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("AccAdminSession") == null) {
            resp.getWriter().print("Access denied");
        } else {
            int id = Integer.parseInt(req.getParameter("oid"));
            new OrderDAO().cancelOrder(id);

            if (req.getSession().getAttribute("AccAdminSession") != null) {
                req.getRequestDispatcher("../admin/order").forward(req, resp);
            } else if (req.getSession().getAttribute("AccSession") != null) {
                req.getRequestDispatcher("../account/profile1").forward(req, resp);
            }
        }
    }

}
