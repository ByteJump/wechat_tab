package com.example.zhizihua.wechatdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.zhizihua.wechatdemo.fragment.SplashFragment;
import com.example.zhizihua.wechatdemo.view.ScaleTransFormer;

/**
 * Created by zhizihua on 2019/5/22.
 */

public class SplashActivity extends AppCompatActivity {
    private int[] mResIds = new int[]{
            R.mipmap.a,
            R.mipmap.b,
            R.mipmap.c,
            R.mipmap.d
    };
    private ViewPager mViewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mViewPager = findViewById(R.id.viewpager);

        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return SplashFragment.newInstance(mResIds[position]);
            }

            @Override
            public int getCount() {
                return mResIds.length;
            }
        });
        mViewPager.setPageTransformer(true,new ScaleTransFormer());
    }
}
