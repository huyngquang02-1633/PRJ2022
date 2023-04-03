/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAL.Category;
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
public class HomeSearchControll extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("homeStatus", 3);
        
        String txtSearch = req.getParameter("txt");
        req.setAttribute("txt", txtSearch);
        
        if(txtSearch==""){
            req.setAttribute("allProduct", "ALL PRODUCTS");
        }
        
        ArrayList<Category> ListCategory = new CategoryDAO().getCategorys();
        req.setAttribute("ListC", ListCategory);

        ArrayList<Products> s = new ProductDAO().pagingProductSearch(txtSearch, 1);
        req.setAttribute("listS", s);
        req.setAttribute("tag", 1);

        ////
        int count = new ProductDAO().getTotalProductSearch(txtSearch);
        int endPage = count / 8;
        if (count % 8 != 0) {
            endPage++;
        }
        req.setAttribute("endP", endPage);
        ////
        req.getRequestDispatcher("index.jsp").forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        ArrayList<Category> ListCategory = new CategoryDAO().getCategorys();
        req.setAttribute("ListC", ListCategory);

        int index = Integer.parseInt(req.getParameter("index"));
        req.setAttribute("tag", index);
        
        String txtSearch = req.getParameter("txt");
        //chia trang
        int count = new ProductDAO().getTotalProductSearch(txtSearch);
        int endPage = count / 8;
        if (count % 8 != 0) {
            endPage++;
        }
        ArrayList<Products> list = new ProductDAO().pagingProductSearch(txtSearch, index);

        req.setAttribute("txt", txtSearch);
        req.setAttribute("listS", list);
        req.setAttribute("endP", endPage);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

}
