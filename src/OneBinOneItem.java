import java.util.ArrayList;
import java.util.List;

public class OneBinOneItem {
    private List<Bin> bins;
    private Reader reader;

    public OneBinOneItem() {
        this.bins = new ArrayList<>();
        this.reader= Reader.getInstance();
    }
    public void pack() {
        this.bins = new ArrayList<>();
        for (Item item : reader.getItems()) {


                Bin bin = new Bin(reader.getBinSize(), reader.getBinSize());
                bin.addItem(item);
                bins.add(bin);

        }
    }

    public List<Bin> getBins() {
        pack();
        return bins;
    }
}

