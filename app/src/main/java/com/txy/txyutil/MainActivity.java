package com.txy.txyutil;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.txy.txyutils.ScreenUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView ivShot = findViewById(R.id.iv_shot);
        findViewById(R.id.btn_shot_screen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = ScreenUtils.screenShot(MainActivity.this);
                ivShot.setImageBitmap(bitmap);
            }
        });
        findViewById(R.id.btn_shot_screen_without_statusbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = ScreenUtils.screenShot(MainActivity.this,true);
                ivShot.setImageBitmap(bitmap);
            }
        });
    }
}
