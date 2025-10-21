/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bullet_club.model.Dol;

import bullet_club.entity.User;

/**
 *
 * @author ScarletMoon
 */
public class Genaric_CE<T> extends User_Dol {
      public void place_order(){
            try{
                data_base.openConn();
                String s="select * from user where username=?and email=?";
                data_base.createps(s);
                
            }
            catch(Exception e){
            }
      
  
  }
}
