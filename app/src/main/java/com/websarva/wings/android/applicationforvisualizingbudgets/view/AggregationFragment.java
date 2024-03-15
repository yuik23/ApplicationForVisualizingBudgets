package com.websarva.wings.android.applicationforvisualizingbudgets.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.websarva.wings.android.applicationforvisualizingbudgets.R;


public class AggregationFragment extends Fragment {
    //コンストラクタ。指定したレイアウトファイルの画面を表示
    public AggregationFragment() {
        super(R.layout.fragment_aggregation);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_aggregation, container, false);
    }
}