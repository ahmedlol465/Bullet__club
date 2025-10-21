/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bullet_club.entity;

public class User {
private   int id;
private String username;
private String role ;
private String password;
private String add;
private String email;
private String phone;

    public User(int id, String username, String role, String password, String add, String email, String phone) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.password = password;
        this.add = add;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

void login(String user,String pass){
   /* try{
    }catch(){
        
        

}*/
}
void logout(){
   /* try{
    }catch(){
        
        

}*/
}

void change_password(String user,String pass){
   /* try{
    }catch(){
        
        

}*/
}



}
