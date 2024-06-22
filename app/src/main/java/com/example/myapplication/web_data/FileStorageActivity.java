package com.example.myapplication.web_data;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

import java.io.File;

public class FileStorageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_file_storage);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sdStorage();
        fileStorage();
    }

    // SD
    public void sdStorage(){
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){  // 判断sd是否存在
            File sdCard = Environment.getExternalStorageDirectory();
            Log.i("SD", sdCard.getPath());
        }
    }

    public void fileStorage(){
        File getFileDir = getFilesDir();
        Log.i("FileStorage", getFileDir.getPath());
    }

}