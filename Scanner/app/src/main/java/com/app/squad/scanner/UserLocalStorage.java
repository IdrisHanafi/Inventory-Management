package com.app.squad.scanner;

import android.content.Context;
import android.content.SharedPreferences;

public class UserLocalStorage {

    public static final String SP_NAME = "userdetails";
    SharedPreferences userLocalDatabase;

    public UserLocalStorage(Context context){
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserData(User user){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("name", user.name);
        spEditor.putString("username", user.username);
        spEditor.putString("password", user.password);
        spEditor.commit();
    }

    public boolean getUserLoggedIn(){
        if (userLocalDatabase.getBoolean("LoggedIn", false)){
            return true;
        }else {
            return false;
        }
    }

    public User getLoggedInUser(){
        String name = userLocalDatabase.getString("name", "");
        int age = userLocalDatabase.getInt("age", -1);
        String username = userLocalDatabase.getString("username", "");
        String password = userLocalDatabase.getString("password", "");
        User storedUser = new User(name, username, password);

        return storedUser;
    }

    public void setUserLoggedIn (boolean loggedIn){
        SharedPreferences.Editor spEditor=userLocalDatabase.edit();
        spEditor.putBoolean("LoggedIn", loggedIn);
        spEditor.commit();
    }

    public void clearUserData(){
        SharedPreferences.Editor spEditor=userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }
}
