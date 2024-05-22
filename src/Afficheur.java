import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class Afficheur extends JFrame {

    private List<Bin> bins;

    public Afficheur(List<Bin> bins) {
        this.bins = bins;
        setTitle("Afficheur de Bins");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        afficherBins();

        setVisible(true);
    }

    private void afficherBins() {
        for (Bin bin : bins) {
            JPanel binPanel = new JPanel();
            binPanel.setLayout(new FlowLayout());
            binPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            binPanel.setPreferredSize(new Dimension((int) bin.getWidth(), (int) bin.getLength()));

            for (Item item : bin.getItems()) {
                JPanel itemPanel = new JPanel();
                itemPanel.setPreferredSize(new Dimension((int) item.getWidth(), (int) item.getLength()));
                itemPanel.setBackground(generateRandomColor());
                binPanel.add(itemPanel);
            }

            add(binPanel);
        }
    }

    private Color generateRandomColor() {
        Random random = new Random();
        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }
}