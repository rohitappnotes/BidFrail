package com.bidfrail.android;

import static android.view.View.LAYER_TYPE_SOFTWARE;

import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_test);
        ImageView imageView =findViewById(R.id.imageView);
        Uri gifUri = Uri.parse("https://cdn.dribbble.com/users/263558/screenshots/1337078/dvsd.gif");

        Glide.with(this)
                .asGif()
                .load(R.drawable.splash_new)
                .centerCrop()
                .into(imageView);
    }
}