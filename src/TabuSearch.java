import java.util.*;

public class TabuSearch {
    private List<Bin> bins;
    private Queue<List<Bin>> tabuList;
    private int tabuTenure;
    private Reader reader;
    private int NbIteration;
    private Fitness fitnessFunction;
    private Neighborhood neighborhood;

    public TabuSearch(int tabuTenure,int NbIteration) {
        this.bins = new ArrayList<>();
        this.tabuList = new LinkedList<>();
        this.tabuTenure = tabuTenure;
        this.fitnessFunction = new Fitness();
        this.NbIteration=NbIteration;
        this.reader = Reader.getInstance();
        this.neighborhood = new Neighborhood();
    }

    public void pack() {

        // Initial solution
        OneBinOneItem onebinoneitem = new OneBinOneItem();
        List<Bin> currentSolution = onebinoneitem.getBins();
        List<Bin> bestSolution = onebinoneitem.getBins();
        double currentScore = fitnessFunction.calculateFitness(currentSolution);
        double bestScore = fitnessFunction.calculateFitness(currentSolution);


        // Tabu search
        for (int i = 0; i < NbIteration; i++) {  // Run for a fixed number of iterations
            List<List<Bin>> neighborhoodSolutions = neighborhood.getNeighborhood(currentSolution);
            List<Bin> bestNeighbor = new ArrayList<>();
            double bestNeighborScore = Double.MAX_VALUE;

            for (List<Bin> candidateSolution : neighborhoodSolutions) {
                double candidateScore = fitnessFunction.calculateFitness(candidateSolution);

                if (candidateScore < bestNeighborScore && !tabuList.contains(candidateSolution)) {
                    bestNeighbor = candidateSolution;
                    bestNeighborScore = candidateScore;

                }
            }

            if (bestNeighborScore >= currentScore) {
                tabuList.add(currentSolution);
            }

            currentSolution = bestNeighbor;
            currentScore = bestNeighborScore;

            if (bestNeighborScore < bestScore) {
                bestSolution = bestNeighbor;
                bestScore = bestNeighborScore;
            }

            if (tabuList.size() > tabuTenure) {
                tabuList.poll();
            }
        }

        bins = bestSolution;
    }

    public List<Bin> getBins() {
        pack();
        return bins;
    }
}