package com.example.recyclerviewex;

public class UserModel {
    int img;
    String name;
    String number;
    public UserModel(int image,String name,String number){
        this.img = image;
        this.name = name;
        this.number = number;
    }


    public int getImage() {
        return img;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }
}
