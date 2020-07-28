package com.cpe.springboot.inventaire.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class InventoryCard {
    @Id
    @GeneratedValue
    private int id;

    // Card Attributes
    private String name;
    private String description;
    private String family;
    private int hp;
    private int energy;
    private int defence;
    private int attack;
    private String imgUrl;
    private int price;

    // User Attributes
    private int idUser;
    private String userName;
    private String userSurname;

    public InventoryCard() {
    }

    public InventoryCard(String name, String description, String family, int hp, int energy, int defence, int attack, String imgUrl, int price, int idUser, String userName, String userSurname) {
        this.name = name;
        this.description = description;
        this.family = family;
        this.hp = hp;
        this.energy = energy;
        this.defence = defence;
        this.attack = attack;
        this.imgUrl = imgUrl;
        this.price = price;
        this.idUser = idUser;
        this.userName = userName;
        this.userSurname = userSurname;
    }

    @Override
    public String toString() {
        return "InventoryCard{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", family='" + family + '\'' +
                ", hp=" + hp +
                ", energy=" + energy +
                ", defence=" + defence +
                ", attack=" + attack +
                ", imgUrl='" + imgUrl + '\'' +
                ", price=" + price +
                ", idUser=" + idUser +
                ", userName='" + userName + '\'' +
                ", userSurname='" + userSurname + '\'' +
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }
}
