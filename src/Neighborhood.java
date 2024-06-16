import java.util.ArrayList;
import java.util.List;

public class Neighborhood {
    public List<List<Bin>> getNeighborhood(List<Bin> solution) {
        List<List<Bin>> neighborhood = new ArrayList<>();

        for (int i = 0; i < solution.size(); i++) {
            for (int j = 0; j < solution.get(i).getItems().size(); j++) {
                for (int k = 0; k < solution.size(); k++) {
                    if (i != k) {
                        // Create a new solution by moving item j from bin i to bin k
                        List<Bin> newSolution = new ArrayList<>();
                        for (Bin bin : solution) {
                            newSolution.add(new Bin(bin.getWidth(), bin.getLength(), new ArrayList<>(bin.getItems())));
                        }
                        Item item = newSolution.get(i).getItems().remove(j);
                        if (newSolution.get(k).canFit(item)) {
                            newSolution.get(k).addItem(item);
                            neighborhood.add(newSolution);
                        }
                    }
                }
            }
        }

        return neighborhood;
    }
}