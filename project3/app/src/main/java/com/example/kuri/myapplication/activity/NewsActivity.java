package com.example.kuri.myapplication.activity;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.kuri.myapplication.R;

public class NewsActivity extends Activity implements View.OnClickListener,
        SeekBar.OnSeekBarChangeListener {
    private Button play, pause, stop;
    private MediaPlayer player;
    private SeekBar mSeekBar;
    private TextView tv, tv2;
    private boolean hadDestroy = false;
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {

            switch (msg.what) {
                case 0x01:

                    break;

                default:
                    break;
            }
        };
    };
    Runnable runnable = new Runnable() {

        @Override
        public void run() {

            if (!hadDestroy) {
                mHandler.postDelayed(this, 1000);
                int currentTime = Math
                        .round(player.getCurrentPosition() / 1000);
                String currentStr = String.format("%s%02d:%02d", "当前时间 ",
                        currentTime / 60, currentTime % 60);
                tv.setText(currentStr);
                mSeekBar.setProgress(player.getCurrentPosition());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play = (Button) findViewById(R.id.play);
        pause = (Button) findViewById(R.id.pause);
        stop = (Button) findViewById(R.id.stop);
        mSeekBar = (SeekBar) findViewById(R.id.seekbar);
        tv = (TextView) findViewById(R.id.tv);
        tv2 = (TextView) findViewById(R.id.tv2);
        mSeekBar.setOnSeekBarChangeListener(this);
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);
        player = new MediaPlayer();
        initMediaplayer();

    }

    /**
     * 初始化播放器
     */
    private void initMediaplayer() {
        try {
            player.setDataSource("http://www.citynorth.cn/music/confucius.mp3");
            Log.e("播放器", "http://www.citynorth.cn/music/confucius.mp3");
            player.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play:
                if (!player.isPlaying()) {
                    player.start();
                    int totalTime = Math.round(player.getDuration() / 1000);
                    String str = String.format("%02d:%02d", totalTime / 60,
                            totalTime % 60);
                    tv2.setText(str);
                    mSeekBar.setMax(player.getDuration());
                    mHandler.postDelayed(runnable, 1000);
                }

                break;
            case R.id.pause:
                if (player.isPlaying()) {
                    player.pause();
                }
                break;
            case R.id.stop:
                if (player.isPlaying()) {
                    player.reset();
                    initMediaplayer();
                }
                break;

            default:
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress,
                                  boolean fromUser) {
        if (player != null) {
            player.seekTo(seekBar.getProgress());
        }

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // TODO 自动生成的方法存根

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // TODO 自动生成的方法存根
    }

    @Override
    protected void onDestroy() {
        // TODO 自动生成的方法存根
        super.onDestroy();
        if (player != null) {
            player.stop();
            hadDestroy = true;
            player.release();
        }
    }

}
