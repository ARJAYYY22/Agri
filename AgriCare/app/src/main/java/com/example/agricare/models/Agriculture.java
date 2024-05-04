package com.example.agricare.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Agriculture implements Serializable {

    public static final String PALAY = "palay";
    public static final String TUBO = "tubo";
    public static final String MAIS = "mais";
    public static final String CHICKEN = "chicken";
    public static final String PIG = "pig";
    public static final String COW = "cow";
    public static final String GOAT = "goat";
    public static final String TILAPIA = "tilapia";
    public static final String HITO = "hito";
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("prevention")
    @Expose
    private String prevention;

    public String getPrevention() {
        return prevention;
    }

    public void setPrevention(String prevention) {
        this.prevention = prevention;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
