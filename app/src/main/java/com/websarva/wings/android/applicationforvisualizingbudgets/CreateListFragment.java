package com.websarva.wings.android.applicationforvisualizingbudgets;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;
import android.view.View;

public class CreateListFragment extends Fragment {

    public CreateListFragment() {
        super(R.layout.fragment_create_list);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Toolbarを取得
        Toolbar toolbar = view.findViewById(R.id.toolbar);

        //ツールバーにタイトル文字列を設定
        toolbar.setTitle(R.string.toolbar_create_budget_items);
        // 1.activityを取得する。
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        // 2.ActionBarを設置する。
        activity.setSupportActionBar(toolbar);
        // 3.ActionBarにhomeボタンを表示する。
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // 4.3で設定したhomeボタンを押下できる設定にする。
        activity.getSupportActionBar().setHomeButtonEnabled(true);
        // 5.Fragment#onOptionsItemSelectedを有効にする。
        setHasOptionsMenu(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                //フラグメントマネージャーを取得
                FragmentManager manager=getParentFragmentManager();
                //バックスタックの一つ前に戻る
                manager.popBackStack();
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
