package com.example.myapplication.moreApplication;

import android.content.Intent;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

import java.io.IOException;

public class VideoActivity extends AppCompatActivity {
    private final String Tag = VideoActivity.class.getSimpleName();

    private MediaPlayer mediaPlayer;
    private Surface surface;

    private ImageView videoImage;
    private SeekBar seekBar;

    private Handler handler = new Handler();

    private final Runnable mTicker = new Runnable() {
        @Override
        public void run() {
            long now = SystemClock.uptimeMillis();
            long next = now + (1000 - now % 1000);

            handler.postAtTime(mTicker, next);

            if(mediaPlayer != null && mediaPlayer.isPlaying()){
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_video);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextureView textureView = findViewById(R.id.textureview);
        textureView.setSurfaceTextureListener(surfaceTextureListener);

        videoImage = findViewById(R.id.iv_video);
        seekBar = findViewById(R.id.sb_video);
        seekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
    }

    // 1.使用自带播放器
    public void selfVideo(){
        String path = Environment.getExternalStorageDirectory() + "/ansen.mp4";  // SD卡内video
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse("file://" + path), "video/mp4");
        startActivity(intent);
    }

    // 2.MediaPlayer+TextureView
    private TextureView.SurfaceTextureListener surfaceTextureListener = new TextureView.SurfaceTextureListener() {
        @Override
        public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surfaceTexture, int width, int height) {
            // Surface可用时

            surface = new Surface(surfaceTexture);
            new PlayerVideoThread().start();

            handler.post(mTicker);
        }

        @Override
        public void onSurfaceTextureSizeChanged(@NonNull SurfaceTexture surfaceTexture, int width, int height) {
            // 尺寸改变


        }

        @Override
        public boolean onSurfaceTextureDestroyed(@NonNull SurfaceTexture surfaceTexture) {
            // 销毁
            surface = null;
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            return true;
        }

        @Override
        public void onSurfaceTextureUpdated(@NonNull SurfaceTexture surfaceTexture) {
            // 更新
        }
    };

    private SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // 进度改变
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // 开始拖动
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // 停下拖动
            if(mediaPlayer != null && mediaPlayer.isPlaying()){
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        }
    };

    private class PlayerVideoThread extends Thread{
        @Override
        public void run() {
            super.run();

            try {
                mediaPlayer = new MediaPlayer();
                Uri uri = Uri.parse("android.resource://com.example.textureviewvideo/");
                mediaPlayer.setDataSource(VideoActivity.this, uri); // 与music一致
                mediaPlayer.setSurface(surface);  // 设置渲染画板
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(onCompletionListener);
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        videoImage.setVisibility(View.GONE);
                        mediaPlayer.start();

                        seekBar.setMax(mediaPlayer.getDuration());
                    }
                });

                mediaPlayer.prepare();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            videoImage.setVisibility(View.VISIBLE);
            seekBar.setProgress(0);
            handler.removeCallbacks(mTicker);
        }
    };

}