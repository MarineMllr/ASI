package com.sp.dto;

public class MoneyExchangeDTO {
    private int idUser;
    private int money;

    public MoneyExchangeDTO(int idUser, int money) {
        this.idUser = idUser;
        this.money = money;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
