/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAL.Category;
import DAL.DBContext;
import DAL.ProductCategory;
import DAL.Products;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class ProductDAO extends DAL.DBContext {

    public ArrayList<Products> getHotProduct() {
        ArrayList<Products> p = new ArrayList<>();
        try {
            String sql = "select top 4 * from (select COUNT(*) as numberOrder, ProductID from [Order Details] \n"
                    + "group by ProductID) as A inner join Products on A.ProductID=Products.ProductID ORDER BY numberOrder DESC";
            PreparedStatement ps = connection.prepareStatement(sql); //phai extends DBcontext
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

                p.add(new Products(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued));

            }

        } catch (Exception e) {
            e.getStackTrace();
        }
        return p;

    }

    public ArrayList<Products> getNewProduct() {
        ArrayList<Products> p = new ArrayList<>();
        try {
            String sql = "select top 4 * from Products order by ProductID desc";
            PreparedStatement ps = connection.prepareStatement(sql); //phai extends DBcontext
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

                p.add(new Products(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued));

            }

        } catch (Exception e) {
            e.getStackTrace();
        }
        return p;

    }

    public ArrayList<Products> getAllProduct() {
        ArrayList<Products> p = new ArrayList<>();
        try {
            String sql = "select * from Products";
            PreparedStatement ps = connection.prepareStatement(sql); //phai extends DBcontext
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

                p.add(new Products(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued));

            }

        } catch (Exception e) {
            e.getStackTrace();
        }
        return p;

    }

    public ArrayList<ProductCategory> pagingAllProductCategory(int index) {
        ArrayList<ProductCategory> list = new ArrayList<>();
        String sql = "select * from Products a INNER JOIN Categories b ON a.CategoryID=b.CategoryID\n"
                + "order by ProductID\n"
                + "offset ? rows fetch next 10 rows only";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 10);
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

        }

        return list;
    }

    public int getTotalProduct() {
        try {
            String sql = "select count(*) from Products a INNER JOIN Categories b ON a.CategoryID=b.CategoryID";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public ArrayList<Products> SearchProductsByName(String txtSearch) {
        ArrayList<Products> p = new ArrayList<>();
        try {
            String sql = "select * from Products where ProductName like ?";
            PreparedStatement ps = connection.prepareStatement(sql); //phai extends DBcontext
            ps.setString(1, "%" + txtSearch + "%");
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

                p.add(new Products(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued));

            }

        } catch (Exception e) {
            e.getStackTrace();
        }
        return p;

    }

    public ArrayList<ProductCategory> pagingSearchProductCategory(String txt, int index) {
        ArrayList<ProductCategory> list = new ArrayList<>();
        String sql = "select * from Products a INNER JOIN Categories b ON a.CategoryID=b.CategoryID where ProductName like ?\n"
                + "order by ProductID\n"
                + "offset ? rows fetch next 10 rows only";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" +txt+ "%");
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

        }

        return list;
    }

    public int getTotalProductCategorySearch(String txtSearch) {
        try {
            String sql = "select count(*) from Products a INNER JOIN Categories b ON a.CategoryID=b.CategoryID where ProductName like ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + txtSearch + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public ProductCategory getProductCategoryByProductID(int id) {
        ProductCategory p = null;
        try {
            String sql = "select *  from Products as a , Categories as b  where a.CategoryID=b.CategoryID AND a.ProductID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int productID = rs.getInt("ProductID");
                String productName = rs.getString("ProductName");
                int categoryID = rs.getInt("CategoryID");
                String quantityPerUnit = rs.getString("QuantityPerUnit");
                double unitPrice = rs.getDouble("UnitPrice");
                int unitsInStock = rs.getInt("UnitsInStock");
                int unitsOnOrder = rs.getInt("UnitsOnOrder");
                int reorderLevel = rs.getInt("ReorderLevel");
                boolean discontinued = rs.getBoolean("Discontinued");

                String categoryName = rs.getString("CategoryName");
                Products product = new Products(productID, productName, categoryID, quantityPerUnit, unitPrice, unitsInStock, unitsOnOrder, reorderLevel, discontinued);
                Category category = new Category(categoryID, categoryName);

                p = new ProductCategory(product, category);
            }
        } catch (SQLException e) {
        }
        return p;
    }
    
    public Products getProductByProductID(int id) {
        try {
            String sql = "select *  from Products where ProductID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int productID = rs.getInt("ProductID");
                String productName = rs.getString("ProductName");
                int categoryID = rs.getInt("CategoryID");
                String quantityPerUnit = rs.getString("QuantityPerUnit");
                double unitPrice = rs.getDouble("UnitPrice");
                int unitsInStock = rs.getInt("UnitsInStock");
                int unitsOnOrder = rs.getInt("UnitsOnOrder");
                int reorderLevel = rs.getInt("ReorderLevel");
                boolean discontinued = rs.getBoolean("Discontinued");

                Products p = new Products(productID, productName, categoryID, quantityPerUnit, unitPrice, unitsInStock, unitsOnOrder, reorderLevel, discontinued);
                return p;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public int getTotalProductSearch(String txtSearch) {
        try {
            String sql = "select count(*) from Products where ProductName like ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + txtSearch + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public ArrayList<Products> pagingProductSearch(String txtSearch, int index) {
        ArrayList<Products> list = new ArrayList<>();
        String sql = "select * from Products where ProductName like ?\n"
                + "order by ProductID\n"
                + "offset ? rows fetch next 8 rows only";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + txtSearch + "%");
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

                list.add(new Products(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued));

            }
        } catch (Exception e) {

        }

        return list;
    }

    public void update(Products p) {
        try {

            String sql = "update Products SET ProductName = ?, CategoryID = ?, "
                    + "QuantityPerUnit = ?, UnitPrice = ?, UnitsInStock = ?, UnitsOnOrder = ?, "
                    + "ReorderLevel = ?, Discontinued = ? where ProductID = ?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, p.getProductName());
            ps.setInt(2, p.getCategoryID());
            ps.setString(3, p.getQuantityPerUnit());
            ps.setDouble(4, p.getUnitPrice());
            ps.setInt(5, p.getUnitsInStock());
            ps.setInt(6, p.getUnitsOnOrder());
            ps.setInt(7, p.getReorderLevel());
            ps.setBoolean(8, p.isDiscontinued());
            ps.setInt(9, p.getProductID());

            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void Delete(int ID) {
        String sql1 = "DELETE FROM Products WHERE ProductID = ?";
        String sql2 = "delete from [Order Details] where ProductID = ?";
        try {

            PreparedStatement ps = connection.prepareStatement(sql2);
            ps.setInt(1, ID);
            ps.executeUpdate();

            PreparedStatement ps1 = connection.prepareStatement(sql1);
            ps1.setInt(1, ID);
            ps1.executeUpdate();

        } catch (SQLException e) {
        }
    }

    public void CreateProduct(Products p) {
        String sql = "insert into Products(ProductName,CategoryID,QuantityPerUnit,UnitPrice,"
                + "UnitsInStock,Discontinued)\n"
                + "values(?,?,?,?,?,?)";
        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, p.getProductName());
            ps.setInt(2, p.getCategoryID());
            ps.setString(3, p.getQuantityPerUnit());
            ps.setDouble(4, p.getUnitPrice());
            ps.setInt(5, p.getUnitsInStock());
            ps.setBoolean(6, p.isDiscontinued());
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

}
