package ColorAddict.Enums;

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

    public String toString() {
        switch (this) {
            case RED:
                return "RED";
            case BLUE:
                return "BLUE";
            case GREEN:
                return "GREEN";
            case YELLOW:
                return "YELLOW";
            case BLACK:
                return "BLACK";
            case ORANGE:
                return "ORANGE";
            case PINK:
                return "PINK";
            default:
                return "WHITE";
        }
    }
}
