package com.robotsme.vn.voicenotification.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.robotsme.vn.voicenotification.ListeningAppManager;
import com.robotsme.vn.voicenotification.R;
import com.robotsme.vn.voicenotification.model.AppInfo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void manageApp(View view) {
        Intent intent = new Intent(this, AddAppActivity.class);
        startActivity(intent);
    }
}
