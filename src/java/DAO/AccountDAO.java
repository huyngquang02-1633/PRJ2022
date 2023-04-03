/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAL.Account;
import DAL.AccountCustomer;
import DAL.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class AccountDAO extends DAL.DBContext {

    public Account getAccount(String email, String pass) {
        Account acc = null;
        try {
            String sql = "select * from Accounts where Email=? and Password=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int AccountID = rs.getInt("AccountID");
                String Email = rs.getString("Email");
                String Password = rs.getString("Password");
                String CustomerID = rs.getString("CustomerID");
                int EmployeeID = rs.getInt("EmployeeID");
                int Role = rs.getInt("Role");
                acc = new Account(AccountID, Email, Password, CustomerID, EmployeeID, Role);
                return acc;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Account CheckEmailExist(String email) {
        Account p = null;
        try {
            String sql = "select * from Accounts\n"
                    + "where Email = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                p = new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

    public void SignUpInAccount(String email, String pass) {
        String sql = "insert into Accounts (Email, [Password])\n"
                + "values(?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, pass);
            //khong tra ve kh can ham result va 
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String randomString(int n) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }

    public int CreateAccount(Customer cust, Account acc) {
        int result1 = 0, result2 = 0;
        String sql1 = "insert into Customers(CustomerID, CompanyName,ContactName,ContactTitle,Address) values(?,?,?,?,?)";
        String sql2 = "insert into Accounts(Email,Password,CustomerID,Role) values(?,?,?,?)";
        String a = randomString(5);
        try {
            PreparedStatement ps1 = connection.prepareStatement(sql1);
            PreparedStatement ps2 = connection.prepareStatement(sql2);
            ps1.setString(1, a);
            ps1.setString(2, cust.getCompanyName());
            ps1.setString(3, cust.getContactName());
            ps1.setString(4, cust.getContactTitle());
            ps1.setString(5, cust.getAddress());

            ps2.setString(1, acc.getEmail());
            ps2.setString(2, acc.getPassword());
            ps2.setString(3, a);
            ps2.setInt(4, 2);

            result1 = ps1.executeUpdate();
            result2 = ps2.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result1 > 0 && result2 > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public AccountCustomer getAccountCustomersByAccountID(int id) {
        AccountCustomer p = null;
        try {
            String sql = "select * from Accounts as a left join Customers as c on a.CustomerID = c.CustomerID where AccountID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int AccountID = rs.getInt("AccountID");
                String Email = rs.getString("Email");
                String Password = rs.getString("Password");
                String CustomerID = rs.getString("CustomerID");
                int EmployeeID = rs.getInt("EmployeeID");
                int Role = rs.getInt("Role");

                String CompanyName = rs.getString("CompanyName");
                String ContactName = rs.getString("ContactName");
                String ContactTitle = rs.getString("ContactTitle");
                String Address = rs.getString("Address");

                Account account = new Account(AccountID, Email, Password, CustomerID, EmployeeID, Role);
                Customer customer = new Customer(CustomerID, CompanyName, ContactName, ContactTitle, Address);

                return p = new AccountCustomer(account, customer);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }

    public void EditInfo(Customer c) {
        try {
            String sql = "update Customers set CompanyName = ?, ContactName = ?, ContactTitle = ?, Address = ? where CustomerID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, c.getCompanyName());
            ps.setString(2, c.getContactName());
            ps.setString(3, c.getContactTitle());
            ps.setString(4, c.getAddress());
            ps.setString(5, c.getCustomerID());

            ps.executeUpdate();

        } catch (Exception e) {
        }
    }

    public void CreateCustomer(Customer cust) {
        String sql = "insert into Customers(CustomerID, CompanyName,ContactName,ContactTitle,Address) values(?,?,?,?,?)";
        String a = randomString(5);
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, a);
            ps.setString(2, cust.getCompanyName());
            ps.setString(3, cust.getContactName());
            ps.setString(4, cust.getContactTitle());
            ps.setString(5, cust.getAddress());
            
            ps.executeUpdate();
            
        } catch (Exception e) {
        }

    }

    public static void main(String[] args) {

        Customer customer = new Customer("32Ulg", "GGG", "UUU", "TTT", "AAA");
        new AccountDAO().EditInfo(customer);
    }

}
