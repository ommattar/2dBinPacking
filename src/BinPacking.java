import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BinPacking {
    public static void main(String[] args) {
        List<Item> data = solutionZero(1);
        List<Bin> bins = new ArrayList<>();

        // Solution ZERO : 1 Bin - 1 Item
        for(Item item : data) {
            if(item.getId() != 0) {
                //Item d'id 0 = taille du bin
                Bin bin = new Bin(data.get(0).getWidth(), data.get(0).getLength());
                bin.addItem(item);
                bins.add(bin);
            }
        }

        Afficheur afficheur = new Afficheur(bins);
    }

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

    public static void testAffichage() {
        // Création de quelques bins et items pour l'exemple
        Bin bin1 = new Bin(100, 200);
        bin1.addItem(new Item(1, 50, 50));
        System.out.println(bin1.getItems());
        bin1.addItem(new Item(1, 80, 80));
        System.out.println(bin1.getItems());

        Bin bin2 = new Bin(150, 250);
        bin2.addItem(new Item(1, 100, 100));
        bin2.addItem(new Item(1, 120, 120));

        List<Bin> bins = new ArrayList<>();
        bins.add(bin1);
        bins.add(bin2);


        Afficheur afficheur = new Afficheur(bins);
    }
}