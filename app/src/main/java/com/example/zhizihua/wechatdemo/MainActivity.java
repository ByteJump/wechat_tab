package com.example.zhizihua.wechatdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.zhizihua.wechatdemo.fragment.TagFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private List<String> titles = new ArrayList<>(Arrays.asList(new String[]{"微信", "通讯录", "朋友圈", "我的"}));
    private Button wechat, tongxunlu, circlr, mine;
    private SparseArray<TagFragment> fragments = new SparseArray<>();
    private List<Button> btns = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initViewPager();
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
                    Button left = btns.get(position);
                    Button right = btns.get(position+1);

                    left.setText((1-positionOffset)+"");
                    right.setText(positionOffset+"");
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
        wechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TagFragment tagFragment = fragments.get(0);
                if (tagFragment != null) {
//                    tagFragment.changetitle("activity调用fragment方法");
                }
            }
        });
    }

    public void setTitle(String title) {
        wechat.setText(title);
    }
}
