package com.herokuapp.money_time.moneytime.retrofit_api;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
//import org.apache.commons.lang.builder.ToStringBuilder;

public class JsonList {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("next")
    @Expose
    private Object next;
    @SerializedName("previous")
    @Expose
    private Object previous;
    @SerializedName("results")
    @Expose
    private List<UserModel> results = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public JsonList() {
    }

    /**
     *
     * @param results
     * @param previous
     * @param count
     * @param next
     */
    public JsonList(Integer count, Object next, Object previous, List<UserModel> results) {
        super();
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Object getNext() {
        return next;
    }

    public void setNext(Object next) {
        this.next = next;
    }

    public Object getPrevious() {
        return previous;
    }

    public void setPrevious(Object previous) {
        this.previous = previous;
    }

    public List<UserModel> getUsers() {
        return results;
    }

    public void setUsers(List<UserModel> results) {
        this.results = results;
    }

//    @Override
//    public String toString() {
//        return new ToStringBuilder(this).append("count", count).append("next", next).append("previous", previous).append("results", results).toString();
//    }

}