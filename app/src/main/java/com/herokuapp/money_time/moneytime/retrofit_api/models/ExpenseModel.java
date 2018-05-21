package com.herokuapp.money_time.moneytime.retrofit_api.models;

import android.location.Location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExpenseModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("category")
    @Expose
    private ExpenseCategoryModel category;
    @SerializedName("location")
    @Expose
    private LocationModel location;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("creation_location")
    @Expose
    private Object creationLocation;

    public ExpenseModel() {
    }

    public ExpenseModel(Integer id, ExpenseCategoryModel category, LocationModel location, String amount, String created, Object creationLocation) {
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

    public LocationModel getLocation() {
        return location;
    }

    public void setLocation(LocationModel location) {
        this.location = location;
    }

    public String getAmount() {
        return amount;
    }

    public String getFixedAmount() {
        return String.format("%.2f", new Float(amount));
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCreated() throws ParseException {
        String toChange = created.replace('T', ' ').replace('Z', ' ');
//        Date d = new Date(created);
        Date d = new SimpleDateFormat("yyyy-mm-dd HH:MM:SS").parse(toChange);
        SimpleDateFormat formatter = new SimpleDateFormat("HH:MM dd/mm");
        return  formatter.format(d);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//        return outputFormat.format(sdf.parse(created));
//        return sdf.toPattern("HH:mm dd.mm");
//        String formattedString = String.valueOf(android.text.format.DateFormat.format("dd-MM-yyyy", sdf));
//        return created;
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
    }
}
