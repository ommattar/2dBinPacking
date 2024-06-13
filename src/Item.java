import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Item {
    private int id;
    private double width;
    private double length;
    private double x;
    private double y;
    private Color binColor;

    public Item(int id, double width, double length) {
        this.id = id;
        this.width = width;
        this.length = length;
        this.binColor = generateRandomColor();
    }

    // Getters
    public int getId() {
        return id;
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Color getBinColor() {
        return  binColor;
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    private Color generateRandomColor() {
        Random random = new Random();
        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }
}