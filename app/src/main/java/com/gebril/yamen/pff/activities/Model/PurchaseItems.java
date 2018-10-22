package com.gebril.yamen.pff.activities.Model;


public class PurchaseItems {

    private int image;
    private String tittle;
    private int price;
    private int unit;

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public PurchaseItems(int image, String tittle, int price) {
        this.image = image;
        this.tittle = tittle;
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
