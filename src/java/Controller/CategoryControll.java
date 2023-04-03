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
public class CategoryControll extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        
        ArrayList<Category> p = new CategoryDAO().getCategorys();
        req.setAttribute("ListC", p);
        
        int cid = Integer.parseInt(req.getParameter("cid"));
        req.setAttribute("cate", cid); //phan dung trang 
        
        String Cname = new CategoryDAO().getCategoryNameByCid(cid);
        req.setAttribute("CategoryName", Cname);
        
        if (req.getParameter("index") == null) {
            ArrayList<Products> list = new CategoryDAO().PagingProductByCategory(cid, 1);
            req.setAttribute("ListP", list);
            req.setAttribute("tag", 1);
        } else {
            int index = Integer.parseInt(req.getParameter("index"));
            ArrayList<Products> list = new CategoryDAO().PagingProductByCategory(cid, index);
            req.setAttribute("ListP", list);
            req.setAttribute("tag", index);
        }
        
        int count = new CategoryDAO().getTotalProductByCategory(cid);
        int endPage = count / 8;
        if (count % 8 != 0) {
            endPage++;
        }
        req.setAttribute("endP", endPage);

        req.getRequestDispatcher("category.jsp").forward(req, resp);
    }

}
