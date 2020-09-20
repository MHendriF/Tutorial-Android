package com.taimoorsikander.cityguideapp.databases;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {

    SharedPreferences userSession;
    SharedPreferences.Editor editor;
    Context context;

    public static final String SESSION_USER = "userLoginSession";
    public static final String SESSION_REMEMBER_ME = "rememberMe";

    //user session
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_FULLNAME = "fullName";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_DATE = "date";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_PHONE_NUMBER = "phoneNumber";

    //remember me session
    private static final String IS_REMEMBER_ME = "IsLoggedIn";
    public static final String KEY_SESSION_PHONE_NUMBER = "phoneNumber";
    public static final String KEY_SESSION_PASSWORD = "password";

    public SessionManager(Context _context, String sessionName) {
        context = _context;
        userSession = context.getSharedPreferences(sessionName, Context.MODE_PRIVATE);
        editor = userSession.edit();
    }

    public void createLoginSession(String fullName, String username, String email, String password, String date, String gender, String phoneNumber) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_FULLNAME, fullName);
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PASSWORD, password);
        editor.putString(KEY_DATE, date);
        editor.putString(KEY_GENDER, gender);
        editor.putString(KEY_PHONE_NUMBER, phoneNumber);
        editor.commit();
    }

    public HashMap<String, String> getUserDetailFromSession() {
        HashMap<String, String> userData = new HashMap<>();
        userData.put(KEY_FULLNAME, userSession.getString(KEY_FULLNAME, null));
        userData.put(KEY_USERNAME, userSession.getString(KEY_USERNAME, null));
        userData.put(KEY_EMAIL, userSession.getString(KEY_EMAIL, null));
        userData.put(KEY_PASSWORD, userSession.getString(KEY_PASSWORD, null));
        userData.put(KEY_DATE, userSession.getString(KEY_DATE, null));
        userData.put(KEY_GENDER, userSession.getString(KEY_GENDER, null));
        userData.put(KEY_PHONE_NUMBER, userSession.getString(KEY_PHONE_NUMBER, null));
        return userData;
    }

    public boolean checkLogin() {
        if (userSession.getBoolean(IS_LOGIN, true)){
            return true;
        }else {
            return false;
        }
    }

    public void logoutUser() {
        editor.clear();
        editor.commit();
    }

    public void createRememberMeSession(String phoneNumber, String password) {
        editor.putBoolean(IS_REMEMBER_ME, true);
        editor.putString(KEY_SESSION_PASSWORD, password);
        editor.putString(KEY_SESSION_PHONE_NUMBER, phoneNumber);
        editor.commit();
    }

    public HashMap<String, String> getRememberMeFromSession() {
        HashMap<String, String> userData = new HashMap<>();
        userData.put(KEY_SESSION_PASSWORD, userSession.getString(KEY_SESSION_PASSWORD, null));
        userData.put(KEY_SESSION_PHONE_NUMBER, userSession.getString(KEY_SESSION_PHONE_NUMBER, null));
        return userData;
    }

    public boolean checkRememberMe() {
        if (userSession.getBoolean(IS_REMEMBER_ME, true)){
            return true;
        }else {
            return false;
        }
    }
}
