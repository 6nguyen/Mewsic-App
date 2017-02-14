package com.example.gnguy.mewsic;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.app.Activity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.song);
        final int MAX_VOLUME = 100;
        AudioManager audioManager;
        SeekBar volumeControl;
        TextView volumeText;

        volumeControl = (SeekBar)findViewById(R.id.volume_control);
        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        volumeControl.setMax(MAX_VOLUME);
        volumeControl.setProgress(MAX_VOLUME*audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)/audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));

        SeekBar.OnSeekBarChangeListener volumeControlListener = new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                float volume = (float)(1 - (Math.log(MAX_VOLUME - progress)/Math.log(MAX_VOLUME)));
                mp.setVolume(volume, volume);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        };

        volumeControl.setOnSeekBarChangeListener(volumeControlListener);
        ImageButton play_Icon = (ImageButton)findViewById(R.id.play_Icon);
        ImageButton pause_Icon = (ImageButton)findViewById(R.id.pause_Icon);
        ImageButton stop_Icon = (ImageButton)findViewById(R.id.stop_Icon);

        play_Icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mp.isPlaying()) {
                    mp.pause();
                } else {
                    mp.start();
                }
            }
        });

        pause_Icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.pause();
            }
        });

        stop_Icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                MainActivity.this.finish();
            }
        });
    }
}
