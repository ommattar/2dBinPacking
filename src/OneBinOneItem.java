import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OneBinOneItem {
    public static List<Item> solutionZero(int datasetNumber) {
        String zero = "";
        if(datasetNumber < 10) {
            zero = "0";
        }

        List<Item> items = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Data/binpacking2d-" + zero + datasetNumber + ".bp2d"))) {
            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                if(lineNumber == 4) {
                    String[] itemsString = line.trim().split("\\s+");
                    int binSize = Integer.parseInt(itemsString[1]);
                    items.add(new Item(0, binSize, binSize));
                    System.out.println("Binsize : " + binSize);
                }

                if(lineNumber >= 8) {
                    String[] itemsString = line.trim().split("\\s+");
                    if (itemsString.length >= 3) {
                        try {
                            int id = Integer.parseInt(itemsString[0]);
                            int width = Integer.parseInt(itemsString[1]);
                            int height = Integer.parseInt(itemsString[2]);
                            items.add(new Item(id, width, height));
                            System.out.println("Item " + id + " : " + width + " x " + height);
                        }
                        catch (NumberFormatException e) {
                            System.err.println("Error parsing line " + lineNumber + ": " + e.getMessage());
                        }
                    }
                }
            }
        } catch (IOException e) {
            // Handle the exception
            e.printStackTrace();
        }
        return items;
    }
}
