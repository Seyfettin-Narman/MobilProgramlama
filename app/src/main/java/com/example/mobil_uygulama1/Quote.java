package com.example.mobil_uygulama1;

import com.google.gson.annotations.SerializedName;

public class Quote {
    @SerializedName("body")
    private String body;
    public String getBody(){return body;}
}
