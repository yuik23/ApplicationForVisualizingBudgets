package com.websarva.wings.android.applicationforvisualizingbudgets;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListFragment extends Fragment {

    //項目作成画面から受け取る変数
    private String budgetName="";
    private String totalBudget="";
    private String period="";

    //予算名リスト
    private List<Map<String,String>> budgetList;
    private Map<String,String> budgetMap;


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

        //ListViewオブジェクトを取得
        ListView lvBudgetV =view.findViewById(R.id.lvBudget);
        //ListViewオブジェクトに表示するリストデータ用Listオブジェクトを作成
        budgetList = new ArrayList<Map<String, String>>();
        //項目データを格納するMapオブジェクトを用意
        budgetMap= new HashMap<String,String>();
        budgetList.add(budgetMap);

        //受け渡されたデータ取得
        Bundle extras=getArguments();
        if(extras!=null){
            budgetName=extras.getString("budgetName");
            totalBudget=extras.getString("totalBudget");
            period=extras.getString("period");
            budgetMap.put("budgetName",budgetName);
            budgetMap.put("totalBudget",totalBudget.toString());
            budgetMap.put("period",period);
        }
        //SimpleAdapter第4引数from用データの用意
        String[] from={"budgetName","totalBudget","period"};
        //SimpleAdapter第5引数to用データの用意
        int[] to={R.id.tvBudgetNameRow,R.id.tvTotalBudgetRow,R.id.tvPeriodRow};
        //アダプタオブジェクトを生成
        SimpleAdapter budgetListAdapter=new SimpleAdapter(requireContext(),budgetList,R.layout.row_budget_list,from,to);
        //リストビューにアダプタを設定
        lvBudgetV.setAdapter(budgetListAdapter);

    }

    //ボタンをクリックしたときのリスナクラス
    private class ibAddClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view){

            //引継ぎデータをまとめて格納できるBundleオブジェクトを作成
            Bundle bundle=new Bundle();
            //Bundleオブジェクトに引き継ぎデータを格納

            //フラグメントマネージャーを取得
            FragmentManager lfManager=getParentFragmentManager();
            //フラグメントトランザクションの開始
            FragmentTransaction transaction=lfManager.beginTransaction();
            //フラグメントトランザクションが正しく動作するように設定
            transaction.setReorderingAllowed(true);
            //現在の表示内容をバックスタックに追加
            transaction.addToBackStack("List");
            //ListFragmentをリスト作成画面フラグメントに置き換え
            transaction.replace(R.id.fragmentMainContainer,CreateListFragment.class,bundle);
            //フラグメントトランザクションのコミット
            transaction.commit();
        }

    }

}