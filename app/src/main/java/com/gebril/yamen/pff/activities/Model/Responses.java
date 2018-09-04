package com.gebril.yamen.pff.activities.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yegebril on 7/15/2018.
 */

public class Responses {

    @SerializedName("result")
    private int result ;

    @SerializedName("error")
    private boolean error;

    @SerializedName("message")
    private int message;

    @SerializedName("api_key")
    private int api_key;

    @SerializedName("user_id")
    private int user_id;

    public int getApi_key() {
        return api_key;
    }

    public void setApi_key(int api_key) {
        this.api_key = api_key;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public boolean getError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
