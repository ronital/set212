package com.example.set21;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    EditText etname;
    TextView tvId;
    Button btnsave;
    PlayerOpenHelper cds;
    long id;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;

        cds=new PlayerOpenHelper(this);
        init();
    }

    public void init()
    {
        Intent recIntent = getIntent();
        final int points = recIntent.getExtras().getInt("points");

        etname =(EditText) findViewById(R.id.insertName);
        btnsave=(Button) findViewById(R.id.insertBtnSave);
        tvId=(TextView) findViewById(R.id.inserttvId);
        btnsave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String name = etname.getText().toString();
                Player p = new Player(0, name, points);
                cds.open();
                p = cds.createPlayer(p);
                cds.close();
                Intent i = new Intent();
                setResult(RESULT_OK, i);
                finish();

                Intent sendIntent=new Intent(context, Scoreboard.class);
                startActivity(sendIntent);
            }
        });

    }

}
