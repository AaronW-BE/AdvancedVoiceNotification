package com.robotsme.vn.voicenotification;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;

import com.robotsme.vn.voicenotification.model.AppInfo;

import java.util.ArrayList;
import java.util.List;

public class ListeningAppManager {

    private static ListeningAppManager instance = null;

    private ListeningAppManager() {

    }

    public static ListeningAppManager getInstance() {
        if (instance == null) {
            return new ListeningAppManager();
        }
        return instance;
    }

    public boolean addApp() {
        return true;
    }

    public ArrayList getListeningApp() {
        return null;
    }

    public static ArrayList<AppInfo> getSystemAppList(Context context) {

        ArrayList<AppInfo> appList = new ArrayList<AppInfo>();
        List<PackageInfo> packages = context.getPackageManager().getInstalledPackages(0);
        for (int i = 0; i< packages.size(); i++) {
            PackageInfo packageInfo = packages.get(i);
            String name = packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString();
            Drawable icon = packageInfo.applicationInfo.loadIcon(context.getPackageManager());
            AppInfo appInfo = new AppInfo(name, packageInfo.packageName, icon);
            appList.add(appInfo);
        }
        return appList;
    }
}

