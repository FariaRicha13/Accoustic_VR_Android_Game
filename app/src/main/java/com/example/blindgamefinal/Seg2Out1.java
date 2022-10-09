package com.example.blindgamefinal;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

public class   Seg2Out1 extends AppCompatActivity {
    MediaPlayer player;
    String number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seg2_out1);
        number = getIntent().getStringExtra("number");
        if(player==null)
        {
            player = MediaPlayer.create(this,R.raw.song_l1s2o1);

        }

        player.start();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                openSeg3();

            }
        });

    }

    private void openSeg3() {
        Intent intent = new Intent(this, Segment3.class);
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