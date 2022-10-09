package com.example.blindgamefinal;

import static android.Manifest.permission.RECORD_AUDIO;

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
import com.google.firebase.database.Query;
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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Seg4Out1 extends AppCompatActivity {
    MediaPlayer player,player1,player2,player3,player4,player5,player6,player7,player8,player9,playersp;
    String number,val1,val2,val3,val4,val5,seg,key,val;
    FirebaseDatabase database;
    private Button b1;
    int maxid;
    public int mcount ;
    String value;
    public String gameopt;
    private SpeechRecognizer speechRecognizer;
    private Intent intentRecogniser;
    private TextView textView;
    DatabaseReference ref,ref2,ref3,ref4,ref5,ref1;
    Integer num1,num2,num3,num4,num5,num;
 float sum;
 float fnum;
HoldSum s = new HoldSum();
     Integer count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seg4_out1);
        number = getIntent().getStringExtra("number");
        ref=database.getInstance().getReference("Score").child("L1");
        ref1 = database.getInstance().getReference("Final Score").child("L1").child(number);
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
            player = MediaPlayer.create(this,R.raw.song_l1s4o1);

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
        if(playersp==null)
        {
            playersp= MediaPlayer.create(this,R.raw.song_endchoice);

        }

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                seg = snapshot.getKey();

                Log.w("Value of keys",seg);
                val=snapshot.child(number).getValue().toString();
                Log.w("Value of child number",val);
                num= Integer.parseInt(val);
                count=num+count;
                Log.w("Val of sum inside", String.valueOf(count));
                s.setSum(count);
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
      Log.w("Val of sum outside", String.valueOf(count));
        player.start();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
sum=(float)s.getSum();
Log.w("Value from class", String.valueOf(sum));
fnum= (float) (((sum+13.0)*(1.0-5.0))/((-13.0-20.0))+1.0);
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

            private void openStory2() {
                playersp.start();
                playersp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        speechRecognizer.startListening(intentRecogniser);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {



                                if(textView.getText().toString().equals("quit"))
                                {

                                    openGameOption();
                                }
                                if(textView.getText().toString().equals("proceed"))
                                {

                                    OpenNext();
                                }
                                if(textView.getText().toString().equals("retry"))
                                {

                                    openlevel1();
                                }


                            }
                        },4000);
                    }
                });



            }
        });


    }


    private void  openlevel1() {
        Intent intent = new Intent(this, StarGame1.class);
        intent.putExtra("number",number);
        startActivity(intent);
    }
    private void  OpenNext() {
        Intent intent = new Intent(this, Story2seg1.class);
        intent.putExtra("number",number);
        startActivity(intent);
    }
    private void  openGameOption() {
        Intent intent = new Intent(this, GameOption.class);
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