package com.example.academy.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class CoinCard {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String nameAbbreviation;
    private double price;
    private double variation;

    private String symbolUrl;

    public CoinCard(){
        //Constructor vacio necesario supuestamente para Room
    }
    public CoinCard(String name, String nameAbbreviation, double price, double variation) {
        this.name = name;
        this.nameAbbreviation = nameAbbreviation;
        this.price = price;
        this.variation = variation;
        this.symbolUrl = generateURL(name, nameAbbreviation);

    }

    /* Following method returns an URL which, eventually, will be used to connect to an
       API that serves crypto coins logos (since CoinCap API doesen't
       supply that resource) and it will be rendered using Picasso.
     */
    public static String generateURL(String coinName, String coinNameAbbreviation){
        StringBuilder urlBuilder = new StringBuilder("https://cryptologos.cc/logos/");
        String path = coinName + " " + coinNameAbbreviation;
        path = path.replace(' ', '-').toLowerCase();
        urlBuilder.append(path).append("-logo.png?v=024");
        return urlBuilder.toString();
    }

    public String getSymbolUrl(){
        return symbolUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameAbbreviation() {
        return nameAbbreviation;
    }

    public void setNameAbbreviation(String nameAbbreviation) {
        this.nameAbbreviation = nameAbbreviation;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getVariation() {
        return variation;
    }

    public void setVariation(double variation) {
        this.variation = variation;
    }

    @Override
    public String toString() {
        return "CoinCard{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nameAbbreviation='" + nameAbbreviation + '\'' +
                ", price=" + price +
                ", variation=" + variation +
                ", symbolUrl='" + symbolUrl + '\'' +
                '}';
    }
}
