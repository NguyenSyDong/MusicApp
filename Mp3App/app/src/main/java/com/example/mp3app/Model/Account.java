package com.example.mp3app.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Account {

@SerializedName("IdAccount")
@Expose
private String idAccount;
@SerializedName("Username")
@Expose
private String username;
@SerializedName("Password")
@Expose
private String password;

public String getIdAccount() {
return idAccount;
}

public void setIdAccount(String idAccount) {
this.idAccount = idAccount;
}

public String getUsername() {
return username;
}

public void setUsername(String username) {
this.username = username;
}

public String getPassword() {
return password;
}

public void setPassword(String password) {
this.password = password;
}

}