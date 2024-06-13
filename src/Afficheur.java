import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;
public class Afficheur extends JFrame {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private List<Bin> bins;
    private double scale = 1.0;

    public Afficheur(List<Bin> bins) {
        this.bins = bins;
        setTitle("Afficheur de Bins");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize((int) screenSize.getWidth(), 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Ajout des boutons de zoom et de dézoom
        JPanel controlsPanel = new JPanel();
        JButton zoomInButton = new JButton("Zoom +");
        JButton zoomOutButton = new JButton("Zoom -");

        zoomInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scale += 0.1;
                afficherBins();
            }
        });

        zoomOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scale = Math.max(0.1, scale - 0.1);
                afficherBins();
            }
        });

        controlsPanel.add(zoomInButton);
        controlsPanel.add(zoomOutButton);

        add(controlsPanel, BorderLayout.NORTH);

        // Affichage des bins
        afficherBins();

        setVisible(true);
    }

    private void afficherBins() {
        // Supprimer tous les composants existants
        getContentPane().removeAll();

        // Ajouter le panneau de contrôle des boutons
        JPanel controlsPanel = new JPanel();
        JButton zoomInButton = new JButton("Zoom +");
        JButton zoomOutButton = new JButton("Zoom -");

        zoomInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scale += 0.1;
                afficherBins();
            }
        });

        zoomOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scale = Math.max(0.1, scale - 0.1);
                afficherBins();
            }
        });

        controlsPanel.add(zoomInButton);
        controlsPanel.add(zoomOutButton);

        add(controlsPanel, BorderLayout.NORTH);

        JPanel binsPanel = new JPanel();
        binsPanel.setLayout(new FlowLayout());

        for (Bin bin : bins) {
            JPanel binPanel = new JPanel();
            binPanel.setLayout(new FlowLayout());
            binPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            binPanel.setPreferredSize(new Dimension((int) (bin.getWidth() * scale), (int) (bin.getLength() * scale)));

            for (Item item : bin.getItems()) {
                JPanel itemPanel = new JPanel();
                itemPanel.setPreferredSize(new Dimension((int) (item.getWidth() * scale), (int) (item.getLength() * scale)));
                itemPanel.setBackground(item.getBinColor());
                binPanel.add(itemPanel);
            }

            binsPanel.add(binPanel);
        }

        add(new JScrollPane(binsPanel), BorderLayout.CENTER);

        // Rafraîchir l'affichage
        revalidate();
        repaint();
    }



}