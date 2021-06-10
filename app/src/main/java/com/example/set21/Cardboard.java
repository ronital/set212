package com.example.set21;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.R.*;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import static android.view.View.generateViewId;

public class Cardboard implements View.OnClickListener {

    private ArrayList<Integer> clickedIds;
    private ArrayList<Card> cards;
    private ArrayList<Card> clickedCards;
    private HashMap<Integer, String> idCards;
    private ArrayList<ImageButton> clickedButtons;
    private int points;
    private Context context;

    public Cardboard(TableLayout table, Context context) {
        //LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //this.setOrientation(VERTICAL);
        this.context = context;
        loadCardboard(table, context);


    }

    @Override
    public void onClick(View view) {
        //Toast.makeText(context, "card clicked", Toast.LENGTH_LONG).show();

        view.setEnabled(false);
        Integer clickedId = view.getId();
        clickedIds.add(clickedId);
        String clickedName = idCards.get(clickedId);
        clickedCards.add(new Card(clickedName));
        clickedButtons.add((ImageButton) view);

        if (clickedCards.size() == 3) {
            if (clickedCards.get(0).isSet(clickedCards.get(1), clickedCards.get(2))) {
                Toast.makeText(context, "well done", Toast.LENGTH_SHORT).show();
                points++;

                for (int i = 0; i < 3; i++) {
                    int imageKey = context.getResources().getIdentifier(cards.get(0).getName(), "drawable", context.getPackageName());
                    clickedButtons.get(i).setImageResource(imageKey);
                    idCards.put(clickedIds.get(i), cards.get(0).getName());
                    cards.remove(0);
                }

                /*int imageKey1 = context.getResources().getIdentifier(cards.get(0).getName(), "drawable", context.getPackageName());
                clickedButtons.get(0).setImageResource(imageKey1);
                idCards.put(clickedIds.get(0), cards.get(0).getName());

                int imageKey2 = context.getResources().getIdentifier(cards.get(1).getName(), "drawable", context.getPackageName());
                clickedButtons.get(1).setImageResource(imageKey2);
                idCards.put(clickedIds.get(1), cards.get(1).getName());

                int imageKey3 = context.getResources().getIdentifier(cards.get(2).getName(), "drawable", context.getPackageName());
                clickedButtons.get(2).setImageResource(imageKey3);
                idCards.put(clickedIds.get(2), cards.get(2).getName());

                cards.remove(0);
                cards.remove(0);
                cards.remove(0);*/
            }
            else
                Toast.makeText(context, "try again", Toast.LENGTH_SHORT).show();

            for(View bt : clickedButtons)
                bt.setEnabled(true);


            clickedCards.clear();
            clickedButtons.clear();
            clickedIds.clear();
        }
    }

    private void loadCardboard(TableLayout table, Context context) {
        cards = new ArrayList<>();
        initCards(cards);
        Collections.shuffle(cards);

        idCards = new HashMap<Integer, String>();

        clickedCards = new ArrayList<>();
        clickedButtons = new ArrayList<>();
        clickedIds = new ArrayList<>();

        for (int i = 0; i < 4; i++) {

            TableRow row = new TableRow(context);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
            lp.gravity = Gravity.CENTER;
            lp.rightMargin = 5;
            lp.leftMargin = 5;
            row.setLayoutParams(lp);
            row.setWeightSum(1.0f);

            for (int j = 0; j < 3; j++) {
                ImageButton btn = new ImageButton(context);
                int id = generateViewId();
                btn.setId(id);
                idCards.put(id, cards.get(0).getName());

                int imageKey = context.getResources().getIdentifier(cards.get(0).getName(), "drawable", context.getPackageName());
                btn.setImageResource(imageKey);
                cards.remove(0);
                row.addView(btn);
                btn.setOnClickListener(this);
            }

            table.addView(row);
        }
    }

    public int getPoints()
    {
        return points;
    }

    private void initCards(ArrayList<Card> cards) {
        cards.add(new Card("cgf1"));
        cards.add(new Card("cgf2"));
        cards.add(new Card("cgf3"));
        cards.add(new Card("cgs1"));
        cards.add(new Card("cgs2"));
        cards.add(new Card("cgs3"));
        cards.add(new Card("cgu1"));
        cards.add(new Card("cgu2"));
        cards.add(new Card("cgu3"));
        cards.add(new Card("cpf1"));
        cards.add(new Card("cpf2"));
        cards.add(new Card("cpf3"));
        cards.add(new Card("cps1"));
        cards.add(new Card("cps2"));
        cards.add(new Card("cps3"));
        cards.add(new Card("cpu1"));
        cards.add(new Card("cpu2"));
        cards.add(new Card("cpu3"));
        cards.add(new Card("crf1"));
        cards.add(new Card("crf2"));
        cards.add(new Card("crf3"));
        cards.add(new Card("crs1"));
        cards.add(new Card("crs2"));
        cards.add(new Card("crs3"));
        cards.add(new Card("cru1"));
        cards.add(new Card("cru2"));
        cards.add(new Card("cru3"));

        cards.add(new Card("sgf1"));
        cards.add(new Card("sgf2"));
        cards.add(new Card("sgf3"));
        cards.add(new Card("sgs1"));
        cards.add(new Card("sgs2"));
        cards.add(new Card("sgs3"));
        cards.add(new Card("sgu1"));
        cards.add(new Card("sgu2"));
        cards.add(new Card("sgu3"));
        cards.add(new Card("spf1"));
        cards.add(new Card("spf2"));
        cards.add(new Card("spf3"));
        cards.add(new Card("sps1"));
        cards.add(new Card("sps2"));
        cards.add(new Card("sps3"));
        cards.add(new Card("spu1"));
        cards.add(new Card("spu2"));
        cards.add(new Card("spu3"));
        cards.add(new Card("srf1"));
        cards.add(new Card("srf2"));
        cards.add(new Card("srf3"));
        cards.add(new Card("srs1"));
        cards.add(new Card("srs2"));
        cards.add(new Card("srs3"));
        cards.add(new Card("sru1"));
        cards.add(new Card("sru2"));
        cards.add(new Card("sru3"));

        cards.add(new Card("tgf1"));
        cards.add(new Card("tgf2"));
        cards.add(new Card("tgf3"));
        cards.add(new Card("tgs1"));
        cards.add(new Card("tgs2"));
        cards.add(new Card("tgs3"));
        cards.add(new Card("tgu1"));
        cards.add(new Card("tgu2"));
        cards.add(new Card("tgu3"));
        cards.add(new Card("tpf1"));
        cards.add(new Card("tpf2"));
        cards.add(new Card("tpf3"));
        cards.add(new Card("tps1"));
        cards.add(new Card("tps2"));
        cards.add(new Card("tps3"));
        cards.add(new Card("tpu1"));
        cards.add(new Card("tpu2"));
        cards.add(new Card("tpu3"));
        cards.add(new Card("trf1"));
        cards.add(new Card("trf2"));
        cards.add(new Card("trf3"));
        cards.add(new Card("trs1"));
        cards.add(new Card("trs2"));
        cards.add(new Card("trs3"));
        cards.add(new Card("tru1"));
        cards.add(new Card("tru2"));
        cards.add(new Card("tru3"));
    }
}
