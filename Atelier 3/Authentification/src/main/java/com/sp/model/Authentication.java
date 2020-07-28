package com.sp.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Authentication")
public class Authentication {

    @Id
    @GeneratedValue
    private int id;
    private String token;

    public Authentication(){

    }

    public Authentication(String token){
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
