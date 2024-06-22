package com.example.myapplication.normalView;

import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class AlertPopupActivity extends AppCompatActivity {
    private Button btnAlert, btnShowPopupWindow, btnBottomPopupWindow, btnAblum, btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_alert_popup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // alertDialog
        btnAlert = findViewById(R.id.btn_alert);
        btnAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        // popupWindow 按钮下出现
        btnShowPopupWindow = findViewById(R.id.btn_show_popupwindow);
        btnShowPopupWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAsDropDown();
            }
        });
        // popupWindow 页面底出现
        btnBottomPopupWindow = findViewById(R.id.btn_bottom_popupwindow);
        btnBottomPopupWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomPopupWindow();
            }
        });
        // 自定义dialog
        findViewById(R.id.btn_my_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialogFragment.newInstance().show(getSupportFragmentManager(), "MyDialogFragment");
            }
        });
        // 自定义alertdialog
        findViewById(R.id.btn_my_alertdialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAlertDialogFragment myAlertDialogFragment = MyAlertDialogFragment.newInstance();
                myAlertDialogFragment.setOnClickListener(onClickListener);
                myAlertDialogFragment.show(getSupportFragmentManager(), "MyAlertDialogFragment");
            }
        });

    }

    // 在Button下弹出窗口
    private void showAsDropDown(){
        View popView = LayoutInflater.from(AlertPopupActivity.this).inflate(R.layout.popup_drop_down, null);
        PopupWindow popupWindow = new PopupWindow(popView, LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);  // 设置PopupWindow，第一个为View，第二个为H，第三个为W
        popupWindow.setOutsideTouchable(true);  // 点击外部后PopupWindow消失
        popupWindow.setBackgroundDrawable(new BitmapDrawable());  // 可以可无
        popupWindow.showAsDropDown(btnShowPopupWindow, 100, 0);  // 目标及目标在X和Y方向的偏移

    }

    // 在Button上弹出窗口
    private void showBottomPopupWindow(){
        View popView = LayoutInflater.from(AlertPopupActivity.this).inflate(R.layout.popup_bottom, null);
        final PopupWindow popupWindow = new PopupWindow(popView, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x30000000));
        popupWindow.setAnimationStyle(R.style.Animation_Bottom_Dialog);
        View.OnClickListener onClickListener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.btn_camera_album){
                    Toast.makeText(AlertPopupActivity.this, "点击拍照按钮", Toast.LENGTH_LONG).show();
                } else if(v.getId() == R.id.btn_camera_cancel){
                    Toast.makeText(AlertPopupActivity.this, "点击取消按钮", Toast.LENGTH_LONG).show();
                }
                popupWindow.dismiss();
            }
        };

        btnAblum = popView.findViewById(R.id.btn_camera_album);
        btnCancel = popView.findViewById(R.id.btn_camera_cancel);

        btnAblum.setOnClickListener(onClickListener);
        btnCancel.setOnClickListener(onClickListener);
        // 设置窗口，设置窗口位置在底部，设置窗口位置x，y
        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);

    }

    protected void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(AlertPopupActivity.this);
        builder.setTitle("提示");  // 设置标题
        builder.setMessage("确定退出吗");  // 设置消息
        builder.setIcon(R.mipmap.ic_launcher);  // 设置icon
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {  // 设置对话框的确认键
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                AlertPopupActivity.this.finish();  // 结束Activity
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();  // 创造dialog并显示
    }

    private DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    Log.i("ansen", "ok");
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    Log.i("ansen", "cancel");
                    break;
            }
        }
    };
}
