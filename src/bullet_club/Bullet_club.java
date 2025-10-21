/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bullet_club;

import bullet_club.entity.Admin;
import bullet_club.entity.User;
import bullet_club.model.Dol.Admin_Dol;
import bullet_club.model.Dol.User_Dol;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author ENG abdelrahman nagi
 */
public class Bullet_club {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
User omar=new User(2,"Tarek_Admin","admin","1234a","Cairo","tarek@comp.com","01001234567"); 
Admin kareem=new Admin(1000,"omar","admin","1234a","Cairo","tarek@comp.com","01001234567"); 
Admin_Dol xx=new  Admin_Dol();
xx.add_user(kareem);
ArrayList<User> users = xx.show_all();

System.out.printf("%-5s %-20s %-15s %-25s %-15s %-20s %-15s%n",
        "ID", "Username", "Role", "Email", "Password", "Address", "Phone");
System.out.println("--------------------------------------------------------------------------------------------");

for (User u : users) {
    System.out.printf("%-5d %-20s %-15s %-25s %-15s %-20s %-15s%n",
            u.getId(), u.getUsername(), u.getRole(), u.getEmail(),
            u.getPassword(), u.getAdd(), u.getPhone());
}
 
    }
    
}
