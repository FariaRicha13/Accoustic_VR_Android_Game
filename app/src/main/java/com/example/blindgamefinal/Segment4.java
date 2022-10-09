package com.example.blindgamefinal;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
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
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.Manifest.permission.RECORD_AUDIO;

public class Segment4 extends AppCompatActivity {
    private Button b1;
    int maxid;
    public int mcount ;
    String value;
    public String gameopt;
    private SpeechRecognizer speechRecognizer;
    private Intent intentRecogniser;
    private TextView textView;
    RelativeLayout relativeLayout;
    private GestureDetector gestureDetector;
    DatabaseReference ref,ref2;
    MediaPlayer player,player1;
    ArrayList<String> myArrayList = new ArrayList<>();
FirebaseDatabase database;
    String userid;
    ArrayList<Integer>countsum = new ArrayList<>();
    Count hcnt = new Count(mcount);
String number;
Boolean flag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segment4);
        relativeLayout = findViewById(R.id.relative_lay);
        b1=findViewById(R.id.b1);
        number = getIntent().getStringExtra("number");
        ActivityCompat.requestPermissions(this,new String[]{RECORD_AUDIO}, PackageManager.PERMISSION_GRANTED);
        textView = findViewById(R.id.textView);
        intentRecogniser = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intentRecogniser.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizer = speechRecognizer.createSpeechRecognizer(this);
        ref = database.getInstance().getReference("Score").child("L1").child("S4");
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
            player = MediaPlayer.create(this,R.raw.song_l1s4);


        }
        if(player1==null)
        {
            player1 = MediaPlayer.create(this,R.raw.song_voiceerror);


        }

        player.start();


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.pause();
                flag = true;
                while (flag == true) {
                    speechRecognizer.startListening(intentRecogniser);
                    Log.w("Flag", String.valueOf(flag));

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            Log.w("flag inside", String.valueOf(flag));
                            if (textView.getText().toString().equals("fast forward")) {
                                flag = false;

                                player.seekTo(player.getCurrentPosition() + 10000);

                                player.start();


                            }
                            if (textView.getText().toString().equals("fast backward")) {
                                flag = false;

                                player.seekTo(player.getCurrentPosition() - 10000);
                                player.start();

                            }

                            if (textView.getText().toString().equals("quit")) {
                                flag = false;
                                stopPlayer();
                                openGameOption();
                            }
                            if (textView.getText().toString().equals("next")) {
                                flag = false;
                                stopPlayer();

                                OpenNext();
                            }
                            if (textView.getText().toString().equals("option one")) {
                                Log.w("Num", number);
                                flag = false;
                                stopPlayer();
                                ref.child(number).setValue("5");

                                openSeg4Out1();
                            } else if (textView.getText().toString().equals(("option 2"))) {
                                flag = false;
                                stopPlayer();
                                ref.child(number).setValue("2");
                                openSeg4Out2();
                            } else if (textView.getText().toString().equals(("option two"))) {
                                flag = false;
                                stopPlayer();
                                ref.child(number).setValue("2");
                                openSeg4Out2();
                            } else if (textView.getText().toString().equals("option three")) {
                                flag = false;
                                stopPlayer();
                                ref.child(number).setValue("-2");
                                openSeg4Out3();
                            } else if (textView.getText().toString().equals("option 3")) {
                                flag = false;
                                stopPlayer();
                                ref.child(number).setValue("-2");
                                openSeg4Out3();
                            }   if((!textView.getText().toString().equals("fast forward"))&&(!textView.getText().toString().equals("fast backward"))&&(!textView.getText().toString().equals("quit"))&&(!textView.getText().toString().equals("next"))&&(!textView.getText().toString().equals("option one"))&&(!textView.getText().toString().equals("option 2"))&&(!textView.getText().toString().equals("option 3")) )
                            {
                                player1.start();
                                player1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    @Override
                                    public void onCompletion(MediaPlayer mp) {
                                        flag = true;

                                    }
                                });


                            }
                        }


                    }, 4000);
                    break;
                }

            }




        });




        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override

            public void onCompletion(MediaPlayer mp) {
                stopPlayer();



                flag=true;
                while (flag==true)
                {
                    speechRecognizer.startListening(intentRecogniser);



                    new Handler().postDelayed(new Runnable() {


                        @Override
                        public void run() {
                            if(textView.getText().toString().equals("fast forward"))
                            {
                                flag=false;

                                player.seekTo(player.getCurrentPosition()+10000);
                                player.start();

                            }
                            if(textView.getText().toString().equals("fast backward"))
                            {

                                flag=false;
                                player.seekTo(player.getCurrentPosition()-10000);
                                player.start();

                            }

                            if(textView.getText().toString().equals("quit"))
                            {
                                flag=false;
                                openGameOption();
                            }
                            if(textView.getText().toString().equals("next"))
                            {flag=false;

                                OpenNext();
                            }
                            if(textView.getText().toString().equals("option one"))
                            {
                                flag=false;
                                ref.child(number).setValue("5");
                                openSeg4Out1();
                            }
                            else if(textView.getText().toString().equals("option two"))
                            {
                                flag=false;
                                ref.child(number).setValue("2");
                                openSeg4Out2();
                            }
                            else if(textView.getText().toString().equals("option 2"))
                            {flag=false;

                                ref.child(number).setValue("2");
                                openSeg4Out2();
                            }
                            else if(textView.getText().toString().equals("option three"))
                            {flag=false;

                                ref.child(number).setValue("-2");
                                openSeg4Out3();
                            }
                            else if(textView.getText().toString().equals("option 3"))
                            {        flag=false;
                                ref.child(number).setValue("-2");
                                openSeg4Out3();
                            }
                            if((!textView.getText().toString().equals("fast forward"))&&(!textView.getText().toString().equals("fast backward"))&&(!textView.getText().toString().equals("quit"))&&(!textView.getText().toString().equals("next"))&&(!textView.getText().toString().equals("option one"))&&(!textView.getText().toString().equals("option 2"))&&(!textView.getText().toString().equals("option 3")) )
                            {
                                player1.start();
                                player1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    @Override
                                    public void onCompletion(MediaPlayer mp) {
                                        b1.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                player1.pause();
                                                flag = true;
                                                while (flag == true) {
                                                    speechRecognizer.startListening(intentRecogniser);
                                                    Log.w("Flag", String.valueOf(flag));

                                                    new Handler().postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {

                                                            Log.w("flag inside", String.valueOf(flag));
                                                            if (textView.getText().toString().equals("fast forward")) {
                                                                flag = false;

                                                                player.seekTo(player.getCurrentPosition() + 10000);

                                                                player.start();


                                                            }
                                                            if (textView.getText().toString().equals("fast backward")) {
                                                                flag = false;

                                                                player.seekTo(player.getCurrentPosition() - 10000);
                                                                player.start();

                                                            }

                                                            if (textView.getText().toString().equals("quit")) {
                                                                flag = false;
                                                                stopPlayer();
                                                                openGameOption();
                                                            }
                                                            if (textView.getText().toString().equals("next")) {
                                                                flag = false;
                                                                stopPlayer();

                                                                OpenNext();
                                                            }
                                                            if (textView.getText().toString().equals("option one")) {
                                                                Log.w("Num", number);
                                                                flag = false;
                                                                stopPlayer();
                                                                ref.child(number).setValue("5");

                                                                openSeg4Out1();
                                                            } else if (textView.getText().toString().equals(("option 2"))) {
                                                                flag = false;
                                                                stopPlayer();
                                                                ref.child(number).setValue("2");
                                                                openSeg4Out2();
                                                            } else if (textView.getText().toString().equals(("option two"))) {
                                                                flag = false;
                                                                stopPlayer();
                                                                ref.child(number).setValue("2");
                                                                openSeg4Out2();
                                                            } else if (textView.getText().toString().equals("option three")) {
                                                                flag = false;
                                                                stopPlayer();
                                                                ref.child(number).setValue("-2");
                                                                openSeg4Out3();
                                                            } else if (textView.getText().toString().equals("option 3")) {
                                                                flag = false;
                                                                stopPlayer();
                                                                ref.child(number).setValue("-2");
                                                                openSeg4Out3();
                                                            }   if((!textView.getText().toString().equals("fast forward"))&&(!textView.getText().toString().equals("fast backward"))&&(!textView.getText().toString().equals("quit"))&&(!textView.getText().toString().equals("next"))&&(!textView.getText().toString().equals("option one"))&&(!textView.getText().toString().equals("option 2"))&&(!textView.getText().toString().equals("option 3")) )
                                                            {
                                                                player1.start();
                                                                player1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                                    @Override
                                                                    public void onCompletion(MediaPlayer mp) {
                                                                        flag = true;

                                                                    }
                                                                });


                                                            }
                                                        }


                                                    }, 4000);
                                                    break;
                                                }

                                            }




                                        });

                                    }
                                });

                            }

                        }
                    },4000);
                    break;
                }



            }
        });


    }
    private void openGameOption() {

        Intent intent = new Intent(this, GameOption.class);
        intent.putExtra("number",number);
        intent.putExtra("Game State", "Segment4");
        startActivity(intent);
    }
    private void OpenNext() {

        Intent intent = new Intent(this, Story2seg1.class);
        intent.putExtra("number",number);
        startActivity(intent);
    }

    private void openSeg4Out1() {
        Intent intent = new Intent(this, Seg4Out1.class);
        intent.putExtra("number",number);
        startActivity(intent);
    }

    private void openSeg4Out2() {
        Intent intent = new Intent(this,Seg4Out2.class);
        intent.putExtra("number",number);
        startActivity(intent);
    }
    private void openSeg4Out3() {
        Intent intent = new Intent(this, Seg4Out3.class);
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