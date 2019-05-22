package com.example.zhizihua.wechatdemo.view;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhizihua.wechatdemo.R;

/**
 * Created by zhizihua on 2019/5/22.
 */

public class tabView extends FrameLayout {
    private ImageView iv_nomal;
    private ImageView iv_select;
    private TextView title;
    private static final int COLORDEFAULT = Color.parseColor("#ff000000");
    private static final int COLORSELECT = Color.parseColor("#ff45c01a");
    public tabView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.tab_item, this);
        iv_nomal = findViewById(R.id.iv_nomal);
        iv_select = findViewById(R.id.iv_select);
        title = findViewById(R.id.tv_title);
        setProgress(0);//默认是灰色的未选中
    }

    public void setProgress(float progress) {
        iv_nomal.setAlpha(1 - progress);
        iv_select.setAlpha(progress);
        title.setTextColor(evaluate(progress,COLORDEFAULT,COLORSELECT));
    }
    public void setImageAndText(int ivnomal, int ivselect, String text) {
        iv_nomal.setImageResource(ivnomal);
        iv_select.setImageResource(ivselect);
        title.setText(text);
    }
//    ArgbEvaluator
    public int evaluate(float fraction, int startValue, int endValue) {
        int startInt = (Integer) startValue;
        float startA = ((startInt >> 24) & 0xff) / 255.0f;
        float startR = ((startInt >> 16) & 0xff) / 255.0f;
        float startG = ((startInt >> 8) & 0xff) / 255.0f;
        float startB = ((startInt) & 0xff) / 255.0f;

        int endInt = (Integer) endValue;
        float endA = ((endInt >> 24) & 0xff) / 255.0f;
        float endR = ((endInt >> 16) & 0xff) / 255.0f;
        float endG = ((endInt >> 8) & 0xff) / 255.0f;
        float endB = ((endInt) & 0xff) / 255.0f;

        // convert from sRGB to linear
        startR = (float) Math.pow(startR, 2.2);
        startG = (float) Math.pow(startG, 2.2);
        startB = (float) Math.pow(startB, 2.2);

        endR = (float) Math.pow(endR, 2.2);
        endG = (float) Math.pow(endG, 2.2);
        endB = (float) Math.pow(endB, 2.2);

        // compute the interpolated color in linear space
        float a = startA + fraction * (endA - startA);
        float r = startR + fraction * (endR - startR);
        float g = startG + fraction * (endG - startG);
        float b = startB + fraction * (endB - startB);

        // convert back to sRGB in the [0..255] range
        a = a * 255.0f;
        r = (float) Math.pow(r, 1.0 / 2.2) * 255.0f;
        g = (float) Math.pow(g, 1.0 / 2.2) * 255.0f;
        b = (float) Math.pow(b, 1.0 / 2.2) * 255.0f;

        return Math.round(a) << 24 | Math.round(r) << 16 | Math.round(g) << 8 | Math.round(b);
    }
}
