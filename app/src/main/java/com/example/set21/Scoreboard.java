package com.example.set21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class Scoreboard extends AppCompatActivity {

    PlayerOpenHelper poh;
    ArrayList<Player> listOfPlayer;
    ListView lv;
    PlayerAdapter PlayerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        lv=(ListView)findViewById(R.id.lv);

        poh = new PlayerOpenHelper(this);
        listOfPlayer = new ArrayList<Player>();
        poh.open();
        listOfPlayer = poh.getAllPlayers();
        poh.close();

        Log.i("data", "list size is " + listOfPlayer.size());

        if(listOfPlayer.size()==0)
        {
            createPlayers();
        }

        PlayerAdapter = new PlayerAdapter(this,0,listOfPlayer);
        lv.setAdapter(PlayerAdapter);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }
    public void createPlayers()
    {
        poh.open();
        Player p1 = new Player(0,"oren",0);
        p1 = poh.createPlayer(p1);
        listOfPlayer.add(p1);

        Player p2 = new Player(0,"dan",0);
        p2 = poh.createPlayer(p2);
        listOfPlayer.add(p2);
        poh.close();
    }

    //Menu
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
