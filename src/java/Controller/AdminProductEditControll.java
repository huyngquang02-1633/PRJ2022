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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import DAO.CategoryDAO;
import DAO.ProductDAO;

/**
 *
 * @author ADMIN
 */
public class AdminProductEditControll extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("AccAdminSession") == null) {
            resp.getWriter().print("Access denied");
        } else {
            req.getSession().setAttribute("productStatus", 2);
            int id = Integer.parseInt(req.getParameter("id"));

            ArrayList<Category> ListCategory = new CategoryDAO().getCategorys();
            req.setAttribute("ListC", ListCategory);

            Products products = new ProductDAO().getProductCategoryByProductID(id).getProducts();
            Category category = new ProductDAO().getProductCategoryByProductID(id).getCategory();

            req.setAttribute("product", products);
            req.setAttribute("categorys", category);

            req.getRequestDispatcher("../create-product.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int productID = Integer.parseInt(req.getParameter("txtProductId"));
        String quantityPerUnit = req.getParameter("txtQuantityPerUnit");
        boolean discontinued = !(req.getParameter("chkDiscontinued") == null);
        int categoryID = Integer.parseInt(req.getParameter("ddlCategory"));

        Products product = new Products();

        String productName = req.getParameter("txtProductName");

        if (productName.isEmpty()) {
            req.setAttribute("productNameMsg", "productName not allow null");
        }

        int unitsInStock = Integer.parseInt(req.getParameter("txtUnitsInStock"));
        if (Integer.parseInt(req.getParameter("txtUnitsInStock")) < 0) {
            req.setAttribute("unitsInStockMsg", "unitsInStock must greater or equal zero");
        }

        double unitPrice = Double.parseDouble(req.getParameter("txtUnitPrice"));
        if (unitPrice < 0) {
            req.setAttribute("unitPriceMsg", "unitPrice must greater or equal zero");
        }

        // product.setUnitPrice(unitPrice);
        int reorderLevel = Integer.parseInt(req.getParameter("txtReorderLevel"));
        if (reorderLevel < 0) {
            req.setAttribute("reorderLevelMsg", "ReorderLevel must greater or equal zero");
        }

        int unitsOnOrder = Integer.parseInt(req.getParameter("txtUnitsOnOrder"));
        if (unitsOnOrder < 0) {
            req.setAttribute("unitsOnOrderMsg", "UnitsOnOrder must greater or equal zero");
        }

        product = new Products(productID, productName, categoryID, quantityPerUnit, unitPrice, unitsInStock, unitsOnOrder, reorderLevel, discontinued);

        if(productName.isEmpty() || req.getParameter("txtUnitsInStock")==null || req.getParameter("txtUnitsInStock")==null || req.getParameter("txtUnitPrice")==null || req.getParameter("txtReorderLevel")==null || req.getParameter("txtUnitsOnOrder")==null || req.getParameter("txtQuantityPerUnit")==null) {

            req.setAttribute("product", product);

            ArrayList<Category> ListCategory = new CategoryDAO().getCategorys();
            req.setAttribute("ListC", ListCategory);

            req.getRequestDispatcher("../create-product.jsp").forward(req, resp);
        }

        new ProductDAO().update(product);
        resp.sendRedirect("../admin/product");

    }

}
