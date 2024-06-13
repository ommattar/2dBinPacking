import java.util.ArrayList;
import java.util.List;

public class Bin {
    private double width;
    private double length;
    private List<Item> items;
    private List<SubBin> subBins;

    public Bin(double width, double length) {
        this.width = width;
        this.length = length;
        this.items = new ArrayList<>();
        this.subBins = new ArrayList<>();
        subBins.add(new SubBin(0, 0, width, length));
    }

    public boolean addItem(Item item) {
        for (SubBin subBin : subBins) {
            if (subBin.canFitItem(item)) {
                item.setPosition(subBin.getX(), subBin.getY());
                items.add(item);
                subBins.remove(subBin);
                splitSubBins(subBin, item);
                return true;
            }
        }
        return false;
    }

    private void splitSubBins(SubBin subBin, Item item) {
        double itemRight = subBin.getX() + item.getWidth();
        double itemTop = subBin.getY() + item.getLength();
        double subBinRight = subBin.getX() + subBin.getWidth();
        double subBinTop = subBin.getY() + subBin.getLength();

        // Create new sub-bins for the remaining spaces
        if (itemRight < subBinRight) {
            subBins.add(new SubBin(itemRight, subBin.getY(), subBinRight - itemRight, subBin.getLength()));
        }
        if (itemTop < subBinTop) {
            subBins.add(new SubBin(subBin.getX(), itemTop, subBin.getWidth(), subBinTop - itemTop));
        }
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

    private class SubBin {
        private double x;
        private double y;
        private double width;
        private double length;

        public SubBin(double x, double y, double width, double length) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.length = length;
        }

        public boolean canFitItem(Item item) {
            return item.getWidth() <= width && item.getLength() <= length;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public double getWidth() {
            return width;
        }

        public double getLength() {
            return length;
        }
    }
}
