package com.example.myapplication.normalView;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class RadioCheckActivity extends AppCompatActivity {
    private RadioGroup rgSex;
    private String javaResult = "", phpResult = "", cResult = "";
    private CheckBox cbJava, cbPHP, cbC;
    private TextView tvLabel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_radio_check);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rgSex = findViewById(R.id.radioGroup);
        rgSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rb_male){
                    Toast.makeText(RadioCheckActivity.this, "您的性别是男", Toast.LENGTH_LONG).show();
                } else if(checkedId == R.id.rb_female){
                    Toast.makeText(RadioCheckActivity.this, "您的性别是女", Toast.LENGTH_LONG).show();
                }
            }
        });

        cbJava = findViewById(R.id.cb_java);
        cbPHP = findViewById(R.id.cb_php);
        cbC = findViewById(R.id.cb_c);
        tvLabel = findViewById(R.id.tv_result);
        // 针对同一组的Checkbox设置同一个checkedChangedlistener
        cbJava.setOnCheckedChangeListener(checkedChangeListener);
        cbPHP.setOnCheckedChangeListener(checkedChangeListener);
        cbC.setOnCheckedChangeListener(checkedChangeListener);

    }

    private CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(buttonView.getId() == R.id.cb_java){
                javaResult = isChecked?"Java":"";
            } else if(buttonView.getId() == R.id.cb_php){
                phpResult = isChecked?"PHP":"";
            } else if(buttonView.getId() == R.id.cb_c){
                cResult = isChecked?"C":"";
            }

            tvLabel.setText(javaResult + " " + phpResult + " " + cResult);
        }
    };
}