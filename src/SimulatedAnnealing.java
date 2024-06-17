import java.util.*;

public class SimulatedAnnealing {
    private List<Bin> bins;
    private double temperature;
    private double coolingRate;

    private int NbChangeTemp;
    private int NbIterationInTemp;
    private Reader reader;
    private Fitness fitnessFunction;
    private Neighborhood neighborhood;

    public SimulatedAnnealing(double temperature, double coolingRate, int NbChangeTemp, int NbIterationInTemp) {
        this.bins = new ArrayList<>();
        this.temperature = temperature;
        this.coolingRate = coolingRate;
        this.NbChangeTemp=NbChangeTemp;
        this.NbIterationInTemp=NbIterationInTemp;
        this.reader = Reader.getInstance();
        this.fitnessFunction = new Fitness();
        this.neighborhood = new Neighborhood();
    }

    public void pack() {
        // Initial solution
        OneBinOneItem onebinoneitem = new OneBinOneItem();
        List<Bin> currentSolution = onebinoneitem.getBins();
        List<Bin> bestSolution = onebinoneitem.getBins();
        double currentScore = fitnessFunction.calculateFitness(currentSolution);
        double bestScore = fitnessFunction.calculateFitness(currentSolution);

        // Simulated annealing
        for (int i = 0; i < NbChangeTemp; i++) {
            for (int j = 0; j < NbIterationInTemp; j++) {
                List<List<Bin>> neighborhoodSolutions = neighborhood.getNeighborhood(currentSolution);
                List<Bin> candidateSolution = neighborhoodSolutions.get(new Random().nextInt(neighborhoodSolutions.size()));
                double candidateScore = fitnessFunction.calculateFitness(candidateSolution);

                if (candidateScore < currentScore || Math.exp((currentScore - candidateScore) / temperature) >= Math.random()) {
                    currentSolution = candidateSolution;
                    currentScore = candidateScore;
                }

                if (currentScore < bestScore) {
                    bestSolution = currentSolution;
                    bestScore = currentScore;
                }
            }

            temperature *= coolingRate;
        }

        bins = bestSolution;
    }

    public List<Bin> getBins() {
        pack();
        return bins;
    }
}