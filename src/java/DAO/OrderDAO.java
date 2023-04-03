/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAL.Cart;
import DAL.Customer;
import DAL.DBContext;
import DAL.Employees;
import DAL.Item;
import DAL.OrderCustomerEmployee;
import DAL.OrderDetail;
import DAL.Orders;
import DAL.Products;
import jakarta.servlet.jsp.jstl.sql.Result;
import java.sql.Array;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class OrderDAO extends DBContext {

    public ArrayList<OrderCustomerEmployee> pagingOrderCustomerEmployee(int index) {
        ArrayList<OrderCustomerEmployee> list = new ArrayList<>();
        String sql = "select * from Customers as c right join Orders as o on c.CustomerID = o.CustomerID left join Employees as e on o.EmployeeID = e.EmployeeID\n"
                + "order by OrderID\n"
                + "offset ? rows fetch next 10 rows only";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 10);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int OrderID = rs.getInt("OrderID");
                String CustomerID = rs.getString("CustomerID");
                int EmployeeID = rs.getInt("EmployeeID");
                Date OrderDate = rs.getDate("OrderDate");
                Date RequiredDate = rs.getDate("RequiredDate");
                Date ShippedDate = rs.getDate("ShippedDate");
                double Freight = rs.getDouble("Freight");
                String ShipName = rs.getString("ShipName");
                String ShipAddress = rs.getString("ShipAddress");
                String ShipCity = rs.getString("ShipCity");
                String ShipRegion = rs.getString("ShipRegion");
                String ShipPostalCode = rs.getString("ShipPostalCode");
                String ShipCountry = rs.getString("ShipCountry");

                Orders order = new Orders(OrderID, CustomerID, EmployeeID, OrderDate, RequiredDate, ShippedDate, Freight, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry);

                String ContactName = rs.getString("ContactName");

                Customer customer = new Customer(CustomerID, ContactName);

                String FirstName = rs.getString("FirstName");

                Employees employee = new Employees(EmployeeID, FirstName);

                list.add(new OrderCustomerEmployee(order, customer, employee));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public int getTotalOrderCustomerEmployee() {
        try {
            String sql = "select count(*) from Customers as c right join Orders as o on c.CustomerID = o.CustomerID left join Employees as e on o.EmployeeID = e.EmployeeID";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public ArrayList<OrderCustomerEmployee> PagingOrderInRange(String fromDate, String toDate, int index) {
        ArrayList<OrderCustomerEmployee> list = new ArrayList<>();
        try {
            String sql = "";
            PreparedStatement ps = null;
            if (!fromDate.isEmpty() && toDate.isEmpty()) {
                sql = "select * from Customers as c right join Orders as o on c.CustomerID = o.CustomerID left join Employees as e on o.EmployeeID = e.EmployeeID  where o.OrderDate >= ? order by OrderID\n"
                        + "offset ? rows fetch next 10 rows only";
                ps = connection.prepareStatement(sql);
                ps.setDate(1, Date.valueOf(fromDate));
                ps.setInt(2, (index - 1) * 10);
            } else if (fromDate.isEmpty() && !toDate.isEmpty()) {
                sql = "select * from Customers as c right join Orders as o on c.CustomerID = o.CustomerID left join Employees as e on o.EmployeeID = e.EmployeeID where o.OrderDate <= ? order by OrderID\n"
                        + "offset ? rows fetch next 10 rows only";
                ps = connection.prepareStatement(sql);
                ps.setDate(1, Date.valueOf(toDate));
                ps.setInt(2, (index - 1) * 10);
            } else if (!fromDate.isEmpty() && !toDate.isEmpty()) {
                sql = "select * from Customers as c right join Orders as o on c.CustomerID = o.CustomerID left join Employees as e on o.EmployeeID = e.EmployeeID where o.OrderDate BETWEEN ? AND ? order by OrderID\n"
                        + "offset ? rows fetch next 10 rows only";
                ps = connection.prepareStatement(sql);
                ps.setDate(1, Date.valueOf(fromDate));
                ps.setDate(2, Date.valueOf(toDate));
                ps.setInt(3, (index - 1) * 10);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int OrderID = rs.getInt("OrderID");
                String CustomerID = rs.getString("CustomerID");
                int EmployeeID = rs.getInt("EmployeeID");
                Date OrderDate = rs.getDate("OrderDate");
                Date RequiredDate = rs.getDate("RequiredDate");
                Date ShippedDate = rs.getDate("ShippedDate");
                double Freight = rs.getDouble("Freight");
                String ShipName = rs.getString("ShipName");
                String ShipAddress = rs.getString("ShipAddress");
                String ShipCity = rs.getString("ShipCity");
                String ShipRegion = rs.getString("ShipRegion");
                String ShipPostalCode = rs.getString("ShipPostalCode");
                String ShipCountry = rs.getString("ShipCountry");

                Orders order = new Orders(OrderID, CustomerID, EmployeeID, OrderDate, RequiredDate, ShippedDate, Freight, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry);

                String ContactName = rs.getString("ContactName");

                Customer customer = new Customer(CustomerID, ContactName);

                String FirstName = rs.getString("FirstName");

                Employees employee = new Employees(EmployeeID, FirstName);

                list.add(new OrderCustomerEmployee(order, customer, employee));
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return list;
    }

    public int getTatolOrderInRange(String fromDate, String toDate) {
        try {
            String sql = "";
            PreparedStatement ps = null;
            if (!fromDate.isEmpty() && toDate.isEmpty()) {
                sql = "select count(*) from Customers as c right join Orders as o on c.CustomerID = o.CustomerID left join Employees as e on o.EmployeeID = e.EmployeeID  where o.OrderDate >= ?";
                ps = connection.prepareStatement(sql);
                ps.setDate(1, Date.valueOf(fromDate));
            } else if (fromDate.isEmpty() && !toDate.isEmpty()) {
                sql = "select count(*) from Customers as c right join Orders as o on c.CustomerID = o.CustomerID left join Employees as e on o.EmployeeID = e.EmployeeID where o.OrderDate <= ? ";
                ps = connection.prepareStatement(sql);
                ps.setDate(1, Date.valueOf(toDate));
            } else if (!fromDate.isEmpty() && !toDate.isEmpty()) {
                sql = "select count(*) from Customers as c right join Orders as o on c.CustomerID = o.CustomerID left join Employees as e on o.EmployeeID = e.EmployeeID where o.OrderDate BETWEEN ? AND ? ";
                ps = connection.prepareStatement(sql);
                ps.setDate(1, Date.valueOf(fromDate));
                ps.setDate(2, Date.valueOf(toDate));
            } else if (fromDate.isEmpty() && toDate.isEmpty()) {
                sql = "select count(*) from Customers as c right join Orders as o on c.CustomerID = o.CustomerID left join Employees as e on o.EmployeeID = e.EmployeeID";
                ps = connection.prepareStatement(sql);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return 0;
    }

    public ArrayList<OrderCustomerEmployee> getOrderInRange(Date fromDate, Date toDate) {
        ArrayList<OrderCustomerEmployee> orders = new ArrayList<>();
        try {
            String sql = "";
            PreparedStatement ps = null;
            if (fromDate != null && toDate == null) {
                sql = "select * from Customers as c right join Orders as o on c.CustomerID = o.CustomerID left join Employees as e on o.EmployeeID = e.EmployeeID  where o.OrderDate <= ?";
                ps = connection.prepareStatement(sql);
                ps.setDate(1, fromDate);
            } else if (fromDate == null && toDate != null) {
                sql = "select * from Customers as c right join Orders as o on c.CustomerID = o.CustomerID left join Employees as e on o.EmployeeID = e.EmployeeID where o.OrderDate <= ? ";
                ps = connection.prepareStatement(sql);
                ps.setDate(1, toDate);
            } else if (fromDate != null && toDate != null) {
                sql = "select * from Customers as c right join Orders as o on c.CustomerID = o.CustomerID left join Employees as e on o.EmployeeID = e.EmployeeID where o.OrderDate BETWEEN ? AND ? ";
                ps = connection.prepareStatement(sql);
                ps.setDate(1, fromDate);
                ps.setDate(2, toDate);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int OrderID = rs.getInt("OrderID");
                String CustomerID = rs.getString("CustomerID");
                int EmployeeID = rs.getInt("EmployeeID");
                Date OrderDate = rs.getDate("OrderDate");
                Date RequiredDate = rs.getDate("RequiredDate");
                Date ShippedDate = rs.getDate("ShippedDate");
                double Freight = rs.getDouble("Freight");
                String ShipName = rs.getString("ShipName");
                String ShipAddress = rs.getString("ShipAddress");
                String ShipCity = rs.getString("ShipCity");
                String ShipRegion = rs.getString("ShipRegion");
                String ShipPostalCode = rs.getString("ShipPostalCode");
                String ShipCountry = rs.getString("ShipCountry");

                Orders order = new Orders(OrderID, CustomerID, EmployeeID, OrderDate, RequiredDate, ShippedDate, Freight, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry);

                String ContactName = rs.getString("ContactName");

                Customer customer = new Customer(CustomerID, ContactName);

                String FirstName = rs.getString("FirstName");

                Employees employee = new Employees(EmployeeID, FirstName);

                orders.add(new OrderCustomerEmployee(order, customer, employee));
            }
        } catch (Exception e) {

        }//finally{ connection.close();}
        return orders;
    }

    public void cancelOrder(int orderID) {
        try {
            String sql = "update Orders SET RequiredDate=null WHERE OrderID=? AND ShippedDate IS NULL";
            //b2 tao doi tuong nhe
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, orderID);
            //b3thuc thi truy van
            ps.executeUpdate();
            //b4 xu ly kqua tra ve

        } catch (Exception e) {

        }

    }

    public void addOrder(Customer c, Cart cart) {
        LocalDate curDate = java.time.LocalDate.now();
        String date = curDate.toString();
        try {
            String sql = "insert into Orders (CustomerID, OrderDate, RequiredDate, Freight,ShipAddress) values (?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, c.getCustomerID());
            ps.setString(2, date);
            ps.setString(3, date);
            ps.setDouble(4, cart.getTotalMoney());
            ps.setString(5, c.getAddress());
            ps.executeUpdate();

            String sql1 = "select top 1 OrderID from Orders \n"
                    + "order by OrderID desc";
            PreparedStatement ps1 = connection.prepareStatement(sql1);
            ResultSet rs = ps1.executeQuery();
            while (rs.next()) {
                int orderId = rs.getInt(1);

                for (Item o : cart.getItems()) {
                    String sql2 = "insert into [Order Details] values(?,?,?,?,?)";
                    PreparedStatement ps2 = connection.prepareStatement(sql2);
                    ps2.setInt(1, orderId);
                    ps2.setInt(2, o.getProduct().getProductID());
                    ps2.setDouble(3, o.getProduct().getUnitPrice());
                    ps2.setInt(4, o.getQuantity());
                    ps2.setDouble(5, 0);

                    int quantity = o.getQuantity();
                    ps2.executeUpdate();

                }

            }

        } catch (Exception e) {
        }

    }

    public ArrayList<Orders> getOrderByCustomerId(String id) {
        ArrayList<Orders> list = new ArrayList<>();
        try {
            String sql = "select * from Orders where CustomerID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int OrderID = rs.getInt("OrderID");
                String CustomerID = rs.getString("CustomerID");
                int EmployeeID = rs.getInt("EmployeeID");
                Date OrderDate = rs.getDate("OrderDate");
                Date RequiredDate = rs.getDate("RequiredDate");
                Date ShippedDate = rs.getDate("ShippedDate");
                double Freight = rs.getDouble("Freight");
                String ShipName = rs.getString("ShipName");
                String ShipAddress = rs.getString("ShipAddress");
                String ShipCity = rs.getString("ShipCity");
                String ShipRegion = rs.getString("ShipRegion");
                String ShipPostalCode = rs.getString("ShipPostalCode");
                String ShipCountry = rs.getString("ShipCountry");

                Orders o = new Orders(OrderID, CustomerID, EmployeeID, OrderDate, RequiredDate, ShippedDate, Freight, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry);
                list.add(o);
            }
        } catch (Exception e) {
        }
        return list;

    }
    

    public ArrayList<OrderDetail> getOrderDetailByOrderId(int id) {
        ArrayList<OrderDetail> list = new ArrayList<>();
        try {
            String sql = "select * from [Order Details] where OrderID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int OrderID = rs.getInt("OrderID");
                int ProductID = rs.getInt("ProductID");
                double UnitPrice = rs.getDouble("UnitPrice");
                double Quantity = rs.getDouble("Quantity");
                double Discount = rs.getDouble("Discount");
                
                OrderDetail od = new OrderDetail(OrderID, ProductID, UnitPrice, Quantity, Discount);
                list.add(od);
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public String getProductNameByProductId(int id) {
        try {
            String sql = "select [ProductName] from Products where ProductID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String productName = rs.getString(1);
                return productName;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static void main(String[] args) {
//        ArrayList<Orders> list = new OrderDAO().getOrderByCustomerId("GODOS");
//        for (Orders orderCustomerEmployee : list) {
//            System.out.println(orderCustomerEmployee);
//        }

//    String name = new OrderDAO().getProductNameByProductId(2);
//        System.out.println(name);
    }
}
