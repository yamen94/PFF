package com.gebril.yamen.pff.activities.Model;

public class Search {

    private String header1, header2;
    private int image;

    public Search(String header1, String header2) {
        this.header1 = header1;
        this.header2 = header2;
    }

    public String getHeader1() {
        return header1;
    }

    public void setHeader1(String header1) {
        this.header1 = header1;
    }

    public String getHeader2() {
        return header2;
    }

    public void setHeader2(String header2) {
        this.header2 = header2;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
