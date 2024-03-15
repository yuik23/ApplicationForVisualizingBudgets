package com.websarva.wings.android.applicationforvisualizingbudgets.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.websarva.wings.android.applicationforvisualizingbudgets.R;

public class NotEnteredDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){
        //ダイアログビルダーを生成
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        // 値を受け取る
        String errorPoint = requireArguments().getString("errorPoint", "");
        //ダイアログのメッセージを設定
        builder.setMessage(errorPoint+"を入力してください");
        //Positive Buttonを設定
        builder.setPositiveButton(R.string.dialog_btn_ok, new DialogButtonClickListener());
        //ダイアログオブジェクトを生成し、リターン
        AlertDialog dialog= builder.create();
        return dialog;
    }

    private class DialogButtonClickListener implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialog,int which){

        }



    }
}

