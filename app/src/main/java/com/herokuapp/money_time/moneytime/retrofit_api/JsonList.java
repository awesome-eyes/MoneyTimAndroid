package com.herokuapp.money_time.moneytime.retrofit_api;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
//import org.apache.commons.lang.builder.ToStringBuilder;

public class JsonList<Model> {

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
    private List<Model> results = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public JsonList() {
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

    public List<Model> getResults() {
        return results;
    }

    public void setUsers(List<Model> results) {
        this.results = results;
    }

//    @Override
//    public String toString() {
//        return new ToStringBuilder(this).append("count", count).append("next", next).append("previous", previous).append("results", results).toString();
//    }

}