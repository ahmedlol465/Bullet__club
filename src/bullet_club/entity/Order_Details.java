/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bullet_club.entity;

/**
 *
 * @author ENG abdelrahman nagi
 */
public class Order_Details {
   private int quantity // شويه لي هشتريهمظظ
           ;
   private int price ;
   private  Product  item  ;

    public Order_Details(int quantity, int price, Product item) {
        this.quantity = quantity;
        this.price = price;
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Product getItem() {
        return item;
    }

    public void setItem(Product item) {
        this.item = item;
    }
 
    
   public double total(){
   return this.price*this.quantity;
   }
    
  /* public void xxx(Product x){
  x.setQuantity(x.getQuantity()-this.quantity);
 
   }*///مش لاقيين اسم
}
