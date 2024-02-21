package com.websarva.wings.android.applicationforvisualizingbudgets;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainFragment extends Fragment {

    private String[]  pageTabTitle ={"リスト", "集計"};

    //コンストラクタ
    public MainFragment() {
        super(R.layout.fragment_main);
    }

    @Override
    public void onViewCreated(@NonNull View view,@Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Toolbarを取得
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        //ツールバーにロゴを設定
        toolbar.setLogo(R.mipmap.ic_elephant);
        //ツールバーにタイトル文字列を設定
        toolbar.setTitle(R.string.toolbar_title);



        //このフラグメントが所属するフラグメントアクティビティオブジェクトを取得
        FragmentActivity parentFragmentActivity =getActivity();

        // FragmentSlideAdapter を確保
        FragmentSlideAdapter fragmentSlideAdapter = new FragmentSlideAdapter(parentFragmentActivity);

        // MainActivity の ViewPager2 に fragmentSlideAdapter を割り当てる
        ViewPager2 viewPager2 = view.findViewById(R.id.pager);
        viewPager2.setAdapter(fragmentSlideAdapter);

        //タブ作成
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) -> tab.setText(pageTabTitle[position])
        ).attach();

    }

}