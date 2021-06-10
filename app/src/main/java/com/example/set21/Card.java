package com.example.set21;

import android.content.Context;

public class Card {

    private String shape; //c-circle, s-square, t-triangle
    private String color; //r-red, g-green, p-purple
    private String filling; //f-filled, s-striped, u-unfilled
    private int number; //1, 2, 3

    public Card(String color, String shape, String filling, int number) {
        this.color = color;
        this.shape = shape;
        this.filling = filling;
        this.number = number;
    }

    public Card(String name) {
        switch (name.charAt(0)) {
            case 'c':
                shape = "circle";
                break;
            case 's':
                shape = "square";
                break;
            case 't':
                shape = "triangle";
                break;
            default:
                throw new IllegalArgumentException("Invalid shape char: " + name.charAt(0));
        }
        switch (name.charAt(1)) {
            case 'r':
                color = "red";
                break;
            case 'g':
                color = "green";
                break;
            case 'p':
                color = "purple";
                break;
            default:
                throw new IllegalArgumentException("Invalid color char: " + name.charAt(1));
        }
        switch (name.charAt(2)) {
            case 'f':
                filling = "filled";
                break;
            case 's':
                filling = "striped";
                break;
            case 'u':
                filling = "unfilled";
                break;
            default:
                throw new IllegalArgumentException("Invalid filling char: " + name.charAt(2));
        }
        switch (name.charAt(3)) {
            case '1':
                number = 1;
                break;
            case '2':
                number = 2;
                break;
            case '3':
                number = 3;
                break;
            default:
                throw new IllegalArgumentException("Invalid number char: " + name.charAt(3));
        }
    }

    public String getColor() {
        return color;
    }

    public String getShape() {
        return shape;
    }

    public String getFilling() {
        return filling;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        String name = shape.substring(0, 1) + color.substring(0, 1) + filling.substring(0, 1) + number;
        return name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public void setFilling(String filling) {
        this.filling = filling;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isSet(Card c2, Card c3) {
        if (setByParameter(this.color, c2.color, c3.color) && setByParameter(this.filling, c2.filling, c3.filling)
                && setByParameter(this.shape, c2.shape, c3.shape) && setByNumber(this.number, c2.number, c3.number))
            return true;
        else
            return false;
    }

    public boolean setByParameter(String s1, String s2, String s3) {
        if (s1.equals(s2) && s2.equals(s3))
            return true;
        else if (!s1.equals(s2) && !s3.equals(s2) && !s1.equals(s3))
            return true;
        else
            return false;
    }

    public boolean setByNumber(int n1, int n2, int n3) {
        if (n1 == n2 && n2 == n3)
            return true;
        else if (n1 != n2 && n2 != n3 && n1 != n3)
            return true;
        else
            return false;
    }

    public boolean differentCards(Card c1, Card c2) {
        if ((this.shape == c1.shape && this.color == c1.color && this.filling == c1.filling && this.number == c1.number) ||
                (this.shape == c2.shape && this.color == c2.color && this.filling == c2.filling && this.number == c2.number))
            return false;
        return true;
    }

}
