package com.gebril.yamen.pff.activities.Model;

import com.google.gson.annotations.SerializedName;

public class Player {

    @SerializedName("id")
    private int id;
    @SerializedName("email")
    private String email;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("username")
    private String username;
    @SerializedName("api_key")
    private String API_KEY;
    @SerializedName("password")
    private String password;
    @SerializedName("height")
    private String height;
    @SerializedName("gender")
    private String gender;
    @SerializedName("age")
    private String age;
    @SerializedName("city")
    private String city;
    @SerializedName("favorite_club")
    private String favorite_club;
    @SerializedName("birth_date")
    private String birthdate;
    @SerializedName("country")
    private String country;
    @SerializedName("position")
    private String position;
    @SerializedName("style")
    private String style;
    @SerializedName("preferred_foot")
    private String preferred_foot;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getPreferred_foot() {
        return preferred_foot;
    }

    public void setPreferred_foot(String preferred_foot) {
        this.preferred_foot = preferred_foot;
    }

    public String getAPI_KEY() {
        return API_KEY;
    }

    public void setAPI_KEY(String API_KEY) {
        this.API_KEY = API_KEY;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getFavorite_club() {
        return favorite_club;
    }

    public void setFavorite_club(String favorite_club) {
        this.favorite_club = favorite_club;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApi_key() {
        return API_KEY;
    }

    public void setApi_key(String api_key) {
        this.API_KEY = api_key;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }



    public String showPlayerInfo()
    {
        StringBuilder builder = new StringBuilder();

        builder.append("First Name: ");
        builder.append(getFirstName());
        builder.append(System.lineSeparator());
        builder.append("Last Name: ");
        builder.append(getLastName());
        builder.append(System.lineSeparator());
        builder.append("Username: ");
        builder.append(getUsername());
        builder.append(System.lineSeparator());
        builder.append("Email: ");
        builder.append(getEmail());
        builder.append(System.lineSeparator());
        builder.append("Password: ");
        builder.append(getPassword());
        builder.append(System.lineSeparator());
        builder.append("County: ");
        builder.append(getCountry());
        builder.append(System.lineSeparator());
        builder.append("City: ");
        builder.append(getCity());
        builder.append(System.lineSeparator());
        builder.append("Birthdate: ");
        builder.append(getBirthdate());
        builder.append(System.lineSeparator());
        builder.append("Favourite Club: ");
        builder.append(getFavorite_club());
        builder.append(System.lineSeparator());
        builder.append("Height: ");
        builder.append(getHeight());
        builder.append(System.lineSeparator());
        builder.append("Preferred Foot: ");
        builder.append(getPreferred_foot());
        builder.append(System.lineSeparator());
        builder.append("Position: ");
        builder.append(getPosition());
        builder.append(System.lineSeparator());
        builder.append("Style: ");
        builder.append(getStyle());
        builder.append(System.lineSeparator());

        return builder.toString();




    }
}
