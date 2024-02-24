package com.websarva.wings.android.applicationforvisualizingbudgets;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.core.util.Pair;
import androidx.fragment.app.FragmentTransaction;

import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateListFragment extends Fragment{
    //期間表示用変数
    private TextView selectedDate;

    public CreateListFragment() {
        super(R.layout.fragment_create_list);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //OnDateSetへ受け渡しのためビューをあらかじめ取得
        selectedDate=view.findViewById(R.id.tvPeriodDisplay);

        //Toolbarを取得
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        //ツールバーにタイトル文字列を設定
        toolbar.setTitle(R.string.toolbar_create_budget_items);
        // activityを取得する。
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        // ActionBarを設置する。
        activity.setSupportActionBar(toolbar);
        // ActionBarにhomeボタンを表示する。
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // homeボタンを押下できる設定にする。
        activity.getSupportActionBar().setHomeButtonEnabled(true);
        // Fragment#onOptionsItemSelectedを有効にする。
        setHasOptionsMenu(true);

        //期間入力ボタンにリスナ設定
        Button btPeriod =view.findViewById(R.id.btPeriodInput);
        btPeriod.setOnClickListener(new BtPeriodClickListener());

        //クリアボタンにリスナ設定
        ImageButton ibClear =view.findViewById(R.id.ibClear);
        ibClear.setOnClickListener(new ibClearClickListener());

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
    private class BtPeriodClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            CreateDatePickerDialog();
        }
    }

    private class ibClearClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view){
            if(selectedDate.getText()!="指定なし"){
                selectedDate.setText("指定なし");
            }
        }
    }
}


