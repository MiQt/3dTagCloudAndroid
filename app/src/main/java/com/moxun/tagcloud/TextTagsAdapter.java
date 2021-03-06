package com.moxun.tagcloud;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.moxun.tagcloudlib.view.TagsAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by moxun on 16/1/19.
 */
public class TextTagsAdapter extends TagsAdapter {

    private List<String> dataSet = new ArrayList<>();
    private ViewGroup.LayoutParams parm = new ViewGroup.LayoutParams(18, 18);

    public TextTagsAdapter(@NonNull String... data) {
        dataSet.clear();
        Collections.addAll(dataSet, data);
    }

    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public View getView(final Context context, final int i, ViewGroup parent) {
        TextView view = new TextView(context);
        view.setLayoutParams(parm);
        view.setText(i + "");
        view.setTextColor(Color.parseColor("#FFFFFF"));
        view.setTextSize(5);
        if (i < 15) {
            view.setBackgroundColor(Color.RED);
        } else if (i < 15 + 20) {
            view.setBackgroundColor(Color.LTGRAY);
        } else if (i < 15 + 20 + 30) {
            view.setBackgroundColor(Color.GRAY);
        } else if (i < 15 + 20 + 30 + 30) {
            view.setBackgroundColor(Color.GREEN);
        } else if (i < 15 + 20 + 30 + 30 + 20) {
            view.setBackgroundColor(Color.BLUE);
        } else if (i < 15 + 20 + 30 + 30 + 20 + 15) {
            view.setBackgroundColor(Color.YELLOW);
        }
        return view;
    }

    @Override
    public Object getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public int getPopularity(int position) {
        return 0;
    }

    @Override
    public void onThemeColorChanged(View view, int themeColor) {
        //view.setBackgroundColor(themeColor);
    }
}