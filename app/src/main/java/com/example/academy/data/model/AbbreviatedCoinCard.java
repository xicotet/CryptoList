package com.example.academy.data.model;

//We use Abbreviated and Concise word indistinctly

public class AbbreviatedCoinCard {
    private String name;
    private String nameAbbreviation;
    private boolean isFavorite;

    private String symbolUrl;

    public AbbreviatedCoinCard(String name, String nameAbbreviation){
        this.name = name;
        this.nameAbbreviation = nameAbbreviation;
        isFavorite = false;
        this.symbolUrl = CoinCard.generateURL(name, nameAbbreviation);
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

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getSymbolUrl() {
        return symbolUrl;
    }

    public void setSymbolUrl(String symbolUrl) {
        this.symbolUrl = symbolUrl;
    }
}
