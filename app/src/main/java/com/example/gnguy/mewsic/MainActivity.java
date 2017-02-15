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

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create MediaPlayer object to play R.raw.song
        // set a constant max volume
        // initialize AudioManager and SeekBar to control volume
        final MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.song);
        final int MAX_VOLUME = 100;
        AudioManager audioManager;
        SeekBar volumeControl;

        // match volumeControl with SeekBar created
        // match audioManager with device's audio
        // set max volume of volumeControl
        // set "progress", volume change progression, to visually represent following formula:
        // MAX_VOLUME * volume index of music playback audio stream / maximum volume index for music playback audio stream
        //      i.e. the percent value of the progress level (65.2, 33.0, etc)
        volumeControl = (SeekBar)findViewById(R.id.volume_control);
        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        volumeControl.setMax(MAX_VOLUME);
        volumeControl.setProgress(MAX_VOLUME*audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)/audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));


        /** Clients can use fromUser parameter to distinguish user-initiated change from programmatical change
         * OnProgressBarChanged
         * @param seekBar SeekBar whose progress has changed
         * @param progress the current progress level, from 0 - max level set by volumeControl.setMax(MAX_VOLUME).
         *                 default value of max is 100.
         * @param fromUser true if progress change was initiated by user
         *   Progress change measured by the following formula, from 0 - 1:
         *   1 - [(natural log of MAX_VOLUME - progress level) / natural log of MAX_VOLUME]
         *
         *  setVolume(float leftVolume, float rightVolume){}
         *         sets left and right volume of Media Player
         *  @param volume volume of left speaker
         *  @param volume volume of right speaker
         *
         *  onStartTrackingTouch(SeekBar seekBar) {}
         *         notification that the user has started a touch gesture
         *  @param seekBar the SeekBar in which the touch gesture began
         *
         *  onStopTrackingTouch(SeekBar seekBar) {}
         *         notification that the user has finished a touch gesture
         *  @param seekBar the SeekBar in which the touch gesture began
         */
        OnSeekBarChangeListener volumeControlListener = new SeekBar.OnSeekBarChangeListener(){
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

        // Sets volumeControl listener to receive notifications of changes to the SeekBar's progress level.
        // Also provides notifications of when the user starts and stops a touch gesture within the SeekBar
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
