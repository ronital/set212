package com.example.set21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnYoutube;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnYoutube=(Button)findViewById(R.id.btnYoutube);
        btnYoutube.setOnClickListener(this);
    }

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
            Intent intent=new Intent(this,Login.class);
            startActivity(intent);
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

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=lqtcKFyg0Bc"));
        startActivity(intent);
    }
}
