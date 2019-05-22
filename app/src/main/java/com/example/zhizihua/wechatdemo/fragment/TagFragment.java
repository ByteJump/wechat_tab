package com.example.zhizihua.wechatdemo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.zhizihua.wechatdemo.MainActivity;
import com.example.zhizihua.wechatdemo.R;

/**
 * Created by zhizihua on 2019/5/22.
 */

public class TagFragment extends Fragment {
    private static final String BUNDLE_KEY_TITLE = "BUNDLE_KEY_TITLE";
    private TextView tab;
    private String title;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("tag", "oncreat");
        if (getArguments() != null)
            title = getArguments().getString(BUNDLE_KEY_TITLE, "");
    }
    public interface OnTabClickListener{
        void onClick(String title);
    }
    private OnTabClickListener onTabClickListener;
    public void setOnTabClickListener(OnTabClickListener onTabClickListener){
        this.onTabClickListener = onTabClickListener;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("tag", "oncreatview");
        return inflater.inflate(R.layout.tab_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.e("tag", "onviewcreat");
        super.onViewCreated(view, savedInstanceState);
        tab = view.findViewById(R.id.tv_tab);
        tab.setText(title);
//        tab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(onTabClickListener != null)
//                onTabClickListener.onClick("f调A");
//            }
//        });
    }

    public static TagFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY_TITLE, title);
        TagFragment tagFragment = new TagFragment();
        tagFragment.setArguments(bundle);
        return tagFragment;
    }
//    public void changetitle(String title){
//        if(!isAdded()){//fragment在没有被attach之前  回调方法不执行 view并没有被创建  说以要先判断是否添加到activity上
//            return;
//        }
//        tab.setText(title);
//    }
}
