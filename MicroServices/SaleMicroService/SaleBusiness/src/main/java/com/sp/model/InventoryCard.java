package com.sp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class InventoryCard {
    @Id
    @GeneratedValue
    private int id;

    // Card Attributes
    private int idCard;

    // User Attributes
    private int idUser;

    public InventoryCard() {
    }

    public InventoryCard(int id, int idCard, int idUser) {
        this.id = id;
        this.idCard = idCard;
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCard() {
        return idCard;
    }

    public void setIdCard(int idCard) {
        this.idCard = idCard;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
