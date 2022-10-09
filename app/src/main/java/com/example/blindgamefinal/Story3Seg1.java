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

public class Story3Seg1 extends AppCompatActivity {
    private Button b1;
    int maxid;
    public int mcount ,ncount;
    SeekBar seekBar;
    String x;
    String value;
    int duration;
    String number;
    RelativeLayout relativeLayout;
    DatabaseReference ref,ref2,ref3;
    MediaPlayer player,player1,player2,player3,player4,player5,player6;
    ArrayList<String> myArrayList = new ArrayList<>();
    Runnable runnable;
    String userid;
    public String gameopt;
    private SpeechRecognizer speechRecognizer;
    private Intent intentRecogniser;
    private TextView textView;
    ArrayList<Integer>countsum = new ArrayList<>();
    Count hcnt = new Count(mcount);
    Handler handler = new Handler();
FirebaseDatabase database;
Boolean flag=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star_game1);
        b1=findViewById(R.id.b1);
        relativeLayout = findViewById(R.id.relative_lay);
        number = getIntent().getStringExtra("number");
        ActivityCompat.requestPermissions(this,new String[]{RECORD_AUDIO}, PackageManager.PERMISSION_GRANTED);
        textView = findViewById(R.id.textView);
        ref = database.getInstance().getReference("Score").child("L3").child("S1");

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
            player = MediaPlayer.create(this, R.raw.song_l3s1);


        }
        if(player1==null)
        {
            player1 = MediaPlayer.create(this, R.raw.song_choice);


        }
        if(player2==null)
        {
            player2 = MediaPlayer.create(this,R.raw.song_voiceerror);


        }

        player.start();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.pause();
                flag = true;
                while (flag == true) {
                speechRecognizer.startListening(intentRecogniser);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.wtf("outside the loop val of maxid", String.valueOf(hcnt.getCnt()));
                        /*  */

                        Log.w("Start", (textView.getText().toString()));
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
                        {flag=false;
                            stopPlayer();

                            openGameOption();
                        }
                        if(textView.getText().toString().equals("next"))
                        {
                            player1.start();
                        }
                        if(textView.getText().toString().equals("option one"))
                        {flag=false;
                            stopPlayer();
                            ref.child(number).setValue("2");
                            openSeg2_1();
                        }
                        else if((textView.getText().toString().equals("option two")||(textView.getText().toString().equals("option 2"))))
                        {flag=false;
                            stopPlayer();
                            ref.child(number).setValue("5");
                            openSeg2_2();
                        }
                        else if((textView.getText().toString().equals("option three")||(textView.getText().toString().equals("option 3"))))
                        {flag=false;
                            stopPlayer();
                            ref.child(number).setValue("-3");
                            openSeg2_3();
                        }


                        if((!textView.getText().toString().equals("fast forward"))&&(!textView.getText().toString().equals("fast backward"))&&(!textView.getText().toString().equals("quit"))&&(!textView.getText().toString().equals("next"))&&(!textView.getText().toString().equals("option one"))&&(!textView.getText().toString().equals("option 2"))&&(!textView.getText().toString().equals("option 3")) ) {
                            player2.start();
                            player2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    flag = true;

                                }
                            });
                        }

                    }
                },4000);
                    break;
                }


            }



        });





        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlayer();

                //  ref = database.getInstance().getReference("root").child("p1");
                Log.wtf("hello", String.valueOf(mcount));

                mcount=0;
                flag = true;
                while (flag == true) {

                speechRecognizer.startListening(intentRecogniser);


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {



                        if(textView.getText().toString().equals("quit"))
                        {flag=false;
                            stopPlayer();
                            openGameOption();
                        }
                        if(textView.getText().toString().equals("next"))
                        {

                            player1.start();
                        }
                        if(textView.getText().toString().equals("option one"))
                        {flag=false;
                            stopPlayer();
                            ref.child(number).setValue("2");
                            openSeg2_1();

                           //openSeg10();
                        }
                        else if((textView.getText().toString().equals("option two")||(textView.getText().toString().equals("option 2"))))
                        {flag=false;
                            stopPlayer();
                            ref.child(number).setValue("5");
                            openSeg2_2();
                            //openSeg11();
                        }
                        else if((textView.getText().toString().equals("option three")||(textView.getText().toString().equals("option 3"))))
                        {stopPlayer();
                            ref.child(number).setValue("-3");
                            openSeg2_3();
                        }

                        if((!textView.getText().toString().equals("fast forward"))&&(!textView.getText().toString().equals("fast backward"))&&(!textView.getText().toString().equals("quit"))&&(!textView.getText().toString().equals("next"))&&(!textView.getText().toString().equals("option one"))&&(!textView.getText().toString().equals("option 2"))&&(!textView.getText().toString().equals("option 3")) )
                        {
                            player2.start();
                            player2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {

                                    b1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            player.pause();
                                            flag = true;
                                            while (flag == true) {
                                                speechRecognizer.startListening(intentRecogniser);
                                                new Handler().postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        Log.wtf("outside the loop val of maxid", String.valueOf(hcnt.getCnt()));
                                                        /*  */

                                                        Log.w("Start", (textView.getText().toString()));
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
                                                        {flag=false;
                                                            stopPlayer();

                                                            openGameOption();
                                                        }
                                                        if(textView.getText().toString().equals("next"))
                                                        {flag=false;
                                                            player1.start();
                                                        }
                                                        if(textView.getText().toString().equals("option one"))
                                                        {flag=false;
                                                            stopPlayer();
                                                            ref.child(number).setValue("2");
                                                            openSeg2_1();
                                                        }
                                                        else if((textView.getText().toString().equals("option two")||(textView.getText().toString().equals("option 2"))))
                                                        {flag=false;
                                                            stopPlayer();
                                                            ref.child(number).setValue("5");
                                                            openSeg2_2();
                                                        }
                                                        else if((textView.getText().toString().equals("option three")||(textView.getText().toString().equals("option 3"))))
                                                        {flag=false;
                                                            stopPlayer();
                                                            ref.child(number).setValue("-3");
                                                            openSeg2_3();
                                                        }


                                                        if((!textView.getText().toString().equals("fast forward"))&&(!textView.getText().toString().equals("fast backward"))&&(!textView.getText().toString().equals("quit"))&&(!textView.getText().toString().equals("next"))&&(!textView.getText().toString().equals("option one"))&&(!textView.getText().toString().equals("option 2"))&&(!textView.getText().toString().equals("option 3")) ) {
                                                            player2.start();
                                                            player2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                                @Override
                                                                public void onCompletion(MediaPlayer mp) {
                                                                    flag = true;

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
        intent.putExtra("Game State", "Story3_seg1");
        startActivity(intent);
    }

    private void  openSeg2_1() {
        Intent intent = new Intent(this, Story3seg1_Out1.class);
        intent.putExtra("number",number);
        startActivity(intent);
    }
    private void  openSeg2_2() {
        Intent intent = new Intent(this, Story3seg1_Out2.class);
        intent.putExtra("number",number);
        startActivity(intent);
    }
    private void  openSeg2_3() {
        Intent intent = new Intent(this, Story3seg1_Out3.class);
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