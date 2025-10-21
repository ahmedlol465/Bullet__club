/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bullet_club.entity;

import java.time.LocalDate;

/**
 *
 * @author ENG abdelrahman nagi
 */
public class Notfication {
private int id;
private String massage;
private LocalDate date;
private Product item;

    public Notfication(int id, String massage, LocalDate date, Product item) {
        this.id = id;
        this.massage = massage;
        this.date = date;
        this.item = item;
    }

   
    public String genarete() {
        return "Notfication{" + "id=" + id + ", massage=" + massage + ", date=" + date + ", item=" + item + '}';
    }


}
