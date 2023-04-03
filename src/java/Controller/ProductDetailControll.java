/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAL.ProductCategory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import DAO.ProductDAO;

/**
 *
 * @author ADMIN
 */
public class ProductDetailControll extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("txtId"));
        ProductCategory pc = new ProductDAO().getProductCategoryByProductID(id);
        req.setAttribute("detail", pc);

        req.getRequestDispatcher("../detail.jsp").forward(req, resp);
    }

}
