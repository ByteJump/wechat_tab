package com.example.zhizihua.wechatdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.zhizihua.wechatdemo.fragment.TagFragment;
import com.example.zhizihua.wechatdemo.view.tabView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivitywithtab extends AppCompatActivity {
    private static final String BOUNDLEKEY = "bundle_ket_position";
    private ViewPager mViewPager;
    private List<String> titles = new ArrayList<>(Arrays.asList(new String[]{"微信", "通讯录", "朋友圈", "我的"}));
    private tabView wechat, tongxunlu, circlr, mine;
    private SparseArray<TagFragment> fragments = new SparseArray<>();
    private List<tabView> btns = new ArrayList<>();
    private int mCurrentPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);
        if(savedInstanceState != null){
            mCurrentPage = savedInstanceState.getInt(BOUNDLEKEY);
        }
        initView();
        initViewPager();
        initEvent();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(BOUNDLEKEY,mViewPager.getCurrentItem());
        super.onSaveInstanceState(outState);
    }

    private void initEvent() {
        for(int i = 0 ; i < btns.size() ; i++){
            tabView tab = btns.get(i);
            final int finalI = i;
            tab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mViewPager.setCurrentItem(finalI,false);
                    setCurrentTab(finalI);
                }
            });
        }
    }

    private void initViewPager() {
        mViewPager = findViewById(R.id.viewPager);
        mViewPager.setOffscreenPageLimit(titles.size());
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                TagFragment tagFragment = TagFragment.newInstance(titles.get(position));
                if (position == 0) {
                    tagFragment.setOnTabClickListener(new TagFragment.OnTabClickListener() {
                        @Override
                        public void onClick(String title) {
                            setTitle(title);
                        }
                    });
                }
                return tagFragment;
            }

            @Override
            public int getCount() {
                return titles.size();
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                TagFragment fragment = (TagFragment) super.instantiateItem(container, position);
                fragments.put(position, fragment);
                return fragment;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                fragments.delete(position);
                super.destroyItem(container, position, object);
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e("scroll",position+"     "+positionOffset);
                //从左到右  positionoffset  是从0到1
                //从右到左  positionoffset 从1到0
                if(positionOffset>0){
                    tabView left = btns.get(position);
                    tabView right = btns.get(position+1);

                    left.setProgress(1-positionOffset);
                    right.setProgress(positionOffset);
                }

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        wechat = findViewById(R.id.btn_wechat);
        tongxunlu = findViewById(R.id.btn_tongxunlu);
        circlr = findViewById(R.id.btn_circle);
        mine = findViewById(R.id.btn_mine);
        btns.clear();
        btns.add(wechat);
        btns.add(tongxunlu);
        btns.add(circlr);
        btns.add(mine);
        setCurrentTab(mCurrentPage);
        wechat.setImageAndText(R.mipmap.weixin,R.mipmap.weixin_select,"微信");
        tongxunlu.setImageAndText(R.mipmap.tongxunlu,R.mipmap.tongxunlu_select,"通讯录");
        circlr.setImageAndText(R.mipmap.faxian,R.mipmap.faxian_select,"发现");
        mine.setImageAndText(R.mipmap.wode,R.mipmap.wode_select,"我的");
    }
    public void setCurrentTab(int position){
        for(int i = 0 ; i < btns.size() ; i++){
            tabView tab = btns.get(i);
            if(i == position){
                tab.setProgress(1);
            }else{
                tab.setProgress(0);
            }
        }
    }
}
