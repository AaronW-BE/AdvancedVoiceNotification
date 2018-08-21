package com.robotsme.vn.voicenotification.ui;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.robotsme.vn.voicenotification.ListeningAppManager;
import com.robotsme.vn.voicenotification.R;
import com.robotsme.vn.voicenotification.adapter.AppListAdapter;
import com.robotsme.vn.voicenotification.model.AppInfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddAppActivity extends AppCompatActivity {
//    private ProgressDialog progressDialog;

    SharedPreferences.Editor edit;
    SharedPreferences preference;

    Set<String> apps;

    ListView appListView;

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_app);

        preference = getSharedPreferences("apps",MODE_PRIVATE);
        edit = preference.edit();

        apps = preference.getStringSet("apps", null);

        List<AppInfo> appInfoList = new ArrayList<AppInfo>();
        for (String appName : apps) {
            appInfoList.add(new AppInfo(appName, appName, null));
        }

        appListView = findViewById(R.id.app_list);
        AppListAdapter appListAdapter = new AppListAdapter(this, R.layout.app_item, appInfoList);
        appListView.setAdapter(appListAdapter);

//        progressDialog = new ProgressDialog(this);
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        progressDialog.setCancelable(false);

    }

    private void refreshListView() {
        List<AppInfo> appInfoList = new ArrayList<AppInfo>();
        for (String appName : apps) {
            appInfoList.add(new AppInfo(appName, appName, null));
        }
        AppListAdapter appListAdapter = new AppListAdapter(this, R.layout.app_item, appInfoList);
        appListView.setAdapter(appListAdapter);
    }

    public void addApp(View view) throws InterruptedException {
//        progressDialog.show();

        final ArrayList<AppInfo> apps = ListeningAppManager.getSystemAppList(this);

        String[] appArr = new String[apps.size()];
        boolean[] appSelected = new boolean[apps.size()];
        for (int i = 0; i<appArr.length; i++) {
            appArr[i] = apps.get(i).name;

            boolean caught = false;
            for (String appPackageName : this.apps) {
                if (appPackageName.equals(apps.get(i).packageName)) {
                    caught = true;
                    break;
                }
            }

            appSelected[i] = caught;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("选择应用");
        builder.setMultiChoiceItems(appArr, appSelected, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                String packageName = apps.get(which).packageName;
                if (isChecked) {
                    addListenedApp(packageName);
                } else {
                    removeListenedApp(packageName);
                }
            }
        });

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                refreshListView();

                dialog.dismiss();
            }
        });

//        progressDialog.hide();
        builder.create().show();
    }


    public void addListenedApp(String packageName) {
        Set<String> apps = preference.getStringSet("apps", null);

        if (apps == null) {
            apps = new HashSet<String>();
        }

        if (!apps.contains(packageName)) {
            apps.add(packageName);
        }

        edit.putStringSet("apps", apps);
        edit.commit();
    }

    public void removeListenedApp(String packageName) {
        Set<String> apps = preference.getStringSet("apps", null);
        if (apps == null) {
            apps = new HashSet<String>();
        }
        if (apps.contains(packageName)){
            apps.remove(packageName);
        }

        edit.putStringSet("apps", apps);
        edit.commit();
    }

}
