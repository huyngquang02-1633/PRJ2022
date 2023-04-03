/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

/**
 *
 * @author ADMIN
 */
public class OrderCustomerEmployee {
    Orders order;
    Customer customer;
    Employees employee;

    public OrderCustomerEmployee() {
    }

    public OrderCustomerEmployee(Orders order, Customer customer, Employees employee) {
        this.order = order;
        this.customer = customer;
        this.employee = employee;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "OrderCustomerEmployee{" + "order=" + order + ", customer=" + customer + ", employee=" + employee + '}';
    }
    
    
}
