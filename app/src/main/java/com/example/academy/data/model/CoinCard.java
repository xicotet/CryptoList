package com.example.academy.data.model;

public class CoinCard {
    private String name;
    private String nameAbbreviation;
    private double price;
    private double variation;

    private String url;

    public CoinCard(String name, String nameAbbreviation, double price, double variation) {
        this.name = name;
        this.nameAbbreviation = nameAbbreviation;
        this.price = price;
        this.variation = variation;
        this.url = generateURL(name, nameAbbreviation);

    }

    private String generateURL(String coinName, String coinNameAbbreviation){
        StringBuilder urlBuilder = new StringBuilder("https://cryptologos.cc/logos/");
        String path = coinName + " " + coinNameAbbreviation;
        path = path.replace(' ', '-').toLowerCase();
        urlBuilder.append(path).append("-logo.png?v=024");
        return urlBuilder.toString();
    }

    public String getUrl(){
        return url;
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

}
