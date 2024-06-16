import java.util.*;

public class TabuSearch {
    private List<Bin> bins;
    private Queue<List<Bin>> tabuList;
    private int tabuTenure;
    private Fitness fitnessFunction;

    public TabuSearch(int tabuTenure) {
        this.bins = new ArrayList<>();
        this.tabuList = new LinkedList<>();
        this.tabuTenure = tabuTenure;
        this.fitnessFunction = new Fitness();
    }

    public void pack(List<Item> items, int binSize) {
        List<Bin> currentSolution = new ArrayList<>();
        List<Bin> bestSolution = new ArrayList<>();
        double bestScore = Double.MAX_VALUE;

        // Initial solution
        FirstFit firstFit = new FirstFit();
        currentSolution = firstFit.getBins(items, binSize);
        bestSolution = new ArrayList<>(currentSolution);
        bestScore = fitnessFunction.calculateFitness(currentSolution);

        // Tabu search
        for (int i = 0; i < 1000; i++) {  // Run for a fixed number of iterations
            List<Bin> candidateSolution = generateCandidateSolution(currentSolution, items, binSize);
            double candidateScore = fitnessFunction.calculateFitness(candidateSolution);

            if (candidateScore < bestScore && !tabuList.contains(candidateSolution)) {
                currentSolution = candidateSolution;
                if (candidateScore < bestScore) {
                    bestSolution = candidateSolution;
                    bestScore = candidateScore;
                }
            }

            tabuList.add(currentSolution);
            if (tabuList.size() > tabuTenure) {
                tabuList.poll();
            }
        }

        bins = bestSolution;
    }

    private List<Bin> generateCandidateSolution(List<Bin> currentSolution, List<Item> items, int binSize) {
        // Generate a candidate solution by repacking the items with a different order
        List<Item> shuffledItems = new ArrayList<>(items);
        Collections.shuffle(shuffledItems);
        FirstFit firstFit = new FirstFit();
        return firstFit.getBins(shuffledItems, binSize);
    }

    public List<Bin> getBins() {
        return bins;
    }
}