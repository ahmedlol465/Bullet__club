/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bullet_club.entity;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author ENG abdelrahman nagi
 */
public class Purchase_Order {
       private int id;
   private String status;
   private int total_Amount;
   private LocalDate date;
   private ArrayList<Purchase_Details> items;
 ArrayList<Purchase_Order> orders=new ArrayList<>() ;
    public Purchase_Order(int id, String status, int total_Amount, LocalDate date, ArrayList<Purchase_Details> items) {
        this.id = id;
        this.status = status;
        this.total_Amount = total_Amount;
        this.date = date;
        this.items = items;
if(this.status=="     ")
        orders.add(this); 
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotal_Amount() {
        return total_Amount;
    }

    public void setTotal_Amount(int total_Amount) {
        this.total_Amount = total_Amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ArrayList<Purchase_Details> getItems() {
        return items;
    }

    public void setItems(ArrayList<Purchase_Details> items) {
        this.items = items;
    }
   public long total_price(){
   long sum=0;
   for(  Purchase_Details item:items  ){
   sum+=item.getPrice();
   
   }
   return sum;
   }
    public String order_info() {
        return "Order{" + "id=" + id + ", status=" + status + ", total_Amount=" + total_Amount + ", date=" + date + ", items=" + items + '}';
    }

    public ArrayList<Purchase_Order> getOrders() {
        return orders;
    }


}
