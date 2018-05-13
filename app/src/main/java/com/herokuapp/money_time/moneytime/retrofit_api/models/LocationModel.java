package com.herokuapp.money_time.moneytime.retrofit_api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocationModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("category")
    @Expose
    private LocationCategoryModel category;
    @SerializedName("name")
    @Expose
    private Object name;
    @SerializedName("location")
    @Expose
    private String location;

    /**
     * No args constructor for use in serialization
     *
     */
    public LocationModel() {
    }

    /**
     *
     * @param id
     * @param category
     * @param location
     * @param name
     */
    public LocationModel(Integer id, LocationCategoryModel category, Object name, String location) {
        super();
        this.id = id;
        this.category = category;
        this.name = name;
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocationCategoryModel getCategory() {
        return category;
    }

    public void setCategory(LocationCategoryModel category) {
        this.category = category;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("ID: " + this.id + "\nName: " +
                this.name + "\nCategory: " + this.category + "\nLocation: " + this.location + "\n");
        return str.toString();
    }

}