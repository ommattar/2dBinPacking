import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    private static Reader instance;
    private int binSize;
    private int datasetNumber;
    private List<Item> items;

    private Reader(int datasetNumber) {
        this.binSize = 0;
        this.datasetNumber=datasetNumber;
        this.items = new ArrayList<>();
        Read();
    }

    public static Reader getInstance(int datasetNumber) {
        if (instance == null) {
            instance = new Reader(datasetNumber);
        }
        return instance;
    }

    public static Reader getInstance() {

        return instance;
    }

    public void Read() {


        String zero = "";
        if(datasetNumber < 10) {
            zero = "0";
        }
        try (BufferedReader br = new BufferedReader(new FileReader("Data/binpacking2d-" + zero + this.datasetNumber + ".bp2d"))) {
            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                if(lineNumber == 4) {
                    String[] itemsString = line.trim().split("\\s+");
                    this.binSize = Integer.parseInt(itemsString[1]);
                    System.out.println("Binsize : " + binSize);
                }

                if(lineNumber >= 8) {
                    String[] itemsString = line.trim().split("\\s+");
                    if (itemsString.length >= 3) {
                        try {
                            int id = Integer.parseInt(itemsString[0]);
                            int width = Integer.parseInt(itemsString[1]);
                            int height = Integer.parseInt(itemsString[2]);
                            this.items.add(new Item(id, width, height));
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
    }

    public int getBinSize() {
        return binSize;
    }

    public List<Item> getItems() {
        return items;
    }

    public int getDatasetNumber() {
        return datasetNumber;
    }
}
