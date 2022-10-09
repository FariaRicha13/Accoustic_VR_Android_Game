package com.example.blindgamefinal;

import static android.Manifest.permission.RECORD_AUDIO;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.blindgamefinal.databinding.ActivitySignupInBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class signup_in extends AppCompatActivity {

    private Button b1;

    public int mcount ,ncount;

    public String gameopt;
    private SpeechRecognizer speechRecognizer;
    private Intent intentRecogniser;
    private TextView textView;



    RelativeLayout relativeLayout;
    DatabaseReference ref,ref2,ref3;
    MediaPlayer player,player1,player2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star_game1);
        b1=findViewById(R.id.b1);
        relativeLayout = findViewById(R.id.relative_lay);

        ActivityCompat.requestPermissions(this,new String[]{RECORD_AUDIO}, PackageManager.PERMISSION_GRANTED);
        textView = findViewById(R.id.textView);
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
            player = MediaPlayer.create(this, R.raw.song_signup_login);



        }
        if(player1==null)
        {
            player1 = MediaPlayer.create(this, R.raw.song_voiceerror);



        }


        player.start();



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              player.pause();
            //   player.stop();

                speechRecognizer.startListening(intentRecogniser);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if(textView.getText().toString().equals("register"))
                        {

openReg();


                        }
                        if(textView.getText().toString().equals("login"))
                        {


openLog();

                        }
                        if((!textView.getText().toString().equals("login"))&&(!textView.getText().toString().equals("register")))
                        {
 player1.start();


                        }

                    }
                },4000);

            }



        });


        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override

            public void onCompletion(MediaPlayer mp) {
                stopPlayer();

                mcount=0;

                speechRecognizer.startListening(intentRecogniser);



                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if(textView.getText().toString().equals("register"))
                        {




                        }
                        if(textView.getText().toString().equals("login"))
                        {




                        }

                    }
                },4000);

            }
        });


    }


    private void openReg() {

        Intent intent = new Intent(this, signup.class);

        startActivity(intent);
    }
    private void openLog() {

        Intent intent = new Intent(this,login.class);

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