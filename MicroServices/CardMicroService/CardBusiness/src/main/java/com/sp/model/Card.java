package com.sp.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Card {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String description;
    private String family;
    private int hp;
    private int energy;
    private int defence;
    private int attack;
    private String imgUrl;
    private int price;

//	@OneToMany(mappedBy = "idCard")
//	private List<AcquiredCard> children;

    public Card() {

    }

    public Card(String name, String description, String family, int hp, int energy, int defence, int attack, String imgUrl, int price) {
        this.name = name;
        this.description = description;
        this.family = family;
        this.hp = hp;
        this.energy = energy;
        this.defence = defence;
        this.attack = attack;
        this.imgUrl = imgUrl;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Card{" +
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
                '}';
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
//
//	public List<AcquiredCard> getChildren() {		return children;
//	}
//
//	public void setChildren(List<AcquiredCard> children) {
//		this.children = children;
//	}
}
