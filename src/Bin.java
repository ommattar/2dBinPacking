import java.util.ArrayList;
import java.util.Comparator;
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

    public Bin(double width, double length, List<Item> items) {
        this.width = width;
        this.length = length;
        this.items = new ArrayList<>();
    }

    // Check if an item can fit in the bin
    public boolean canFit(Item item) {
        double maxX = 0;
        double maxY = 0;

        for (Item existingItem : items) {
            maxX = Math.max(maxX, existingItem.getX() + existingItem.getWidth());
            maxY = Math.max(maxY, existingItem.getY() + existingItem.getLength());
        }

        // Check if the item can fit to the right of the bounding box or below the bounding box
        return maxX + item.getWidth() <= this.width || maxY + item.getLength() <= this.length;
    }

    // Add an item to the bin
    public boolean addItem(Item item) {
        if (canFit(item)) {
            // Set the item's position
            // For simplicity, we'll place the item at the top left corner if it fits there
            // Otherwise, we'll place it below the existing items
            double maxX = 0;
            double maxY = 0;

            for (Item existingItem : items) {
                maxX = Math.max(maxX, existingItem.getX() + existingItem.getWidth());
                maxY = Math.max(maxY, existingItem.getY() + existingItem.getLength());
            }

            if (maxX + item.getWidth() <= this.width) {
                item.setPosition(maxX, 0);
            } else {
                item.setPosition(0, maxY);
            }

            // Add the item to the bin
            this.items.add(item);

            return true;
        } else {
            return false;
        }
    }

    public boolean removeItem(Item item) {
        boolean isRemoved = this.items.remove(item);
        if (isRemoved) {
            reorganizeItems();
        }
        return isRemoved;
    }

    private void reorganizeItems() {
        List<Item> sortedItems = new ArrayList<>(this.items);
        this.items.clear();

        // Sort the items by their size
        sortedItems.sort(Comparator.comparingDouble(Item::getWidth).reversed());

        // Re-add the items to the bin
        for (Item item : sortedItems) {
            this.addItem(item);
        }
    }

    public void addClonedItem(Item item) {
        this.items.add(item);
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