package com.example.blindgamefinal;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.util.TimeUtils;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static android.Manifest.permission.RECORD_AUDIO;

public class signup extends AppCompatActivity {
    private Button b1;
    private FirebaseUser user;
    int maxid;
    public int mcount ,ncount;
    SeekBar seekBar;
    String x;
    String value;
    FirebaseDatabase database;
    public String gameopt;
    private SpeechRecognizer speechRecognizer;
    private Intent intentRecogniser;
    private TextView textView;
private FirebaseAuth mAuth;
    int duration;
String number;
    //SwipeListener swipeListener;
    RelativeLayout relativeLayout;
    DatabaseReference ref,ref1,ref3;
    MediaPlayer player,player1,player2;
    ArrayList<String> myArrayList = new ArrayList<>();
    Runnable runnable;
    String userid;
    ArrayList<Integer>countsum = new ArrayList<>();
    Count hcnt = new Count(mcount);
    Handler handler = new Handler();

    public signup() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        b1=findViewById(R.id.b1);
        relativeLayout = findViewById(R.id.relative_lay);
        member mem;
        ActivityCompat.requestPermissions(this,new String[]{RECORD_AUDIO}, PackageManager.PERMISSION_GRANTED);
        textView = findViewById(R.id.textView);
        mAuth = FirebaseAuth.getInstance();
        user= FirebaseAuth.getInstance().getCurrentUser();
        ref = database.getInstance().getReference().child("User");
//        userid=user.getUid();
        mem = new member();
//        ref1 = database.getInstance().getReference().child("User").child(userid);
        intentRecogniser = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intentRecogniser.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizer = speechRecognizer.createSpeechRecognizer(this);
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {


            }

            @Override
            public void onError(int error) {

            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String>matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                gameopt= "";
                if(matches!=null)
                {
                    gameopt = matches.get(0);

                    textView.setText(gameopt);

                }


            }

            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });
        if(player==null)
        {
            player = MediaPlayer.create(this, R.raw.song_reg);



        }
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    maxid = (int) snapshot.getChildrenCount();
                    maxid++;



                }
                else {//
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        player.start();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlayer();
                speechRecognizer.startListening(intentRecogniser);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.w("Start", (textView.getText().toString()));
                        number =textView.getText().toString();
                        Log.w("IN NUMVER",number);
                        mem.setNumber(number);
                    }
                },6000);


            }

        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  player.pause();
                speechRecognizer.stopListening();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.w("Start", (textView.getText().toString()));
                        number =textView.getText().toString();
                        number=number.replace(" ","");

                        Log.w("IN NUMVER",number);
                        mem.setNumber(number);
                        //number.replaceAll("\\s", "");

                        ref.child(String.valueOf(maxid)).setValue(mem.getNumber());
                        openNext();
                    }
                },4000);

//Log.w("Button number",mem.getNumber());






            }



        });




    }

    private void openNext() {Log.w("outside",number);
        Intent intent = new Intent(this, Story2seg1.class);
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
    protected void Pause() {
        if(player!=null)
            player.pause();
    }






}