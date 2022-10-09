package com.example.blindgamefinal;

import static android.Manifest.permission.RECORD_AUDIO;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import com.example.blindgamefinal.databinding.ActivityLoginBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class login extends AppCompatActivity {

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
    ArrayList<String> num= new ArrayList<>();

int c =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
            player = MediaPlayer.create(this, R.raw.song_login);



        }
        if(player2==null)
        {
            player2 = MediaPlayer.create(this, R.raw.song_invalidnum);



        }
        if(player1==null)
        {
            player1 = MediaPlayer.create(this, R.raw.song_numnotreg);



        }



        player.start();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlayer();
                speechRecognizer.startListening(intentRecogniser);


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
                                number = number.replaceAll("\\s", "");
                                ref.addChildEventListener(new ChildEventListener() {
                                    @Override
                                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                        if(snapshot.getValue().toString().equals(number))
                                        {
                                            openNext();

                                        }


                                    }

                                    @Override
                                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                    }

                                    @Override
                                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                                    }

                                    @Override
                                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                                // mem.setNumber(number);





                            }
                        },4000);

//Log.w("Button number",mem.getNumber());






                    }



                });

            }

        });





    }


    private void openNext() {
        Intent intent = new Intent(this,GameOption.class);
        intent.putExtra("number",number);
        startActivity(intent);
    }
    private void open_signup_in() {
        Intent intent = new Intent(this, signup_in.class);

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