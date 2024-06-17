import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BinPacking {
    public static void main(String[] args) {
        long startTime, endTime;
        Reader reader = Reader.getInstance(3);

// To be modified by the user
        FirstFit firstFit = new FirstFit();
        NextFit nextFit = new NextFit();
        TabuSearch tabuSearch = new TabuSearch(10,1000);
        SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing(50, 0.9, 10, 100);




        //Solution FirstFit
        startTime = System.currentTimeMillis();
        List<Bin> binsFirstFit = firstFit.getBins();
        endTime = System.currentTimeMillis();
        System.out.println("\n First Fit:");
        System.out.println("Number of bins: " + binsFirstFit.size());
        System.out.println("Execution time: " + (endTime - startTime) + "ms");

        //Solution NextFit
        startTime = System.currentTimeMillis();
        List<Bin> binsNextFit = nextFit.getBins();
        endTime = System.currentTimeMillis();
        System.out.println("\n Next Fit:");
        System.out.println("Number of bins: " + binsNextFit.size());
        System.out.println("Execution time: " + (endTime - startTime) + "ms");

        //Solution TabuSearch
        startTime = System.currentTimeMillis();
        List<Bin> binsTabuSearch = tabuSearch.getBins();
        endTime = System.currentTimeMillis();
        System.out.println("\n Tabu Search:");
        System.out.println("Number of bins: " + binsTabuSearch.size());
        System.out.println("Execution time: " + (endTime - startTime) + "ms");

        //Solution SimulatedAnnealing
        startTime = System.currentTimeMillis();
        List<Bin> binsSimulatedAnnealing = simulatedAnnealing.getBins();
        endTime = System.currentTimeMillis();
        System.out.println("\n Simulated Annealing:");
        System.out.println("Number of bins: " + binsSimulatedAnnealing.size());
        System.out.println("Execution time: " + (endTime - startTime) + "ms");

        // Print solution
        //Afficheur afficheur = new Afficheur(binsFirstFit);
    }

}
