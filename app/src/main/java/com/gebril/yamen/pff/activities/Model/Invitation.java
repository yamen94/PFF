package com.gebril.yamen.pff.activities.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yegebril on 9/7/2018.
 */

public class Invitation {

    @SerializedName("inv_type")
    private int invitation_type;
    @SerializedName("inv_image")
    private int invitaiton_image;
    @SerializedName("inv_body")
    private String invitation_body;
    @SerializedName("inv_creation_date")
    private String creation_date;

    public int getInvitation_type() {
        return invitation_type;
    }

    public void setInvitation_type(int invitation_type) {
        this.invitation_type = invitation_type;
    }

    public int getInvitaiton_image() {
        return invitaiton_image;
    }

    public void setInvitaiton_image(int invitaiton_image) {
        this.invitaiton_image = invitaiton_image;
    }

    public String getInvitation_body() {
        return invitation_body;
    }

    public void setInvitation_body(String invitation_body) {
        this.invitation_body = invitation_body;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }
}
