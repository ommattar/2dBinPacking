import java.util.ArrayList;
import java.util.List;

public class FirstFit {
    private List<Bin> bins;
    private Reader reader;

    public FirstFit() {
        this.bins = new ArrayList<>();
        this.reader= Reader.getInstance();
    }

    public void pack() {
        for (Item item : reader.getItems()) {
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
                Bin newBin = new Bin(reader.getBinSize(), reader.getBinSize());  // Assuming the bin width and height are 250
                newBin.addItem(item);
                bins.add(newBin);
            }
        }
    }

    public List<Bin> getBins() {
        pack();
        return bins;
    }
}