import javax.swing.*;
import java.awt.*;

public class GridExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Button Grid");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 900);

        // Outer panel (prevents stretching)
        JPanel outerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 50));

        // Grid panel (your buttons)
        JPanel gridPanel = new JPanel(new GridLayout(7, 7)); // rows, cols, gaps

        Dimension buttonSize = new Dimension(80, 80);

        for (int i = 1; i <= 49; i++) {
            JButton button = new JButton("Btn " + i);
            button.setPreferredSize(buttonSize); // fixed size
            gridPanel.add(button);
        }

        outerPanel.add(gridPanel);
        frame.add(outerPanel);

        frame.setVisible(true);
    }
}