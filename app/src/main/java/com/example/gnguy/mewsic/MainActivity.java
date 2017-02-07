package com.example.gnguy.mewsic;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton play_Icon = (ImageButton)findViewById(R.id.play_Icon);
        ImageButton pause_Icon = (ImageButton)findViewById(R.id.pause_Icon);
        ImageButton stop_Icon = (ImageButton)findViewById(R.id.stop_Icon);

        final MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.song);


        play_Icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
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
