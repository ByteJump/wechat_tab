package com.example.zhizihua.wechatdemo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.zhizihua.wechatdemo.R;

/**
 * Created by zhizihua on 2019/5/22.
 */

public class SplashFragment extends Fragment {
    private static final String BUNDLE_KEY_ID = "bundle_key_resid";
    private ImageView mSplashImg;
    private int ResId;
    public static SplashFragment newInstance(int resId) {
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_KEY_ID,resId);
        SplashFragment splashFragment = new SplashFragment();
        splashFragment.setArguments(bundle);
        return splashFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            ResId = getArguments().getInt(BUNDLE_KEY_ID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSplashImg = view.findViewById(R.id.iv_splash);
        mSplashImg.setImageResource(ResId);
        view.setTag(ResId);
    }
}
