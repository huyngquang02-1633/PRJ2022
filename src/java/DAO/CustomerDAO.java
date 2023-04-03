/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAL.Customer;
import DAL.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class CustomerDAO extends DBContext {

    public ArrayList<Customer> PagingAllCustomers(int index) {
        ArrayList<Customer> list = new ArrayList<>();
        String sql = "select * from Customers\n"
                + "order by CustomerID\n"
                + "offset ? rows fetch next 10 rows only";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 10);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String CustomerID = rs.getString("CustomerID");
                String CompanyName = rs.getString("CompanyName");
                String ContactName = rs.getString("ContactName");
                String ContactTitle = rs.getString("ContactTitle");
                String Address = rs.getString("Address");

                list.add(new Customer(CustomerID, CompanyName, ContactName, ContactTitle, Address));

            }
        } catch (Exception e) {
        }
        return list;
    }

    public int getTotalCustomer() {
        try {
            String sql = "select count(*) from Customers";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public void deleteCustomer(String cid) {
        String sql = "delete from Accounts where CustomerID = ?\n"
                + "delete from Customers where CustomerID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, cid);
            ps.setString(2, cid);
            ps.executeUpdate();

        } catch (Exception e) {
        }
    }

    public ArrayList<Customer> PagingSearchCustomers(String cid, int index) {
        ArrayList<Customer> list = new ArrayList<>();
        String sql = "select * from Customers where CustomerID like ?\n"
                + "order by CustomerID\n"
                + "offset ? rows fetch next 10 rows only";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + cid + "%");
            ps.setInt(2, (index - 1) * 10);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String CustomerID = rs.getString("CustomerID");
                String CompanyName = rs.getString("CompanyName");
                String ContactName = rs.getString("ContactName");
                String ContactTitle = rs.getString("ContactTitle");
                String Address = rs.getString("Address");

                list.add(new Customer(CustomerID, CompanyName, ContactName, ContactTitle, Address));

            }
        } catch (Exception e) {
        }
        return list;
    }

    public int getTotalSearchCustomer(String cid) {
        try {
            String sql = "select count(*) from Customers where CustomerID like ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + cid + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

}
