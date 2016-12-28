package com.moxun.tagcloud;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.moxun.tagcloudlib.view.TagCloudView;

import java.sql.Time;

public class MainActivity extends AppCompatActivity {

    private TagCloudView tagCloudView;
    private TextTagsAdapter textTagsAdapter;
    private SensorManager manager;
    private Sensor sensor;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tagCloudView = (TagCloudView) findViewById(R.id.tag_cloud);
        tagCloudView.setBackgroundColor(Color.LTGRAY);
        tagCloudView.setAutoScrollMode(TagCloudView.MODE_DISABLE);
        textTagsAdapter = new TextTagsAdapter(new String[130]);

        tagCloudView.setAdapter(textTagsAdapter);

        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = manager.getDefaultSensor(Sensor.TYPE_ORIENTATION);


    }

    float lastx;
    float lasty;
    float lastz;

    float currx;
    float curry;
    float currz;
    SensorEventListener mSensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (System.currentTimeMillis() - lasttime < 500) {
                return;
            }
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            tagCloudView.setRotation(90 - curry, 0, 0,
                    false);
            tagCloudView.setRotation(0, x - lastx, 0,
                    true);
            tagCloudView.setRotation(curry - 90, 0, 0,
                    false);

            tagCloudView.setRotation(y - lasty, 0, 0,
                    true);
            curry += y - lasty;
            currz += z - lastz;
            currx += x - lastx;
            lastx = x;
            lasty = y;
            lastz = z;
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
    long lasttime;

    @Override
    protected void onResume() {
        super.onResume();
        manager.registerListener(mSensorEventListener, sensor, SensorManager.SENSOR_DELAY_UI);
        lasttime = System.currentTimeMillis();
    }

    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterListener(mSensorEventListener);
    }
}
