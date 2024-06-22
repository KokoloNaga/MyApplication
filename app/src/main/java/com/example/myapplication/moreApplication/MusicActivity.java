package com.example.myapplication.moreApplication;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

import java.io.IOException;
import java.nio.channels.SeekableByteChannel;
import java.text.SimpleDateFormat;

public class MusicActivity extends AppCompatActivity {
    private boolean isPlay = false;  // 判断播放状态，即是否有歌可播
    private boolean isPause = false;  // 判断暂停状态

    private MediaPlayer mediaPlayer;
    private ImageView ivPlay;
    private SeekBar seekBar;

    private Handler handler = new Handler();

    private TextView tvStart, tvEnd;

    private final Runnable mTicker = new Runnable() {
        @Override
        public void run() {
            long now = SystemClock.currentThreadTimeMillis();   // 当前时间
            long next = now + (1000 - now % 1000);  // 即将的整秒

            handler.postAtTime(mTicker, next);  // 延迟到整秒，并之后每一秒更新

            if(mediaPlayer != null && isPlay && !isPause){  // 正在播放
                seekBar.setProgress(mediaPlayer.getCurrentPosition());  // 音频条更新
                tvStart.setText(getTimeStr(mediaPlayer.getCurrentPosition()));  // 更新开始时间
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_music);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ivPlay = findViewById(R.id.iv_play);
        ivPlay.setOnClickListener(onClickListener);
        seekBar = findViewById(R.id.seekbar);
        seekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);

        tvStart = findViewById(R.id.tv_start_time);
        tvEnd = findViewById(R.id.tv_end_time);


    }
    // 监听play
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.iv_play){
                if(isPlay){
                    if(isPause){  // 当前暂停中，准备启动
                        mediaPlayer.start();
                        isPause = false;
                        ivPlay.setImageResource(android.R.drawable.ic_media_pause);
                    }else {  // 当前未暂停，准备暂停
                        mediaPlayer.pause();
                        isPause = true;
                        ivPlay.setImageResource(android.R.drawable.ic_media_play);
                        handler.removeCallbacks(mTicker);  // 删除Runable计时器mTicker
                    }
                }else {  // 尚未播放过
                    playMusic();
                    ivPlay.setImageResource(android.R.drawable.ic_media_pause);
                    isPlay = true;
                }
            }
        }
    };

    public void playMusic(){
        try {
            mediaPlayer = new MediaPlayer();
            Uri uri = Uri.parse("android.resource://com.ansen.playmusic/");
            mediaPlayer.setDataSource(MusicActivity.this, uri);  // 设置资源，资源可以是文件，url，SD卡路径
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);  // 设置播放类型
            mediaPlayer.setOnCompletionListener(onCompletionListener);  // 播放完成监听

            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {  // 设置预加载监听
                @Override
                public void onPrepared(MediaPlayer mp) {
                    seekBar.setMax(mediaPlayer.getDuration());  // 设置seekBar的进度
                    mediaPlayer.start();    // 开始播放
                    handler.post(mTicker);  // 更新进度
                    tvEnd.setText(getTimeStr(mediaPlayer.getDuration()));   // 将歌曲时长转成标准格式
                }
            });

            mediaPlayer.prepare();  // 预加载
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // 监听时间条变动
    private SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // 进度改变
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // 开始拖动进度
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // 停止拖动
            if(mediaPlayer != null && isPlay){
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        }
    };

    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {  // 播放完成
            isPause = true;  // 进入暂停状态
            ivPlay.setImageResource(android.R.drawable.ic_media_play);
            mediaPlayer.seekTo(0);  // 音频归零
            seekBar.setProgress(0);     // 进度归零
            tvStart.setText("00:00:00");
            handler.removeCallbacks(mTicker);   // 终止计时器
        }
    };

    // 将时间转换成标准格式
    private String getTimeStr(long time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        return simpleDateFormat.format(time);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        isPause = true;
        mediaPlayer.stop();
        mediaPlayer.reset();
        mediaPlayer.release();
        mediaPlayer = null;
    }
}