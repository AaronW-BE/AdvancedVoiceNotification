package com.robotsme.vn.voicenotification.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.robotsme.vn.voicenotification.R;
import com.robotsme.vn.voicenotification.model.AppInfo;

import java.util.List;

public class AppListAdapter extends ArrayAdapter {
    private final int resourceId;
    public AppListAdapter(Context context, int textViewResourceId, List<AppInfo> objects) {
        super(context, textViewResourceId, objects);
        this.resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        AppInfo appInfo = (AppInfo) getItem(position);
        @SuppressLint("ViewHolder") View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
//        ImageView appIcon = view.findViewById(R.id.app_icon);
        TextView appName = view.findViewById(R.id.app_name);

        assert appInfo != null;
//        appIcon.setImageDrawable(appInfo.icon);
        appName.setText(appInfo.name);
        return view;
    }
}
