import java.util.ArrayList;
import java.util.List;

public class Bin {
    private double width;
    private double length;
    private List<Item> items;

    public Bin(double width, double length) {
        this.width = width;
        this.length = length;
        this.items = new ArrayList<>();
    }

    public boolean addItem(Item item) {
        if (canFitItem(item)) {
            items.add(item);
            return true;
        }
        items.add(item);
        return false;
    }

    private boolean canFitItem(Item item) {
        double remainingArea = width * length;
        double remainingLength = length;
        double remainingWidth = width;

        for (Item i : items) {
            remainingArea -= i.getLength() * i.getWidth();
            remainingLength -= i.getLength();
            remainingWidth -= i.getWidth();
        }

        return item.getLength() <= remainingLength &&
                item.getWidth() <= remainingWidth &&
                item.getLength() * item.getWidth() <= remainingArea;
    }

    // Getters
    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }

    public List<Item> getItems() {
        return items;
    }
}