package com.gebril.yamen.pff.activities.Model;

import com.google.gson.annotations.SerializedName;



public class Match {


    @SerializedName("first_team_id")
    private int first_team_id;
    @SerializedName("second_team_id")
    private int second_team_id;
    @SerializedName("first_team_name")
    private String first_team_name;
    @SerializedName("second_team_name")
    private String second_team_name;
    @SerializedName("first_team_image")
    private int first_team_image;
    @SerializedName("second_team_image")
    private int Second_team_image;
    @SerializedName("first_team_score")
    private int first_team_score;
    @SerializedName("second_team_score")
    private int second_team_score;
    @SerializedName("match_type")
    private String match_type;

    public int getFirst_team_id() {
        return first_team_id;
    }

    public void setFirst_team_id(int first_team_id) {
        this.first_team_id = first_team_id;
    }

    public int getSecond_team_id() {
        return second_team_id;
    }

    public void setSecond_team_id(int second_team_id) {
        this.second_team_id = second_team_id;
    }

    public String getFirst_team_name() {
        return first_team_name;
    }

    public void setFirst_team_name(String first_team_name) {
        this.first_team_name = first_team_name;
    }

    public String getSecond_team_name() {
        return second_team_name;
    }

    public void setSecond_team_name(String second_team_name) {
        this.second_team_name = second_team_name;
    }

    public int getFirst_team_image() {
        return first_team_image;
    }

    public void setFirst_team_image(int first_team_image) {
        this.first_team_image = first_team_image;
    }

    public int getSecond_team_image() {
        return Second_team_image;
    }

    public void setSecond_team_image(int second_team_image) {
        Second_team_image = second_team_image;
    }

    public int getFirst_team_score() {
        return first_team_score;
    }

    public void setFirst_team_score(int first_team_score) {
        this.first_team_score = first_team_score;
    }

    public int getSecond_team_score() {
        return second_team_score;
    }

    public void setSecond_team_score(int second_team_score) {
        this.second_team_score = second_team_score;
    }

    public String getMatch_type() {
        return match_type;
    }

    public void setMatch_type(String match_type) {
        this.match_type = match_type;
    }
}
