package com.websarva.wings.android.applicationforvisualizingbudgets.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.websarva.wings.android.applicationforvisualizingbudgets.MainViewModel;
import com.websarva.wings.android.applicationforvisualizingbudgets.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListFragment extends Fragment {

    private MainViewModel viewModel;


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
        BudgetListAdapter budgetListAdapter = new BudgetListAdapter();
        //MainViewModel の budgetNameListLiveData が設定 (setValue/postValue) されたら、その値をパラメータとして adapter.setList() を呼ぶように設定
        viewModel.getBudgetNameList().observe(getViewLifecycleOwner(), budgetListAdapter::setList);
        lvBudgetV.setAdapter(budgetListAdapter);


        //受け渡されたデータ取得
        //ViewModelProviderでViewModelを取得
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);



        //budgetNameに入力があればデータ取得
        if(inputNumber != 0){
            //ListViewオブジェクトに表示するリストデータ用Listオブジェクトを作成
            budgetList = new ArrayList<Map<String, String>>();
            for(int i=0 ; i<inputNumber ; i++){
                //項目データを格納するMapオブジェクトを用意
                budgetMap= new HashMap<String,String>();
                budgetMap.put("budgetName",viewModel.budgetName.get(i));
                budgetMap.put("totalBudget",viewModel.totalBudget.get(i).toString()+"円");
                budgetMap.put("period",viewModel.period.get(i));
                budgetList.add(budgetMap);
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
    }

    private static class BudgetListAdapter extends BaseAdapter {
        private List<String> budgetNameList = Collections.emptyList();
        private List<String> periodList=Collections.emptyList();
        private List<String> totalBudgetList=Collections.emptyList();

        @SuppressWarnings("notifyDataSetChanged")//IDE での警告を抑制
        void setList(List<String> budgetName,List<String> period,List<Integer> totalBudget) {
            this.budgetNameList = new ArrayList(budgetName); //防御コピー
            this.periodList=new ArrayList(budgetName);
            this.totalBudgetList=new ArrayList(budgetName);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return budgetNameList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //convertViewがnullならViewを生成、あれば再利用
            return (convertView == null ? new ViewHolder(parent) : (ViewHolder)convertView.getTag())
                    .bind(budgetNameList.get(position));

        }
        private class ViewHolder {
            private final View itemView;
            private final TextView text1;

            ViewHolder(ViewGroup parent) {
                itemView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
                itemView.setTag(this);
                text1 = itemView.findViewById(android.R.id.text1);
            }

            View bind(String name) {
                text1.setText(name);
                return itemView;
            }
        }
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