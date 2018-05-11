package com.herokuapp.money_time.moneytime.retrofit_api;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
//import org.apache.commons.lang.builder.ToStringBuilder;

public class UserModel {

    @SerializedName("pk")
    @Expose
    private Integer pk;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("last_login")
    @Expose
    private String lastLogin;

    /**
     * No args constructor for use in serialization
     *
     */
    public UserModel() {
    }

    /**
     *
     * @param lastName
     * @param lastLogin
     * @param username
     * @param email
     * @param firstName
     * @param pk
     */
    public UserModel(Integer pk, String username, String email, String firstName, String lastName, String lastLogin) {
        super();
        this.pk = pk;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastLogin = lastLogin;
    }

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

//    @Override
//    public String toString() {
//        return new ToStringBuilder(this).append("pk", pk).append("username", username).append("email", email).append("firstName", firstName).append("lastName", lastName).append("lastLogin", lastLogin).toString();
//    }

}