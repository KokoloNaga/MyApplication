package com.example.myapplication.moreApplication;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class CopyPasteActivity extends AppCompatActivity {
    private EditText editText;
    private TextView textView;
    private Button copyButton;
    private Button pasteButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_copy_paste);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        copyButton = findViewById(R.id.copyButton);
        pasteButton = findViewById(R.id.pasteButton);

        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取输入的文本
                String textToCopy = editText.getText().toString();
                // 获取剪贴板管理器实例
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 创建一个新的ClipData对象
                ClipData clip = ClipData.newPlainText("label", textToCopy);
                // 将ClipData对象设置到剪贴板中
                clipboard.setPrimaryClip(clip);
                // 提示用户复制成功
                Toast.makeText(CopyPasteActivity.this, "文本已复制", Toast.LENGTH_SHORT).show();
            }
        });

        pasteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取剪贴板管理器实例
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 检查剪贴板是否包含数据
                if (clipboard.hasPrimaryClip()) {
                    // 获取剪贴板上的ClipData对象
                    ClipData clip = clipboard.getPrimaryClip();
                    // 获取第一个剪贴板数据项
                    ClipData.Item item = clip.getItemAt(0);
                    // 获取数据并转换为字符串
                    String pastedText = item.getText().toString();
                    // 显示粘贴的文本
                    textView.setText(pastedText);
                    // 提示用户粘贴成功
                    Toast.makeText(CopyPasteActivity.this, "文本已粘贴", Toast.LENGTH_SHORT).show();
                } else {
                    // 提示用户剪贴板上没有数据
                    Toast.makeText(CopyPasteActivity.this, "剪贴板上没有数据", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

}