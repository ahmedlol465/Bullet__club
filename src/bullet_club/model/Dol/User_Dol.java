/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bullet_club.model.Dol;

import bullet_club.entity.User;

/**
 *
 * @author ENG abdelrahman nagi
 */
public class User_Dol implements Data_Conn {
   public void login(User user1){
   try{ data_base.openConn();
   String s="select *from user where username=? and password=?";
   data_base.createps(s);
data_base.getPs().setString(1, user1.getUsername());
data_base.getPs().setString(2, user1.getPassword());
   data_base.createRs();
   int coun=0;
   while(data_base.getRs().next()){
   System.out.println(true);
   coun++;
   }
   if(coun==0)
 System.out.println(false);
   data_base.closseConn();
   
   
   }catch(Exception e){
        
        
System.out.println(e);
   
    }
   } 
 
void logout(){
   /* try{
    }catch(){
        
        

}*/
}

public void change_password(User user1,String pass) {
    try {
        data_base.openConn();

        String s = "UPDATE user SET password = ? WHERE username = ? AND email = ?";
        data_base.createps(s);
        data_base.getPs().setString(1, pass);
        data_base.getPs().setString(2, user1.getUsername());
        data_base.getPs().setString(3, user1.getEmail());
     
        int rows = data_base.getPs().executeUpdate();

        if (rows > 0) {
        user1.setPassword(pass);
            System.out.println("Password updated successfully for user: " + user1.getUsername());
        } else {
            System.out.println("️ No matching user found (check username/email).");
        }

        data_base.closseConn();

    } catch (Exception e) {
        System.out.println("Error in change_password: " + e.getMessage());
    }
}
}

