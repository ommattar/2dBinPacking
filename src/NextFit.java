import java.util.ArrayList;
import java.util.List;

public class NextFit {
    private List<Bin> bins;
    private Reader reader;
    private int lastBinIndex;

    public NextFit() {
        this.bins = new ArrayList<>();
        this.lastBinIndex = 0;
        this.reader= Reader.getInstance();
    }

    public void pack() {
        for (Item item : reader.getItems()) {
            boolean packed = false;

            // Try to pack the item in the next bin where it fits
            for (int i = lastBinIndex; i < bins.size(); i++) {
                Bin bin = bins.get(i);
                if (bin.addItem(item)) {
                    packed = true;
                    lastBinIndex = i;  // Update the index of the last bin where an item was packed
                    break;
                }
            }

            // If the item doesn't fit in any of the existing bins, create a new bin and pack the item there
            if (!packed) {
                Bin newBin = new Bin(reader.getBinSize(), reader.getBinSize());  // Assuming the bin width and height are 250
                newBin.addItem(item);
                bins.add(newBin);
                lastBinIndex = bins.size() - 1;  // Update the index of the last bin where an item was packed
            }
        }
    }

    public List<Bin> getBins() {
        pack();
        return bins;
    }
}