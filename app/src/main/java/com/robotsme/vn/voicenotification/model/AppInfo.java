package com.robotsme.vn.voicenotification.model;

import android.graphics.drawable.Drawable;

public class AppInfo {
    public String name;
    public String packageName;
    public Drawable icon;

    /**
     *
     * @param name name
     * @param packageName package name
     * @param icon package icon
     */
    public AppInfo(String name, String packageName, Drawable icon) {
        this.name = name;
        this.packageName = packageName;
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Appinfo: " + name + " - " + packageName;
    }
}