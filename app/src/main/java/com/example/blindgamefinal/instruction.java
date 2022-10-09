package com.example.blindgamefinal;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.blindgamefinal.databinding.ActivityInstructionBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class instruction extends AppCompatActivity {


    MediaPlayer player;
    private int mcount = 0;
    FirebaseDatabase database;
    String userid;

    //  Count hcnt = new Count();

    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);

        if(player==null)
        {
            player = MediaPlayer.create(this,R.raw.song_ins);
        }
        player.start();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlayer();
                openNext();

            }

        });






    }

    private void openNext() {
        Intent intent = new Intent(this,GameOption.class);

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