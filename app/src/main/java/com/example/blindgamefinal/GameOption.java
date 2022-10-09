package com.example.blindgamefinal;

import android.Manifest;
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
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PublicKey;
import java.util.ArrayList;

import static android.Manifest.permission.RECORD_AUDIO;

public class GameOption extends AppCompatActivity {
    private Button b1;
      int maxid;
    public int mcount ;
String value;
public String gameopt;
private SpeechRecognizer speechRecognizer;
private Intent intentRecogniser;
private TextView textView;
    DatabaseReference ref,ref2;
    MediaPlayer player,player1;
    ArrayList<String> myArrayList = new ArrayList<>();
String Value;
    RelativeLayout relativeLayout;
    String userid;
    ArrayList<Integer>countsum = new ArrayList<>();
    Count hcnt = new Count(mcount);
boolean flag = false;
String number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_option);
        b1=findViewById(R.id.b1);
        relativeLayout = findViewById(R.id.relative_lay);
Log.w("Hello","hello");
        ActivityCompat.requestPermissions(this,new String[]{RECORD_AUDIO}, PackageManager.PERMISSION_GRANTED);
       textView = findViewById(R.id.textView);
        number = getIntent().getStringExtra("number");
       Value = getIntent().getStringExtra("Game State");
        intentRecogniser = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intentRecogniser.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
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
                Log.w("Text","Hello");
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
            player = MediaPlayer.create(this,R.raw.song_menu);
        }
        if(player1==null)
        {
            player1 = MediaPlayer.create(this,R.raw.song_voiceerror);
        }
        player.start();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlayer();

                Log.wtf("hello", String.valueOf(mcount));



                speechRecognizer.startListening(intentRecogniser);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.w("Start", (textView.getText().toString()));
                        if(textView.getText().toString().equals("resume"))
                        { openResume();


                        }
                        if(textView.getText().toString().equals("start"))
                        {
                            Log.w("Kaaj","hocche");
                            //openStartGame();
                            openStartGame();
                        }
                        if(textView.getText().toString().equals("instruction"))
                        {
                            Log.w("Kaaj","hocche");
                            //openStartGame();
                            openInstruction();
                        }
                        if(!(textView.getText().toString().equals("start")||(textView.getText().toString().equals("instruction")||(textView.getText().toString().equals("resume")))))
                        {
                            player1.start();
                        }

                    }
                },4000);

            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  player.pause();

                speechRecognizer.startListening(intentRecogniser);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.wtf("outside the loop val of maxid", String.valueOf(hcnt.getCnt()));
                        /*  */

                        if(textView.getText().toString().equals("resume"))
                        { openResume();


                        }
                        if(textView.getText().toString().equals("start"))
                        {
                            Log.w("Kaaj","hocche");
                            //openStartGame();
                            openStartGame();
                        }
                        if(textView.getText().toString().equals("instruction"))
                        {
                            Log.w("Kaaj","hocche");
                            //openStartGame();
                            openInstruction();
                        }
                        if(!(textView.getText().toString().equals("start")||(textView.getText().toString().equals("instruction")||(textView.getText().toString().equals("resume")))))
                        {
                            player1.start();
                        }


                    }
                },4000);

            }



        });


    }
    private void openResume() {


if(Value.equals("StarGame1"))
{
    Intent intent = new Intent(this, StarGame1.class);
    intent.putExtra("number",number);
    startActivity(intent);
}
        if(Value.equals("Segment2"))
        {
            Intent intent = new Intent(this,Segmen2.class);
            intent.putExtra("number",number);
            startActivity(intent);
        }
        if(Value.equals("Segment3"))
        {
            Intent intent = new Intent(this, Segment3.class);
            intent.putExtra("number",number);
            startActivity(intent);
        }
        if(Value.equals("Segment4"))
        {
            Intent intent = new Intent(this, Segment4.class);
            intent.putExtra("number",number);
            startActivity(intent);
        }
        if(Value.equals("Story2Seg1"))
        {
            Intent intent = new Intent(this, Story2seg1.class);
            intent.putExtra("number",number);
            startActivity(intent);
        }
        if(Value.equals("Story2_out1"))
        {
            Intent intent = new Intent(this, Story2_out1.class);
            intent.putExtra("number",number);
            startActivity(intent);
        }
        if(Value.equals("Story2_out2"))
        {
            Intent intent = new Intent(this, Story2_out2.class);
            intent.putExtra("number",number);
            startActivity(intent);
        }
        if(Value.equals("Story2_seg4"))
        {
            Intent intent = new Intent(this, story2_seg4.class);
            intent.putExtra("number",number);
            startActivity(intent);
        }
        if(Value.equals("Story2_seg5"))
        {
            Intent intent = new Intent(this, story2seg5.class);
            intent.putExtra("number",number);
            startActivity(intent);
        }
        if(Value.equals("Story2_seg6"))
        {
            Intent intent = new Intent(this, Story2seg6.class);
            intent.putExtra("number",number);
            startActivity(intent);
        }
        if(Value.equals("Story2_seg7"))
        {
            Intent intent = new Intent(this, Story2seg7.class);
            intent.putExtra("number",number);
            startActivity(intent);
        }
        if(Value.equals("Story2_seg8"))
        {
            Intent intent = new Intent(this, story2seg8.class);
            intent.putExtra("number",number);
            startActivity(intent);
        }
        if(Value.equals("Story2_seg9"))
        {
            Intent intent = new Intent(this, story2seg9.class);
            intent.putExtra("number",number);
            startActivity(intent);
        }
        if(Value.equals("Story2_seg10"))
        {
            Intent intent = new Intent(this, story2seg10.class);
            intent.putExtra("number",number);
            startActivity(intent);
        }
        if(Value.equals("Story2_seg11"))
        {
            Intent intent = new Intent(this, story2seg11.class);
            intent.putExtra("number",number);
            startActivity(intent);
        }
        if(Value.equals("Story2_seg14"))
        {
            Intent intent = new Intent(this, story2seg14.class);
            intent.putExtra("number",number);
            startActivity(intent);
        }
        if(Value.equals("Story2_seg15"))
        {
            Intent intent = new Intent(this, story2seg15.class);
            intent.putExtra("number",number);
            startActivity(intent);
        }
        if(Value.equals("Story2_seg12"))
        {
            Intent intent = new Intent(this, story2seg12.class);
            intent.putExtra("number",number);
            startActivity(intent);
        }

        if(Value.equals("Story2_seg13"))
        {
            Intent intent = new Intent(this, story2seg13.class);
            intent.putExtra("number",number);
            startActivity(intent);
        }
        if(Value.equals("Story3_seg1"))
        {
            Intent intent = new Intent(this, Story3Seg1.class);
            intent.putExtra("number",number);
            startActivity(intent);
        }
        if(Value.equals("Story3_seg1_out1"))
        {
            Intent intent = new Intent(this, Story3seg1_Out1.class);
            intent.putExtra("number",number);
            startActivity(intent);
        }
        if(Value.equals("Story3_seg1_out2"))
        {
            Intent intent = new Intent(this, Story3seg1_Out2.class);
            intent.putExtra("number",number);
            startActivity(intent);
        }
        if(Value.equals("Story3_seg1_out3"))
        {
            Intent intent = new Intent(this, Story3seg1_Out3.class);
            intent.putExtra("number",number);
            startActivity(intent);
        }
        if(Value.equals("Story3_seg1_out2_next"))
        {
            Intent intent = new Intent(this, Story3seg1out2_next.class);
            intent.putExtra("number",number);
            startActivity(intent);
        }
        if(Value.equals("Story3_seg1o3_op1"))
        {
            Intent intent = new Intent(this, Story3seg1o3_op1.class);
            intent.putExtra("number",number);
            startActivity(intent);
        }
        if(Value.equals("Story3_seg1o3_op2"))
        {
            Intent intent = new Intent(this, Story3seg1o3_op1.class);
            intent.putExtra("number",number);
            startActivity(intent);
        }
        if(Value.equals("Story3l3op1"))
        {
            Intent intent = new Intent(this, Story3l3op1.class);
            intent.putExtra("number",number);
            startActivity(intent);
        }
        if(Value.equals("Story3l3op2"))
        {
            Intent intent = new Intent(this, Story3l3op2.class);
            intent.putExtra("number",number);
            startActivity(intent);
        }
        if(Value.equals("Story3l3op3"))
        {
            Intent intent = new Intent(this, Story3l3op3.class);
            intent.putExtra("number",number);
            startActivity(intent);
        }




    }

    private void openInstruction() {
        Intent intent = new Intent(this, instruction.class);

        startActivity(intent);
    }
    private void openStartGame() {
        Intent intent = new Intent(this, StarGame1.class);
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