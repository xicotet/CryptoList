package com.example.academy.ui.data;

public class CoinCard {
    private String name;
    private String nameAbbreviation;
    private String price;
    private String variation;
    private String imageSource;

    public CoinCard(String name, String nameAbbreviation, String price, String variation) {
        this.name = name;
        this.nameAbbreviation = nameAbbreviation;
        this.price = price;
        this.variation = variation;
        //this.imageSource = imageSource;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getVariation() {
        return variation;
    }

    public void setVariation(String variation) {
        this.variation = variation;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }
}
