package com.websarva.wings.android.applicationforvisualizingbudgets;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.android.material.datepicker.MaterialDatePicker;

import java.util.Calendar;

public class CreateListFragment extends Fragment implements DatePickerDialog.OnDateSetListener{
    //期間表示用変数
    private TextView inputDateStart;
    private TextView inputDateEnd;

    public CreateListFragment() {
        super(R.layout.fragment_create_list);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //OnDateSetへ受け渡しのためビューをあらかじめ取得
        inputDateStart=view.findViewById(R.id.btPeriodStart);
        inputDateEnd=view.findViewById(R.id.btPeriodStart);

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
        Button btPeriodStart =view.findViewById(R.id.btPeriodStart);
        btPeriodStart.setOnClickListener(new BtPeriodClickListener());

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

    public static class DatePickerFragment extends DialogFragment{
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            // Use the current date as the default date in the picker.
            final Calendar cl = Calendar.getInstance();
            int year = cl.get(Calendar.YEAR);
            int month = cl.get(Calendar.MONTH);
            int day = cl.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it.
            return new DatePickerDialog(requireContext(), (DatePickerDialog.OnDateSetListener)getParentFragment(), year, month, day);
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        String outputDateStart=String.format("%d/%d/%d", year, month+1, day);
        inputDateStart.setText(outputDateStart);
    }


    private class BtPeriodClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            DatePickerFragment dpFragment= new DatePickerFragment();
            dpFragment.show(getChildFragmentManager(), "datePicker");
        }
    }
}
