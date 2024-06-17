import java.util.ArrayList;
import java.util.List;

public class Neighborhood {

    public List<List<Bin>> getNeighborhood(List<Bin> bins) {
        List<List<Bin>> neighborhood = new ArrayList<>();

        for (int i = 0; i < bins.size(); i++) {
            Bin sourceBin = bins.get(i);
            for (int j = 0; j < bins.size(); j++) {
                if (i != j) {
                    Bin targetBin = bins.get(j);
                    for (int k = 0; k < sourceBin.getItems().size(); k++) {
                        List<Bin> neighbor = cloneBins(bins);
                        Bin clonedSourceBin = neighbor.get(i);
                        Bin clonedTargetBin = neighbor.get(j);

                        // Ensure cloned items have unique identities
                        Item clonedItem = clonedSourceBin.getItems().get(k);

                        // Attempt to add the item to the target bin
                        if (clonedTargetBin.addItem(clonedItem)) {
                            clonedSourceBin.removeItem(clonedItem);
                            if (clonedSourceBin.getItems().isEmpty()) {
                                neighbor.remove(clonedSourceBin);
                            }
                            neighborhood.add(neighbor);
                        }
                    }
                }
            }
        }

        return neighborhood;
    }

    private List<Bin> cloneBins(List<Bin> originalBins) {
        List<Bin> clonedBins = new ArrayList<>();
        for (Bin bin : originalBins) {
            Bin clonedBin = new Bin(bin.getWidth(), bin.getLength());
            for (Item item : bin.getItems()) {
                Item clonedItem = cloneItem(item);
                clonedBin.addClonedItem(clonedItem);
            }
            clonedBins.add(clonedBin);
        }
        return clonedBins;
    }

    private Item cloneItem(Item item) {
        Item clonedItem = new Item(item.getId(), item.getWidth(), item.getLength());
        clonedItem.setPosition(item.getX(), item.getY());
        return clonedItem;
    }
}
