package com.example.set21;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class PlayerOpenHelper extends SQLiteOpenHelper {


    public static final String DATABASENAME = "player.db";
    public static final String TABLE_PLAYER = "tblplayer";
    public static final int DATABASEVERSION = 1;

    public static final String COLUMN_ID = "playerId";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SETS = "sets";

    private static final String CREATE_TABLE_CUSTOMER = "CREATE TABLE IF NOT EXISTS " +
            TABLE_PLAYER + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME + " VARCHAR,"
            + COLUMN_SETS + " INTEGER" + ");";


    String[] allColumns = {PlayerOpenHelper.COLUMN_ID, PlayerOpenHelper.COLUMN_NAME,
            PlayerOpenHelper.COLUMN_SETS};

    SQLiteDatabase database;

    public PlayerOpenHelper(Context context) {
        super(context, DATABASENAME, null, DATABASEVERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CUSTOMER);
        Log.i("data", "Table customer created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYER);
        onCreate(db);
    }

    public void open()
    {
        database=this.getWritableDatabase();
        Log.i("data", "Database connection open");
    }


    //queries
    public Player createPlayer(Player p)
    {
        ContentValues values = new ContentValues();
        values.put(PlayerOpenHelper.COLUMN_NAME, p.getName());
        values.put(PlayerOpenHelper.COLUMN_SETS, p.getSets());

        long insertId=database.insert(PlayerOpenHelper.TABLE_PLAYER, null, values);
        Log.i("data", "Player " + insertId + " insert to database");
        p.setPlayerId(insertId);
        return p;
    }

    public ArrayList<Player> getAllPlayers() {

        ArrayList<Player> l = new ArrayList<Player>();
        Cursor cursor=database.query(PlayerOpenHelper.TABLE_PLAYER, allColumns, null, null, null, null, null);

        if(cursor.getCount()>0)
        {
            while(cursor.moveToNext())
            {
                long id = cursor.getLong(cursor.getColumnIndex(PlayerOpenHelper.COLUMN_ID));
                String name =cursor.getString(cursor.getColumnIndex(PlayerOpenHelper.COLUMN_NAME));
                int sets = cursor.getInt(cursor.getColumnIndex(PlayerOpenHelper.COLUMN_SETS));
                Player p = new Player(id, name, sets);
                l.add(p);
            }
        }
        return l;
    }

    public ArrayList<Player>getAllCustomersByFIlter(String selection,String OrderBy)
    {
        Cursor cursor=database.query(PlayerOpenHelper.TABLE_PLAYER, allColumns, selection, null, null, null, OrderBy);
        ArrayList<Player>l=convertCursorToList(cursor);
        return  l;
    }


    private ArrayList<Player> convertCursorToList(Cursor cursor) {
        ArrayList<Player>l=new ArrayList<Player>();

        if(cursor.getCount()>0)
        {
            while(cursor.moveToNext())
            {
                long id = cursor.getLong(cursor.getColumnIndex(PlayerOpenHelper.COLUMN_ID));
                String name =cursor.getString(cursor.getColumnIndex(PlayerOpenHelper.COLUMN_NAME));
                int sets = cursor.getInt(cursor.getColumnIndex(PlayerOpenHelper.COLUMN_SETS));
                Player p =new Player(id,name,sets);
                l.add(p);

            }
        }
        return l;
    }

    public long deleteAll()
    {
        Log.i("data", "Database deleted");
        return database.delete(PlayerOpenHelper.TABLE_PLAYER, null, null);
    }
    public long deleteCustomerByRow(long rowId)
    {
        return database.delete(PlayerOpenHelper.TABLE_PLAYER, PlayerOpenHelper.COLUMN_ID + "=" + rowId, null);
    }

    public long updateByRow(Player c) {

        ContentValues values = new ContentValues();
        values.put(PlayerOpenHelper.COLUMN_ID, c.getPlayerId());
        values.put(PlayerOpenHelper.COLUMN_NAME, c.getName());
        values.put(PlayerOpenHelper.COLUMN_SETS, c.getSets());

        return database.update(PlayerOpenHelper.TABLE_PLAYER, values, PlayerOpenHelper.COLUMN_ID + "=" + c.getPlayerId(), null);
    }

}