package com.covidfight.model;

import com.google.gson.annotations.SerializedName;

public class Info {

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("name")
    private String name;

    @SerializedName("city")
    private String city;

    @SerializedName("phone")
    private String phone;

    @SerializedName("address")
    private String address;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
