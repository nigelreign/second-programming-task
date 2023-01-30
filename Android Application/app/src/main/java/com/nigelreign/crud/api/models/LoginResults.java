package com.nigelreign.crud.api.models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResults {

    @SerializedName("access")
    @Expose
    private String access;

    @SerializedName("refresh")
    @Expose
    private String refresh;

    public String getAccessToken() {
        return access;
    }
    public void setAccessToken(String access_token) {
        this.access = access;
    }

    public String getRefreshToken() {
        return refresh;
    }

    public void setRefreshToken(String refresh_token) {
        this.refresh = refresh;
    }

}