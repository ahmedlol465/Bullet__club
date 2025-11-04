/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bullet_club.model.Dol;

import bullet_club.entity.Order;
import bullet_club.entity.Order_Details;
import bullet_club.entity.User;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author ScarletMoon
 */
public class Genaric_CE<T> extends User_Dol {
      public void place_order(Order order, List<Order_Details> orderDetails){
            try{
                data_base.openConn();
                String s = "INSERT INTO order VALUES (?, ?, ?, ?)";
                data_base.createps(s);
                data_base.getPs().setString(1, order.getStatus());
                data_base.getPs().setDouble(2, order.getTotal_Amount());
                data_base.getPs().setDate(3, new java.sql.Date(order.getDate().getTime()));
                data_base.getPs().setInt(4, 5);///fix later
                
            }
            catch(Exception e){
                 
            }
      
  
  }
}
