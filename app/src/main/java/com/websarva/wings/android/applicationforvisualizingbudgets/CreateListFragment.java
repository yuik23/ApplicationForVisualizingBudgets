package com.websarva.wings.android.applicationforvisualizingbudgets;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateListFragment extends Fragment{
    public CreateListFragment() {
        super(R.layout.fragment_create_list);
    }

    //期間入力時に選択された期間を格納
    private TextView selectedDate;

    //予算項目入力時の候補表示用アダプター
    private ItemArrayAdapter itemAdapter;

    //ListView表示用
    public SimpleAdapter itemListAdapter;
    public List<Map<String, String>> itemList;
    public Map<String, String> itemData;
   /* public ImageButton ibDeleteRowV;*/


    //入力項目取得用
    public AutoCompleteTextView actvItemV;
    public String inputItem;
    public EditText etItemBudgetV;
    public Integer inputItemBudget;

    //項目入力後、合計予算を表示する用
    public TextView tvTotalBudgetDisplayV;
    public Integer totalBudget=0;

    //項目削除の際に取得する削除分の予算
    private Integer deletedBudget;
    private Map<String, String> selectedRow;




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //OnDateSetへ受け渡しのためビューをあらかじめ取得
        selectedDate=view.findViewById(R.id.tvPeriodDisplay);

        //プラスボタンリスナへ受け渡しのためビューをあらかじめ取得
        actvItemV=view.findViewById(R.id.actvItem);
        etItemBudgetV=view.findViewById(R.id.etItemBudget);

        //合計予算表示用ビューを取得
        tvTotalBudgetDisplayV=view.findViewById(R.id.tvTotalBudgetDisplay);

        /*//リストビューの行削除のため削除ボタンのビューをあらかじめ取得
        ibDeleteRowV=view.findViewById(R.id.ibDeleteRow);*/


        //Toolbarを取得
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        //ツールバーにタイトル文字列を設定
        toolbar.setTitle(R.string.toolbar_create_budget_items);
        // activityを取得する。
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        // ActionBarを設置する。
        activity.setSupportActionBar(toolbar);
        // ActionBarに戻るボタンを表示する。
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // 戻るボタンを押下できる設定にする。
        activity.getSupportActionBar().setHomeButtonEnabled(true);
        // Fragment#onOptionsItemSelectedを有効にする。
        setHasOptionsMenu(true);


        //期間入力ボタンにリスナ設定
        ImageButton ibPeriod =view.findViewById(R.id.ibPeriodInput);
        ibPeriod.setOnClickListener(new BtPeriodClickListener());


        //クリアボタンにリスナ設定
        ImageButton ibClear =view.findViewById(R.id.ibClear);
        ibClear.setOnClickListener(new btClearClickListener());


        //予算項目入力時に候補を表示する
        String[] itemArray = {"食費", "日用品費", "医療費", "子ども費", "被服費", "美容費", "交際費", "娯楽費", "雑費", "特別費"};
        itemAdapter = new ItemArrayAdapter (requireContext(), android.R.layout.simple_dropdown_item_1line, itemArray);
        actvItemV .setAdapter(itemAdapter);
        //未入力(フォーカス、タップ)で候補を表示
        actvItemV.setThreshold(1);
        actvItemV.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                actvItemV.showDropDown();
            }
        });
        actvItemV.setOnClickListener(v -> actvItemV.showDropDown());
        //enterキーが押されたときソフトキーボードを隠す
        actvItemV.setOnEditorActionListener(new enterClickListener());
        etItemBudgetV.setOnEditorActionListener(new enterClickListener());


        //ListViewオブジェクトを取得
        ListView itemListView=view.findViewById(R.id.lvBudgetItems);
        //ListViewに表示するリストデータ用Listオブジェクトを作成
        itemList = new ArrayList<Map<String, String>>();
        //項目データを格納するMapオブジェクトを用意
        itemData = new HashMap<String,String>();
        //SimpleAdapter第4引数from用データの用意
        String[] from={"item","itemBudget"};
        //SimpleAdapter第5引数to用データの用意
        int[] to={R.id.tvItemRow,R.id.tvItemBudgetRow};
        //アダプタオブジェクトを生成
        itemListAdapter=new SimpleAdapter(requireContext(),itemList,R.layout.row_item_list,from,to);
        //リストビューにアダプタオブジェクトを設定
        itemListView.setAdapter(itemListAdapter);


        //プラスボタンにリスナ設定
        ImageButton ibAddItem=view.findViewById(R.id.ibAddItem);
        ibAddItem.setOnClickListener(new ibAddItemClickListener());

        registerForContextMenu(itemListView);


       /* // ListViewに表示するためのDATAを作成する
        int MAXDATA = 10;
        for (int i = 0; i < MAXDATA; i++) {
            data = new HashMap<String, String>();
            data.put("text1", "タイトル" + i);
            data.put("text2", "サブ" + i);
            dataList.add(data);
        }

        // アダプターにデータを渡す
        listAdapter = new ItemListViewAdapter(
                requireContext(),
                dataList,
                R.layout.row_item_list,
                new String[] { "text1", "text2" },
                new int[] { android.R.id.text1,
                        android.R.id.text2 });

        // ListViewにアダプターをSETする
        listView = (ListView) view.findViewById(R.id.lvBudgetItems);
        listView.setAdapter(listAdapter);
        listView.setTextFilterEnabled(false);*/
    }

    //MenuItemが押されると呼ばれるメソッド
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


    private void CreateDatePickerDialog() {
        // Creating a MaterialDatePicker builder for selecting a date range
        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
        builder.setTitleText("期間を選択してください");

        // Building the date picker dialog
        MaterialDatePicker<Pair<Long, Long>> datePicker = builder.build();
        datePicker.addOnPositiveButtonClickListener(selection -> {

            // Retrieving the selected start and end dates
            Long startDate = selection.first;
            Long endDate = selection.second;

            // Formatting the selected dates as strings
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            String startDateString = sdf.format(new Date(startDate));
            String endDateString = sdf.format(new Date(endDate));

            // Creating the date range string
            String selectedDateRange = startDateString + " - " + endDateString;

            // Displaying the selected date range in the TextView
            selectedDate.setText(selectedDateRange);
        });

        // Showing the date picker dialog
        datePicker.show(getChildFragmentManager(), "datePicker");
    }

    //期間入力ボタンのリスナ
    private class BtPeriodClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            CreateDatePickerDialog();
        }
    }

    //期間クリアボタンのリスナ
    private class btClearClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view){
            if(selectedDate.getText()!="指定なし"){
                selectedDate.setText("指定なし");
            }
        }
    }

    //項目に入力候補を表示するためにFilter作成
    public class ItemsFilter extends Filter {
        //候補を絞らないのでperformFilteringで空のFilterResultを返す
        @Override
        protected FilterResults performFiltering(CharSequence prefix) {
            return new FilterResults();
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count > 0) {
                itemAdapter.notifyDataSetChanged();
            } else {
                itemAdapter.notifyDataSetInvalidated();
            }
        }
    }

    //項目に入力候補を表示するためにAdapter作成
    public class ItemArrayAdapter extends ArrayAdapter<String> {
        //コンストラクタは適当
        public ItemArrayAdapter (@NonNull Context context, int textViewResourceId, @NonNull String[] objects) {
            super(requireContext(), textViewResourceId, objects);
        }
        @Override
        public Filter getFilter() {
            return new ItemsFilter();
        }
    }

    //項目入力欄のリスナ。enterキー入力時にソフトウェアキーボードを隠す処理
    private class enterClickListener implements TextView.OnEditorActionListener {

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                if (event.getAction() == KeyEvent.ACTION_UP) {
                    // ソフトウェアキーボードを隠す
                    InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
                    return true;
            }
                return false;
        }
    }


    //プラスボタンのリスナ
    private class ibAddItemClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view){
            //入力がある場合、項目入力欄の値を取得、クリア
            if((actvItemV.length()!=0)&&(etItemBudgetV.length()!=0)) {
                inputItem = actvItemV.getText().toString();
                actvItemV.setText("");
                inputItemBudget = Integer.parseInt(etItemBudgetV.getText().toString());
                totalBudget=totalBudget+inputItemBudget;
                tvTotalBudgetDisplayV.setText(totalBudget.toString());
                etItemBudgetV.setText("");

                //ListViewに取得した値を格納
                itemData = new HashMap<>();
                itemData.put("item", inputItem);
                itemData.put("itemBudget", inputItemBudget + "円");
                itemList.add(itemData);
                itemListAdapter.notifyDataSetChanged();
            }
        }
    }

    //リストを長押しで削除コンテキストメニューを表示
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        //親クラスの同名メソッド呼び出し
        super.onCreateContextMenu(menu,view,menuInfo);
        //メニューインフレーターの取得
        MenuInflater inflater =getActivity().getMenuInflater();
        //コンテキストメニュー用.xmlファイルをインフレート
        inflater.inflate(R.menu.context_menu,menu);
    }

    //コンテキストメニューが選択されたときの処理
    @Override
    public boolean onContextItemSelected(MenuItem item){
        //戻り値用の変数を初期値trueで用意
        boolean returnVal =true;
        //長押しされたビューに関する情報が格納されたオブジェクトを取得
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        //長押しされたリストのポジションを取得
        int listPosition=info.position;
        //選択された行の予算を取得
        selectedRow=itemList.get(listPosition);
        deletedBudget=Integer.parseInt(selectedRow.get("itemBudget").replace("円",""));
        //合計予算から削除分を減らす
        totalBudget=totalBudget-deletedBudget;
        tvTotalBudgetDisplayV.setText(totalBudget.toString());
        //選択されたListViewの行を削除
        itemList.remove(listPosition);
        itemListAdapter.notifyDataSetChanged();
        return returnVal;
    }


}
