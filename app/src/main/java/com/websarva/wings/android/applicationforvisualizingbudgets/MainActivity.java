package com.websarva.wings.android.applicationforvisualizingbudgets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    private String[]  pageTabTitle ={"リスト", "集計"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Toolbarを取得
        Toolbar toolbar = findViewById(R.id.toolbar);
        //ツールバーにロゴを設定
        toolbar.setLogo(R.mipmap.ic_elephant);
        //ツールバーにタイトル文字列を設定
        toolbar.setTitle(R.string.toolbar_title);
        //ツールバーのタイトル文字色を設定
        toolbar.setTitleTextColor(Color.WHITE);
        //アクションバーにツールバーを設定
        setSupportActionBar(toolbar);


        // FragmentSlideAdapter を確保
        FragmentSlideAdapter fragmentSlideAdapter = new FragmentSlideAdapter(this);

        // MainActivity の ViewPager2 に fragmentSlideAdapter を割り当てる
        ViewPager2 viewPager2 = findViewById(R.id.pager);
        viewPager2.setAdapter(fragmentSlideAdapter);

        //タブ作成
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) -> tab.setText(pageTabTitle[position])
        ).attach();

    }
}