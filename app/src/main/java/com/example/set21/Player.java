package com.example.set21;

import java.sql.Time;

public class Player
{
    private long playerId;
    private String name;
    private int sets;
    private String date;

    public Player(long playerId, String name, int sets, String date) {
        super();
        this.playerId = playerId;
        this.name = name;
        this.sets = sets;
        this.date = date;
    }

    public long getPlayerId() {
        return playerId;
    }
    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getSets() {
        return sets;
    }
    public void setSets(Time time) {
        this.sets = sets;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
