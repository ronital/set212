package com.example.set21;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnYoutube;
    TextView tv;
    BroadCastBattery broadCastBattery;
    Context context;

    Dialog d;
    Button btnEasy;
    Button btnMedium;
    Button btnHard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        btnYoutube=(Button)findViewById(R.id.btnYoutube);
        btnYoutube.setOnClickListener(this);

        tv=(TextView)findViewById(R.id.tv);
        broadCastBattery=new BroadCastBattery();
    }

    private class BroadCastBattery extends BroadcastReceiver

    {
        @Override
        public void onReceive(Context context, Intent intent) {
            int battery = intent.getIntExtra("level",0);
            tv.setText("broadcast battery " + String.valueOf(battery) + "%");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadCastBattery,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadCastBattery);
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
        /*if (id == R.id.action_login) {
            Log.d("MainActivity", "login selected");
            Intent intent=new Intent(this,Login.class);
            startActivity(intent);
        }*/
        if (id == R.id.action_home) {
            Log.d("MainActivity", "home selected");
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        if (id == R.id.action_game) {
            Log.d("MainActivity", "game selected");
            createGameDialog();
        }
        else if (id == R.id.action_scoreboard) {
            Log.d("MainActivity", "scoreboard selected");
            Intent intent=new Intent(this,Scoreboard.class);
            startActivity(intent);
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=lqtcKFyg0Bc"));
        startActivity(intent);
    }

    public void createGameDialog()
    {
        d= new Dialog(this);
        d.setContentView(R.layout.dialog_layout);
        d.setTitle("start game");
        d.setCancelable(false);
        btnEasy = (Button)d.findViewById(R.id.easy);
        btnMedium = (Button)d.findViewById(R.id.medium);
        btnHard = (Button)d.findViewById(R.id.hard);
        btnEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,Game.class);
                intent.putExtra("level","easy");
                startActivity(intent);
            }
        });
        btnMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,Game.class);
                intent.putExtra("level","medium");
                startActivity(intent);
            }
        });
        btnHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,Game.class);
                intent.putExtra("level","hard");
                startActivity(intent);
            }
        });
        d.show();
    }
}
