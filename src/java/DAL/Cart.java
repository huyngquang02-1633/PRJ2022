/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class Cart {

    private ArrayList<Item> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public Cart(ArrayList<Item> items) {
        this.items = items;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public Item getItemByID(int id) {
        for (Item item : items) {
            if (item.getProduct().getProductID() == id) {
                return item;
            }
        }
        return null;
    }

    public int getQuantityByID(int id) {
        return getItemByID(id).getQuantity();
    }
    
    public void addItem(Item t){
        if(getItemByID(t.getProduct().getProductID())!=null){
            Item i = getItemByID(t.getProduct().getProductID());
            i.setQuantity(i.getQuantity()+t.getQuantity());
        }else{
            items.add(t);
        }
    }
    
    public void removeItemById(int id){
        if(getItemByID(id)!=null){
            items.remove(getItemByID(id));
        }
    }
    
    public void removeItem(Item t){
        if(t!=null){
            items.remove(t);
        }
    }
    
    public double getTotalMoney(){
    double t = 0;
            for (Item item : items) {
                t+=item.getQuantity()*item.getPrice();
            }
        return t;
    }

}
