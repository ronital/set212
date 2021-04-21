package com.example.set21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.os.SystemClock;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.media.MediaPlayer;
import android.widget.Button;
import java.util.Locale;

public class Game extends AppCompatActivity {

    //clock
    private static final long START_TIME_IN_MILLIS = 60000;
    private TextView mTextViewCountDown;
    private Button mButtonReset;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    //music
    private MediaPlayer mediaPlayer;

    //cards
    Cardboard c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //clock
        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        mButtonReset = findViewById(R.id.button_reset);
        startTimer();
        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });
        updateCountDownText();

        //music
        mediaPlayer = MediaPlayer.create(this, R.raw.sound);
        Button playButton = (Button) findViewById(R.id.start);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        Toast.makeText(Game.this, "The Song is Over", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        Button pauseButton = (Button) findViewById(R.id.stop);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
            }
        });

        //cards
        TableLayout tableLayout = (TableLayout) findViewById(R.id.table_layout_game);
        c = new Cardboard(tableLayout, this);
        //setContentView(c);
    }


    //clock
    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }
            @Override
            public void onFinish() {
                mTimerRunning = false;
            }
        }.start();
        mTimerRunning = true;
    }

    private void resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        mCountDownTimer.start();
    }
    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        mTextViewCountDown.setText(timeLeftFormatted);
    }


    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_login) {
            Log.d("MainActivity", "login selected");
            return true;
        }
        if (id == R.id.action_home) {
            Log.d("MainActivity", "home selected");
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        if (id == R.id.action_game) {
            Log.d("MainActivity", "game selected");
            Intent intent=new Intent(this,Game.class);
            startActivity(intent);
        }
        else if (id == R.id.action_scoreboard) {
            Log.d("MainActivity", "scoreboard selected");
            Intent intent=new Intent(this,Scoreboard.class);
            startActivity(intent);
        }
        return true;
    }
}
