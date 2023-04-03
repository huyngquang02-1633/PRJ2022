/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAL.Category;
import DAL.ProductCategory;
import DAL.Products;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import DAO.CategoryDAO;
import DAO.ProductDAO;

/**
 *
 * @author ADMIN
 */
public class AdminProductControll extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("AccAdminSession") == null) {
            resp.getWriter().print("Access denied");
        } else {
            req.getSession().setAttribute("pagingStatus", 1);

            ArrayList<Category> ListCategory = new CategoryDAO().getCategorys();
            req.setAttribute("ListC", ListCategory);

            //chia trang
            int count = new ProductDAO().getTotalProduct();
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            req.setAttribute("endP", endPage);

            if (req.getParameter("index") == null) {
                ArrayList<ProductCategory> list = new ProductDAO().pagingAllProductCategory(1);
                req.setAttribute("listP", list);
                req.setAttribute("tag", 1);
            } else {
                int index = Integer.parseInt(req.getParameter("index"));
                ArrayList<ProductCategory> list = new ProductDAO().pagingAllProductCategory(index);
                req.setAttribute("listP", list);
                req.setAttribute("tag", index);
            }

            req.getRequestDispatcher("../product.jsp").forward(req, resp);
        }
    }

    //req
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("pagingStatus", 1);

        ArrayList<Category> ListCategory = new CategoryDAO().getCategorys();
        req.setAttribute("ListC", ListCategory);

        //chia trang
        int count = new ProductDAO().getTotalProduct();
        int endPage = count / 10;
        if (count % 10 != 0) {
            endPage++;
        }
        req.setAttribute("endP", endPage);

        if (req.getParameter("index") == null) {
            ArrayList<ProductCategory> list = new ProductDAO().pagingAllProductCategory(1);
            req.setAttribute("listP", list);
            req.setAttribute("tag", 1);
        } else {
            int index = Integer.parseInt(req.getParameter("index"));
            ArrayList<ProductCategory> list = new ProductDAO().pagingAllProductCategory(index);
            req.setAttribute("listP", list);
            req.setAttribute("tag", index);
        }

        req.getRequestDispatcher("../product.jsp").forward(req, resp);
    }

}
