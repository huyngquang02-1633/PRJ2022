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
public class AdminProductFilterControll extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ArrayList<Category> ListCategory = new CategoryDAO().getCategorys();
        req.setAttribute("ListC", ListCategory);

        int categoryToFilter = Integer.parseInt(req.getParameter("ddlCategory"));
        resp.getWriter().print(categoryToFilter);
        req.setAttribute("categoryToFilter", categoryToFilter);

        int index = Integer.parseInt(req.getParameter("index"));
        req.setAttribute("tag", index);

        String productSearch = req.getParameter("txtSearch");
        
        req.setAttribute("productSearch", productSearch);
//
        if (req.getSession().getAttribute("status") == "2") {
            ArrayList<ProductCategory> list = new CategoryDAO().PagingProductCategoryByCategory(categoryToFilter, index);
            req.setAttribute("listP", list);
            //chia trang
            int count = new CategoryDAO().getTotalProductByCategory(categoryToFilter);
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            req.setAttribute("endP", endPage);
        }
        if (req.getSession().getAttribute("status") == "3") {

            ArrayList<ProductCategory> list = new CategoryDAO().PagingProductCategoryByCategoryProductName(categoryToFilter, productSearch, index);
            req.setAttribute("listP", list);

            //chia trang
            int count = new CategoryDAO().getTotalProductByCategoryProductName(productSearch, categoryToFilter);
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            req.setAttribute("endP", endPage);
        }
        if (req.getSession().getAttribute("status") == "4") {
            ArrayList<ProductCategory> list = new ProductDAO().pagingSearchProductCategory(productSearch, index);
            req.setAttribute("listP", list);

            //chiatrang
            int count = new ProductDAO().getTotalProductCategorySearch(productSearch);
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            req.setAttribute("endP", endPage);

        }
        
        req.getRequestDispatcher("../product.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("pagingStatus", 2);

        ArrayList<Category> ListCategory = new CategoryDAO().getCategorys();
        req.setAttribute("ListC", ListCategory);

        int categoryToFilter = Integer.parseInt(req.getParameter("ddlCategory"));
        req.setAttribute("categoryToFilter", categoryToFilter);

        String productSearch = req.getParameter("txtSearch");
        req.setAttribute("productSearch", productSearch);

        if (categoryToFilter == 0 && productSearch == "") {
            req.getRequestDispatcher("../admin/product").forward(req, resp);
            return;
            
        } else if (productSearch == "") {
            req.getSession().setAttribute("status", "2");
            ArrayList<ProductCategory> list = new CategoryDAO().PagingProductCategoryByCategory(categoryToFilter, 1);
            req.setAttribute("listP", list);

            //chia trang
            int count = new CategoryDAO().getTotalProductByCategory(categoryToFilter);
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            req.setAttribute("endP", endPage);
        } else {
            req.getSession().setAttribute("status", "3");
            ArrayList<ProductCategory> list = new CategoryDAO().PagingProductCategoryByCategoryProductName(categoryToFilter, productSearch, 1);
            req.setAttribute("listP", list);
            req.setAttribute(productSearch, "productSearch");

            //chia trang
            int count = new CategoryDAO().getTotalProductByCategoryProductName(productSearch, categoryToFilter);
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            req.setAttribute("endP", endPage);
        }

        if (categoryToFilter == 0 && productSearch != "") {
            req.getSession().setAttribute("status", "4");
            ArrayList<ProductCategory> list = new ProductDAO().pagingSearchProductCategory(productSearch, 1);
            req.setAttribute("listP", list);

            //chiatrang
            int count = new ProductDAO().getTotalProductCategorySearch(productSearch);
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            req.setAttribute("endP", endPage);
            req.setAttribute(productSearch, "productSearch");
        }

        //page ban dau
        req.setAttribute("tag", 1);
        req.getRequestDispatcher("../product.jsp").forward(req, resp);
    }

}
