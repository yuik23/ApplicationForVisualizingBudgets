package com.websarva.wings.android.applicationforvisualizingbudgets.view;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


public class FragmentSlideAdapter extends FragmentStateAdapter {

    private static final int FRAGMENT_MAX = 2;

    private final Fragment[] fragments;
    private final String[]   messages;

    public FragmentSlideAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);

        fragments = new Fragment[]{
                new ListFragment(),
                new AggregationFragment(),
        };

        messages = new String[]{    // Fragment_0～2 へ渡すデータ
                null,
                null,
        };
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
/*
        if(position == 0) {
            // Fragment_0 へ文字列を渡す
            // キー "param1" は Fragment_0.java で定義された ARG_PARAM1 の値を使っている
            Bundle bundle = new Bundle();
            bundle.putString("param1", messages[0]);
            fragments[position].setArguments(bundle);
        }*/



        return fragments[position];
    }

    @Override
    public int getItemCount() { return FRAGMENT_MAX; }

/*    // MainActivity から文字列を受け取る
    public void setString(int position, String string) {
        messages[position] = string;
    }*/
}