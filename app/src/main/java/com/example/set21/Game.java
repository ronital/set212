package com.example.set21;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.media.MediaPlayer;
import android.widget.Button;
import java.util.Locale;

public class Game extends AppCompatActivity {

    private Context context;
    private PlayerOpenHelper poh;
    private long id;

    //clock
    private static final long START_TIME_IN_MILLIS = 6000;
    private TextView mTextViewCountDown;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    //music
    private MediaPlayer mediaPlayer;

    //cards
    private Cardboard c;
    private TextView tvPoints;
    private Button btHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        poh=new PlayerOpenHelper(this);
        context = this;

        //clock
        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        startTimer();
        updateCountDownText();

        //music
        mediaPlayer = MediaPlayer.create(this, R.raw.amsound);
        Switch musicSwitch = (Switch) findViewById(R.id.switch1);
        musicSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            Toast.makeText(Game.this, "The Song is Over", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    mediaPlayer.pause();
                }
            }
        });

        //cards
        TableLayout tableLayout = (TableLayout) findViewById(R.id.table_layout_game);
        c = new Cardboard(tableLayout, this);
        //setContentView(c);
        tvPoints = (TextView)findViewById(R.id.tvPoints);
        btHelp = findViewById(R.id.btHelp);
        btHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (c.availableSet())
                    Toast.makeText(Game.this, "There is a set", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(Game.this, "No set", Toast.LENGTH_LONG).show();
            }
        });
    }

    //music
    private void releaseMediaPlayer() {
        try {
            if (mediaPlayer != null) {
                if (mediaPlayer.isPlaying())
                    mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //clock
    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();

                tvPoints.setText("sets: " + String.valueOf(c.getPoints()));
            }
            @Override
            public void onFinish() {
                mTimerRunning = false;

                AlertDialog alertDialog = new AlertDialog.Builder(context)
                        //set icon
                        //.setIcon(android.R.drawable.ic_dialog_alert)
                        //set title
                        .setTitle("end of the game")
                        //set message
                        .setMessage("save results?")
                        //set positive button
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Log.d("Game", "login selected");
                                Intent intent=new Intent(context, Login.class);
                                intent.putExtra("points",c.getPoints());
                                startActivity(intent);
                            }
                        })
                        //set negative button
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Log.d("Game", "home selected");
                                Intent intent=new Intent(context, MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .show();

                releaseMediaPlayer();
            }
        }.start();
        mTimerRunning = true;
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
            releaseMediaPlayer();
            Log.d("MainActivity", "home selected");
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        if (id == R.id.action_game) {
            releaseMediaPlayer();
            Log.d("MainActivity", "game selected");
            Intent intent=new Intent(this,Game.class);
            startActivity(intent);
        }
        else if (id == R.id.action_scoreboard) {
            releaseMediaPlayer();
            Log.d("MainActivity", "scoreboard selected");
            Intent intent=new Intent(this,Scoreboard.class);
            startActivity(intent);
        }
        return true;
    }
}
