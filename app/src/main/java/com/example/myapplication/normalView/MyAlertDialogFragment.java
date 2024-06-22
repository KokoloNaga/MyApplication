package com.example.myapplication.normalView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.myapplication.R;

public class MyAlertDialogFragment extends DialogFragment {
    private DialogInterface.OnClickListener onClickListener;
    public static MyAlertDialogFragment newInstance(){
        return new MyAlertDialogFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setIcon(R.mipmap.ic_launcher)
                .setTitle(R.string.app_name);

        if(onClickListener != null){
            builder.setPositiveButton(R.string.alert_dialog_ok, onClickListener);
            builder.setNegativeButton(R.string.alert_dialog_cancel, onClickListener);
        }

        return builder.create();
    }

    public void setOnClickListener(DialogInterface.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }
}
