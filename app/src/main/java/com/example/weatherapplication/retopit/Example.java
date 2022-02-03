package com.example.weatherapplication.retopit;

import com.google.gson.annotations.SerializedName;

public class Example {

    @SerializedName("main")
    private Main main;


    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
