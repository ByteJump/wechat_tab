package com.example.zhizihua.wechatdemo.view;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

/**
 * Created by zhizihua on 2019/5/22.
 */

public class ScaleTransFormer implements ViewPager.PageTransformer{
    private static final float MIN_SCALE = 0.75f;
    @Override
    public void transformPage(@NonNull View page, float position) {
        Log.e("trans",page.getTag()+"   "+position);
        //a→b
        //a 的position  0→-1
        //b position   1→ 0

        //b→a
        //a position  -1→0
        //b position  0→1

        if(position <= -1){//[,-1]
            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
        }else if(position < 1){//[-1,1]
            if(position < 0){//a页面
                //[1,0.75]
                float ScaleA = MIN_SCALE + (1 - MIN_SCALE) * (1 + position);
                page.setScaleX(ScaleA);
                page.setScaleY(ScaleA);
            }else{//b页面
                //[0.75,1]
                float ScaleB = MIN_SCALE + (1 - MIN_SCALE)*(1 - position);
                page.setScaleX(ScaleB);
                page.setScaleY(ScaleB);
            }
        }else{//[1,]
            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
        }
    }
}
