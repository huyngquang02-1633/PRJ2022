/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAL.Account;
import DAL.Cart;
import DAL.Customer;
import DAL.Item;
import DAL.ProductCategory;
import DAL.Products;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import DAO.AccountDAO;
import DAO.ProductDAO;

/**
 *
 * @author ADMIN
 */
public class ShoppingBuyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = null;
        Cart o = (Cart) req.getSession().getAttribute("cart");

        if (o != null) {
            cart = o;
        } else {
            cart = new Cart();
        }

        //bynow
        int num = 0;
        if (req.getParameter("num") != null) {
            num = Integer.parseInt(req.getParameter("num"));
        }
        //addtocart
        if (req.getParameter("num") == null) {
            num = 1;
        }

        int id = Integer.parseInt(req.getParameter("tid"));

        if (num <= 0 && cart.getQuantityByID(id) == 1) {
            cart.removeItemById(id);
        } else {
            Products p = new ProductDAO().getProductByProductID(id);
            double price = p.getUnitPrice();
            Item t = new Item(p, num, price);

            cart.addItem(t);
        }

        ArrayList<Item> list = cart.getItems();
        req.getSession().setAttribute("cart", cart);
        req.getSession().setAttribute("total", cart.getTotalMoney());
        req.getSession().setAttribute("size", list.size());

        if (req.getParameter("num") != null) {
            //lay ìnfo customer
            req.getRequestDispatcher("../shopping/order").forward(req, resp);
        }
        if (req.getParameter("num") == null) {
            ProductCategory pc = new ProductDAO().getProductCategoryByProductID(id);
            req.setAttribute("detail", pc);

            req.getRequestDispatcher("../detail.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //remove
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        int id = Integer.parseInt(req.getParameter("tid"));

        cart.removeItemById(id);

        ArrayList<Item> list = cart.getItems();
        req.getSession().setAttribute("cart", cart);
        req.getSession().setAttribute("total", cart.getTotalMoney());
        req.getSession().setAttribute("size", list.size());

        req.getRequestDispatcher("../cart.jsp").forward(req, resp);

    }

}
