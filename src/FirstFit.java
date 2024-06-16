import java.util.ArrayList;
import java.util.List;

public class FirstFit {
    private List<Bin> bins;

    public FirstFit() {
        this.bins = new ArrayList<>();
    }

    public void pack(List<Item> items, int binSize) {
        for (Item item : items) {
            boolean packed = false;

            // Try to pack the item in the first bin where it fits
            for (Bin bin : bins) {
                if (bin.addItem(item)) {
                    packed = true;
                    break;
                }
            }

            // If the item doesn't fit in any of the existing bins, create a new bin and pack the item there
            if (!packed) {
                Bin newBin = new Bin(binSize, binSize);  // Assuming the bin width and height are 250
                newBin.addItem(item);
                bins.add(newBin);
            }
        }
    }

    public List<Bin> getBins(List<Item> items,int binSize) {
        pack(items, binSize);
        return bins;
    }
}