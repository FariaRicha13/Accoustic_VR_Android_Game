package com.example.blindgamefinal;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

public class Seg1Out1 extends AppCompatActivity {
    MediaPlayer player;
    String number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        number = getIntent().getStringExtra("number");
        setContentView(R.layout.activity_seg1_out1);
        if(player==null)
        {
            player = MediaPlayer.create(this,R.raw.song_l1s1o1);

        }

        player.start();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                openSeg2();

            }
        });

    }

    private void openSeg2() {
        Intent intent = new Intent(this, Segmen2.class);
        intent.putExtra("number",number);
        startActivity(intent);
    }

    private void stopPlayer()
    {
        if(player!=null)
        {
            player.release();
            player=null;

        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
    }
}