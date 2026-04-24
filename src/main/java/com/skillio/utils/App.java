package com.skillio.utils;

import java.io.IOException;

public class App {

    private static final String filepath = "./src/main/resources/app.properties";

    private App() {
    }

    public static String getBrowserName() {
        PropUtil prop = new PropUtil();
        String browserName = null;

        try {
            browserName = prop.getProperty(filepath, "browser");  // ✔ FIXED
        } catch (IOException e) {
            e.printStackTrace();
        }

        return browserName;
    }

    public static String getappUrl(String env) {
        PropUtil prop = new PropUtil();
        String appUrl = null;

        try {
            appUrl = prop.getProperty(filepath, "app." + env + ".url"); // ✔ FIXED
        } catch (IOException e) {
            e.printStackTrace();
        }

        return appUrl;
    }

    public static String getUsername(String env) {
        PropUtil prop = new PropUtil();
        String username = null;

        try {
            username = prop.getProperty(filepath, "app." + env + ".username"); // ✔ FIXED
        } catch (IOException e) {
            e.printStackTrace();
        }

        return username;
    }

    // New helper to read password from properties (app.<env>.password)
    public static String getPassword(String env) {
        PropUtil prop = new PropUtil();
        String password = null;
        try {
            password = prop.getProperty(filepath, "app." + env + ".password");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return password;
    }
}