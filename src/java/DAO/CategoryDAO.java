/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAL.Category;
import DAL.ProductCategory;
import DAL.Products;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class CategoryDAO extends DAL.DBContext {

    public ArrayList<Category> getCategorys() {
        ArrayList<Category> list = new ArrayList<>();
        try {
            String sql = "select * from Categories";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int CategoryID = rs.getInt("CategoryID");
                String CategoryName = rs.getString("CategoryName");
                String Description = rs.getString("Description");
                String Picture = rs.getString("Picture");

                Category p = new Category(CategoryID, CategoryName, Description, Picture);

                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public String getCategoryNameByCid(int cid) {
        try {
            String sql = "select CategoryName from Categories where CategoryID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, cid);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getString(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Products> getProductByCategory(String cid) {
        ArrayList<Products> list = new ArrayList<>();
        try {
            String sql = "select * from Products where CategoryID = ?";
            PreparedStatement ps = connection.prepareStatement(sql); //phai extends DBcontext
            ps.setString(1, cid);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = rs.getBoolean("Discontinued");

                Products p = new Products(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
                list.add(p);

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return list;
    }

    public ArrayList<Products> PagingProductByCategory(int cid, int index) {
        ArrayList<Products> list = new ArrayList<>();
        try {
            String sql = "select * from Products where CategoryID = ?\n"
                    + "order by CategoryID\n"
                    + "offset ? rows fetch next 8 rows only";
            PreparedStatement ps = connection.prepareStatement(sql); //phai extends DBcontext
            ps.setInt(1, cid);
            ps.setInt(2, (index - 1) * 8);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = rs.getBoolean("Discontinued");

                Products p = new Products(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
                list.add(p);

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return list;
    }

    public ArrayList<ProductCategory> PagingProductCategoryByCategory(int cid, int index) {
        ArrayList<ProductCategory> list = new ArrayList<>();
        try {
            String sql = "select * from Products a INNER JOIN Categories b ON a.CategoryID=b.CategoryID where a.CategoryID = ?\n"
                    + "order by ProductID\n"
                    + "offset ? rows fetch next 10 rows only";
            PreparedStatement ps = connection.prepareStatement(sql); //phai extends DBcontext
            ps.setInt(1, cid);
            ps.setInt(2, (index - 1) * 10);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = rs.getBoolean("Discontinued");

                String categoryName = rs.getString("CategoryName");

                Products product = new Products(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
                Category category = new Category(CategoryID, categoryName);

                list.add(new ProductCategory(product, category));

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return list;
    }

    public ArrayList<ProductCategory> PagingProductCategoryByCategoryProductName(int cid,String txt, int index) {
        ArrayList<ProductCategory> list = new ArrayList<>();
        try {
            String sql = "select * from Products as a inner join Categories as b on a.CategoryID = b.CategoryID where a.CategoryID = ? and ProductName like ?\n"
                    + "order by ProductID\n"
                    + "offset ? rows fetch next 10 rows only";
            PreparedStatement ps = connection.prepareStatement(sql); //phai extends DBcontext
            ps.setInt(1, cid);
            ps.setString(2, "%" + txt + "%");
            ps.setInt(3, (index - 1) * 10);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = rs.getBoolean("Discontinued");

                String categoryName = rs.getString("CategoryName");

                Products product = new Products(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
                Category category = new Category(CategoryID, categoryName);

                list.add(new ProductCategory(product, category));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getTotalProductByCategoryProductName(String txt, int cid) {
        try {
            String sql = "select count(*) from Products where CategoryID = ? and ProductName like ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, cid);
            ps.setString(2, "%" + txt + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public int getTotalProductByCategory(int cid) {
        try {
            String sql = "select count(*) from Products where CategoryID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, cid);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public static void main(String[] args) {
        ArrayList<ProductCategory> list = new CategoryDAO().PagingProductCategoryByCategoryProductName(1,"cha", 1);
        for (ProductCategory productCategory : list) {
            System.out.println(productCategory);
        }
    }
}
