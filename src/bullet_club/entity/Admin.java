/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bullet_club.entity;

import java.util.ArrayList;

/**
 *
 * @author ENG abdelrahman nagi
 */
public class Admin extends User {
    
    
    public Admin(int id, String username, String role, String password, String add, String email, String phone) {
        super(id, username, role, password, add, email, phone);
    }
public void add_user(User user1){
/*try{}catch(){}*/
}    
public void remove_user(User user1){
/*try{}catch(){}*/
}   
 public ArrayList<User> show_all(){
    ArrayList<User> users = new ArrayList<>();
    return users;
}

}
