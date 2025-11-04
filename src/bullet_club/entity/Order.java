/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bullet_club.entity;
import  bullet_club.entity.Order_Details;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ENG abdelrahman nagi
 */
public class Order {
   private int id;
   private String status;
   private int total_Amount;
   private Date date;
   private ArrayList<Order_Details> items;
 ArrayList<Order> orders=new ArrayList<>() ;
    public Order(int id, String status, int total_Amount, Date date, ArrayList<Order_Details> items) {
        this.id = id;
        this.status = status;
        this.total_Amount = total_Amount;
        this.date = date;
        this.items = items;
if(this.status=="ACC")
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<Order_Details> getItems() {
        return items;
    }

    public void setItems(ArrayList<Order_Details> items) {
        this.items = items;
    }
   public long total_price(){
   long sum=0;
   for( Order_Details item:items ){
   sum+=item.getPrice();
   
   }
   return sum;
   }
    public String order_info() {
        return "Order{" + "id=" + id + ", status=" + status + ", total_Amount=" + total_Amount + ", date=" + date + ", items=" + items + '}';
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }


 

}  
      
 
