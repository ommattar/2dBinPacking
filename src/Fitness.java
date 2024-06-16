import java.util.List;

public class Fitness {
    public double calculateFitness(List<Bin> solution) {
        double totalUnusedSpace = 0;

        for (Bin bin : solution) {
            double usedSpace = 0;
            for (Item item : bin.getItems()) {
                usedSpace += item.getWidth() * item.getLength();
            }
            double binArea = bin.getWidth() * bin.getLength();
            totalUnusedSpace += binArea - usedSpace;
        }

        return totalUnusedSpace;
    }
}