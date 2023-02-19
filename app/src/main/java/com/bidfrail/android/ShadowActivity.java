package com.bidfrail.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bidfrail.android.shadow.ColorShadowDrawable;
import com.bidfrail.android.shadow.ShadowDrawable;

public class ShadowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shadow);

        TextView textView1 = findViewById(R.id.text1);
        TextView textView2 = findViewById(R.id.text2);
        TextView textView3 = findViewById(R.id.text3);
        TextView textView4 = findViewById(R.id.text4);

        ShadowDrawable.setShadowDrawable(textView1, Color.parseColor("#2979FF"), dpToPx(8),
                Color.parseColor("#992979FF"), dpToPx(10), 0, 0);

        ShadowDrawable.setShadowDrawable(textView2, new int[] {
                        Color.parseColor("#536DFE"), Color.parseColor("#7C4DFF")}, dpToPx(8),
                Color.parseColor("#997C4DFF"), dpToPx(6), dpToPx(3), dpToPx(3));

        ShadowDrawable.setShadowDrawable(textView3, ShadowDrawable.SHAPE_CIRCLE, Color.parseColor("#2979FF"),
                0, Color.parseColor("#aa536DFE"), dpToPx(10), 0, 0);

        ShadowDrawable.setShadowDrawable(textView4, ShadowDrawable.SHAPE_CIRCLE, Color.parseColor("#7C4DFF"),
                dpToPx(8), Color.parseColor("#992979FF"), dpToPx(6), dpToPx(3), dpToPx(3));



        LinearLayout layout = findViewById(R.id.linearLayout);
        /*  ViewCompat.setBackground(layout, getShadowBg(
                ColorShadowDrawable.ShadowCorner.ALL,  ColorShadowDrawable.ShadowSide.ALL
        ));*/

        ColorShadowDrawable textBg = getShadowBg( ColorShadowDrawable.ShadowCorner.ALL,  ColorShadowDrawable.ShadowSide.ALL, 10);
        ViewCompat.setBackground(layout, textBg);

        LinearLayout layout2 = findViewById(R.id.linearLayout2);
        ShadowDrawable.setShadowDrawable(layout2, Color.parseColor("#FFFFFF"), dpToPx(10),
                Color.parseColor("#DCDDE0"), dpToPx(8), 0, 0);
    }

    private int dpToPx(int dp) {
        return (int) (Resources.getSystem().getDisplayMetrics().density * dp + 0.5f);
    }

    private ColorShadowDrawable getShadowBg(int cornerType, int sideType) {
        Resources resources = getResources();
        ColorStateList backgroundColor = ColorStateList.valueOf(resources.getColor(android.R.color.white));//设置背景色
        ColorShadowDrawable shadowBg = new ColorShadowDrawable(resources, backgroundColor, dip2px(25), dip2px(3), dip2px(6));
        shadowBg.setShadowCorner(cornerType);
        shadowBg.setShadowSide(sideType);
        int startColor = resources.getColor(R.color.shadow_start_color);//阴影内侧颜色
        int endColor = resources.getColor(R.color.shadow_end_color);//阴影外侧颜色
        shadowBg.setShadowColor(startColor, endColor);
        return shadowBg;
    }

    private ColorShadowDrawable getShadowBg(int cornerType, int sideType, float radius) {
        Resources resources = getResources();
        ColorStateList backgroundColor = ColorStateList.valueOf(resources.getColor(android.R.color.white));//设置背景色
        ColorShadowDrawable shadowBg = new ColorShadowDrawable(resources, backgroundColor, dip2px(radius), dip2px(3), dip2px(6));
        shadowBg.setShadowCorner(cornerType);
        shadowBg.setShadowMultiplier(1.5f);
        shadowBg.setShadowSide(sideType);
/*        int startColor = resources.getColor(R.color.shadow_start_color);//阴影内侧颜色
        int endColor = resources.getColor(R.color.shadow_end_color);//阴影外侧颜色
        shadowBg.setShadowColor(startColor, endColor);*/
        return shadowBg;
    }

    private int dip2px(float dipValue) {
        if (dipValue == 0) {
            return 0;
        }
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}