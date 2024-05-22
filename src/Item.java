import java.util.ArrayList;
import java.util.List;



public class Item {
    private int id;
    private double width;
    private double length;

    public Item(int id, double width, double length) {
        this.id = id;
        this.width = width;
        this.length = length;
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
}