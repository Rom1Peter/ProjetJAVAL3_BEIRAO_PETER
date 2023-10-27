package com.example.projet;

import javafx.scene.paint.Paint;

public enum CardColor {
    RED,
    BLUE,
    GREEN,
    YELLOW,
    BLACK,
    ORANGE,
    PINK;

    public Paint getColor() {
        switch (this) {
            case RED:
                return Paint.valueOf("red");
            case BLUE:
                return Paint.valueOf("blue");
            case GREEN:
                return Paint.valueOf("green");
            case YELLOW:
                return Paint.valueOf("yellow");
            case BLACK:
                return Paint.valueOf("black");
            case ORANGE:
                return Paint.valueOf("orange");
            case PINK:
                return Paint.valueOf("pink");
            default:
                return Paint.valueOf("white");
        }
    }
}
