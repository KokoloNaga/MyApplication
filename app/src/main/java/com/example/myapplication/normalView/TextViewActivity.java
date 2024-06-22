package com.example.myapplication.normalView;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class TextViewActivity extends AppCompatActivity {
    public TextView tvXml, tvFromHtml, tvToast, tvSpan, tvSpanClick, tvClickBackground;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_text_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 通过xml修改内容
        tvXml = findViewById(R.id.tv_xml_act);
        // tvXml.setText(getString(R.string.testing, new Integer[]{11, 21, 31}));

        // 直接以html语句设置TextView的内容及其字体
        String html = "<font color = 'red'>TextView显示html字体颜色为红色</font><br/>";
        tvFromHtml = findViewById(R.id.tv_fromHtml);
        tvFromHtml.setText(Html.fromHtml(html));

        // Click
        tvToast = findViewById(R.id.tv_click);
        tvToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TextViewActivity.this, "点击触发Toast", Toast.LENGTH_LONG).show();
            }
        });

        // 样式类Span
        tvSpan = findViewById(R.id.tv_span);
        SpannableString spannableString = new SpannableString("TextView的样式类Span");
        BackgroundColorSpan backgroundColorSpan = new BackgroundColorSpan(Color.RED);
        spannableString.setSpan(backgroundColorSpan, 0, 10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);  // 切片[0:10]的背景
        tvSpan.setText(spannableString);

        // 通过Span对某段文字设置
        tvSpanClick = findViewById(R.id.tv_span_click);
        SpannableString spannableString2 = new SpannableString("TextView设置点击事件Span");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Toast.makeText(TextViewActivity.this, "触发点击事件", Toast.LENGTH_LONG).show();
            }
        };
        spannableString2.setSpan(clickableSpan, 11, 15, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        tvSpanClick.setMovementMethod(LinkMovementMethod.getInstance());  // 对TextView设置Movement对象
        tvSpanClick.setText(spannableString2);

        // 设置TextView点击背景
        tvClickBackground = findViewById(R.id.tv_click_background);
        tvClickBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TextViewActivity.this, "查看点击背景", Toast.LENGTH_LONG).show();
            }
        });
    }
}