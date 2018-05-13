package com.herokuapp.money_time.moneytime.retrofit_api.models;

import android.location.Location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExpenseModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("category")
    @Expose
    private ExpenseCategoryModel category;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("creation_location")
    @Expose
    private Object creationLocation;

    /**
     * No args constructor for use in serialization
     *
     */
    public ExpenseModel() {
    }

    /**
     *
     * @param amount
     * @param id
     * @param category
     * @param creationLocation
     * @param created
     * @param location
     */
    public ExpenseModel(Integer id, ExpenseCategoryModel category, Location location, String amount, String created, Object creationLocation) {
        super();
        this.id = id;
        this.category = category;
        this.location = location;
        this.amount = amount;
        this.created = created;
        this.creationLocation = creationLocation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ExpenseCategoryModel getCategory() {
        return category;
    }

    public void setCategory(ExpenseCategoryModel category) {
        this.category = category;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Object getCreationLocation() {
        return creationLocation;
    }

    public void setCreationLocation(Object creationLocation) {
        this.creationLocation = creationLocation;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("ID: " + this.id + "\nAmount: " +
                this.amount + "\nCreated: " + this.created + "\nCategory: " + this.category + "\n");
        return str.toString();
//        return new ToStringBuilder(this).append("id", id).append("amount", amount)
// .append("created", created).append("creationLocation", creationLocation)
// .append("user", user).append("category", category).append("location", location)
// .toString();
    }

}
