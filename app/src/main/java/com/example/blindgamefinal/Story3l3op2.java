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

public class Story3l3op2 extends AppCompatActivity {
    private Button b1;

    public int mcount ,ncount;


    RelativeLayout relativeLayout;
    MediaPlayer player,player1,player2,player3,player4,player5,player6,player7,player8,player9,players;
    String number,val1,val2,val3,val4,val5,seg,key,val;
    FirebaseDatabase database;
    DatabaseReference ref,ref1;
    Integer num;
    float sum;
    float fnum;
    HoldSum s = new HoldSum();
    Integer count=0;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story3l3op2);
        b1=findViewById(R.id.b1);
        relativeLayout = findViewById(R.id.relative_lay);
        number = getIntent().getStringExtra("number");
        ref=database.getInstance().getReference("Score").child("L3");
        ref1 = database.getInstance().getReference("Final Score").child("L3").child(number);
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
            player = MediaPlayer.create(this, R.raw.song_l3s3o2);


        }
        if(player1==null)
        {
            player1 = MediaPlayer.create(this,R.raw.song_1star);

        }
        if(player2==null)
        {
            player2 = MediaPlayer.create(this,R.raw.song_15star);

        }
        if(player3==null)
        {
            player3= MediaPlayer.create(this,R.raw.song_2star);

        }
        if(player4==null)
        {
            player4 = MediaPlayer.create(this,R.raw.song_25star);

        }
        if(player5==null)
        {
            player5 = MediaPlayer.create(this,R.raw.song_3star);

        }
        if(player6==null)
        {
            player6 = MediaPlayer.create(this,R.raw.song_35star);

        }
        if(player7==null)
        {
            player7 = MediaPlayer.create(this,R.raw.song_4star);

        }
        if(player8==null)
        {
            player8= MediaPlayer.create(this,R.raw.song_45star);

        }
        if(player9==null)
        {
            player9= MediaPlayer.create(this,R.raw.song_5star);

        }
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(snapshot.child(number).exists())
                {
                    seg = snapshot.getKey();

                    Log.w("Value of keys",seg);
                    val=snapshot.child(number).getValue().toString();
                    Log.w("Value of child number",val);
                    num= Integer.parseInt(val);
                    count=num+count;
                    Log.w("Val of sum inside", String.valueOf(count));
                    s.setSum(count);
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

        player.start();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.pause();
                speechRecognizer.startListening(intentRecogniser);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if(textView.getText().toString().equals("fast forward"))
                        {


                            player.seekTo(player.getCurrentPosition()+10000);
                            player.start();

                        }
                        if(textView.getText().toString().equals("fast backward"))
                        {


                            player.seekTo(player.getCurrentPosition()-10000);
                            player.start();

                        }

                        if(textView.getText().toString().equals("quit"))
                        {
                            stopPlayer();
                            openGameOption();
                        }




                        int x = hcnt.getCnt();
                        Log.w("Val of x", String.valueOf(x));
                        if(x==1)
                        {
                            speechRecognizer.stopListening();
                            Log.w("",gameopt);

                        }

                    }
                },4000);

            }



        });






        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                sum=(float)s.getSum();
                Log.w("Value from class", String.valueOf(sum));
                fnum= (float) (((sum+6.0)*(1.0-5.0))/((-6.0-10.0))+1.0);
                Log.w("Value after calculation", String.valueOf(fnum));
                fnum= (float) ((Math.round(fnum * 100))/100.0);
                val1= String.valueOf(fnum);
                Log.w("Value of val", String.valueOf(val1));
                ref1.setValue(val1);
                if(fnum>=1.0&&fnum<1.5)
                {
                    player1.start();
                    player1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            openStory2();
                        }
                    });
                }
                if(fnum>=1.5&&fnum<2.0)
                {
                    player2.start();
                    player2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            openStory2();
                        }
                    });
                }
                if(fnum>=2.0&&fnum<2.5)
                {
                    player3.start();
                    player3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            openStory2();
                        }
                    });
                }
                if(fnum>=2.5&&fnum<3.0)
                {
                    player4.start();
                    player4.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            openStory2();
                        }
                    });
                }
                if(fnum>=3.0&&fnum<3.5)
                {
                    player5.start();
                    player5.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            openStory2();
                        }
                    });
                }
                if(fnum>=3.5&&fnum<4.0)
                {
                    player6.start();
                    player6.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            openStory2();
                        }
                    });
                }
                if(fnum>=4.0&&fnum<4.5)
                {
                    player7.start();
                    player7.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            openStory2();
                        }
                    });
                }
                if(fnum>=4.5&&fnum<5)
                {
                    player8.start();
                    player8.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            openStory2();
                        }
                    });
                }
                if(fnum==5.0)
                {
                    player9.start();
                    player9.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            openStory2();
                        }
                    });
                }

            }
        });


    }
    private void openGameOption() {

        Intent intent = new Intent(this, GameOption.class);
        intent.putExtra("number",number);
        intent.putExtra("Game State", "Story3l3op2");
        startActivity(intent);
    }

    private void openStory2() {

        /*Intent intent = new Intent(this, Story3Seg1.class);
        intent.putExtra("number",number);
        startActivity(intent);*/
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