package com.sp.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String surname;
    private String password;

//    @OneToMany(mappedBy = "idUser", targetEntity = AcquiredCard.class)
//    private List<AcquiredCard> acquiredCards;

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    private int money;

    public User(){

    }

    public User(String name, String surname, String password) {
        super();
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.money = 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", password='" + password + '\'' +
//                ", acquiredCards=" + acquiredCards +
                ", money=" + money +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public List<AcquiredCard> getAcquiredCards() {
//        return acquiredCards;
//    }
//
//    public void setAcquiredCards(List<AcquiredCard> acquiredCards) {
//        this.acquiredCards = acquiredCards;
//    }
}
