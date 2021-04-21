package com.example.set21;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.R.*;
import android.widget.TableRow;

public class Cardboard implements View.OnClickListener {


    public Cardboard(TableLayout table, Context context) {
        //LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //this.setOrientation(VERTICAL);
        loadCardboard1(table, context);



    }

    public void loadCardboard()
    {
        /*LinearLayout l = new LinearLayout(context);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 100, 0, 0);
        l.setLayoutParams(layoutParams);

        for (int j=0; j<3; j++)
        {
            for(int i=0;i<2;i++)
            {

                ImageView iv = new ImageView(context);
                LayoutParams ivParam = new TableLayout.LayoutParams(120,120);
                iv.setLayoutParams(ivParam);
                int imageKey = getResources().getIdentifier("setpicture", "drawable", context.getPackageName());
                iv.setImageResource(imageKey);
                iv.setOnClickListener(this);
            }
            this.addView(l);
        }*/




    }

    @Override
    public void onClick(View view) {

    }

    private void loadCardboard1(TableLayout table, Context context)
    {
        //Card[] cards = new Card[82];
        //Card cgf1 = new Card("cgf1");
        //Card cgf2 = new Card("cgf2");


        for (int i = 0; i <4; i++) {

            TableRow row = new TableRow(context);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
            lp.gravity = Gravity.CENTER;
            lp.rightMargin = 10;
            row.setLayoutParams(lp);
            row.setWeightSum(1.0f);
            ImageButton btn1 = new ImageButton(context);
            ImageButton btn2 = new ImageButton(context);
            ImageButton btn3 = new ImageButton(context);

            //TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            //lp.gravity = Gravity.CENTER;
            //addBtn.setLayoutParams(layoutParams);

            //TableLayout.LayoutParams ibParam = (TableLayout.LayoutParams) addBtn.getLayoutParams();
            //ibParam.height = 200;
            //ibParam.width = 200;
            //addBtn.setLayoutParams(ibParam);
            //addBtn.setScaleType(ImageButton.ScaleType.FIT_XY);

            btn1.setImageResource(R.drawable.cgf1copy);
            btn2.setImageResource(R.drawable.cgf1copy);
            btn3.setImageResource(R.drawable.cgf1copy);


            row.addView(btn1);
            row.addView(btn2);
            row.addView(btn3);
            table.addView(row);
        }
    }
}
