package com.example.blindgamefinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.blindgamefinal.Count;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private Button b1;
    MediaPlayer player;
    private int mcount = 0;
    FirebaseDatabase database;
    String userid;

  //  Count hcnt = new Count();

    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(player==null)
        {
            player = MediaPlayer.create(this,R.raw.song);
        }
        player.start();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlayer();
                b1=findViewById(R.id.b1);
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       openNext();
                    }
                });
            }

        });






    }

    private void openNext() {
        Intent intent = new Intent(this,signup_in.class);

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