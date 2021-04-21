package com.example.set21;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cds=new PlayerOpenHelper(this);
        init();
    }

    public void init()
    {
        etname =(EditText) findViewById(R.id.insertName);
        btnsave=(Button) findViewById(R.id.insertBtnSave);
        tvId=(TextView) findViewById(R.id.inserttvId);
        btnsave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String name = etname.getText().toString();
                Player p = new Player(0, name, 0, -1);
                cds.open();
                p = cds.createPlayer(p);
                cds.close();
                Intent i = new Intent();
                setResult(RESULT_OK, i);
                finish();
            }
        });

    }

}
