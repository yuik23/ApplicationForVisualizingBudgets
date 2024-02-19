package com.websarva.wings.android.applicationforvisualizingbudgets;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ListFragment extends Fragment {

    public ListFragment() {
        super(R.layout.fragment_list);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //ボタンを取得
        ImageButton ibAddButton =view.findViewById(R.id.ibAdd);
        //戻るボタンにリスナを登録
        ibAddButton.setOnClickListener(new ibAddClickListener());
    }

    //ボタンをクリックしたときのリスナクラス
    private class ibAddClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view){

            //引継ぎデータをまとめて格納できるBundleオブジェクトを作成
            Bundle bundle=new Bundle();
            //Bundleオブジェクトに引き継ぎデータを格納

            //フラグメントマネージャーを取得
            FragmentManager manager=getParentFragmentManager();
            //フラグメントトランザクションの開始
            FragmentTransaction transaction=manager.beginTransaction();
            //フラグメントトランザクションが正しく動作するように設定
            transaction.setReorderingAllowed(true);
            //現在の表示内容をバックスタックに追加
            transaction.addToBackStack("List");
            //ListFragmentをリスト作成画面フラグメントに置き換え
            transaction.replace(R.id.fragment_list,CreateListFragment.class,bundle);
            //フラグメントトランザクションのコミット
            transaction.commit();

        }

    }

}