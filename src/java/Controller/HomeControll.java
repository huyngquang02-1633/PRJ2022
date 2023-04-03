/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAL.Account;
import DAL.Category;
import DAL.Customer;
import DAL.Products;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import DAO.AccountDAO;
import DAO.CategoryDAO;
import DAO.ProductDAO;

/**
 *
 * @author ADMIN
 */
public class HomeControll extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //xoa trang thai search
        if (req.getSession().getAttribute("homeStatus") != null) {
            req.getSession().removeAttribute("homeStatus");
        }

        ArrayList<Category> ListCategory = new CategoryDAO().getCategorys();
        req.setAttribute("ListC", ListCategory);

        ArrayList<Products> ListHot = new ProductDAO().getHotProduct();
        req.setAttribute("listH", ListHot);

        ArrayList<Products> ListNew = new ProductDAO().getNewProduct();
        req.setAttribute("listN", ListNew);

        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    //signin
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Category> ListCategory = new CategoryDAO().getCategorys();
        req.setAttribute("ListC", ListCategory);

        ArrayList<Products> ListHot = new ProductDAO().getHotProduct();
        req.setAttribute("listH", ListHot);

        ArrayList<Products> ListNew = new ProductDAO().getNewProduct();
        req.setAttribute("listN", ListNew);

        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

}
