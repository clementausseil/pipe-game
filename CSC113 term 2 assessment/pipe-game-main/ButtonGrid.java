import java.awt.*;
import javax.swing.*;

public class ButtonGrid extends JFrame {
    public ButtonGrid() {
        setTitle("Wellington Water Woes");       
        this.getContentPane().setPreferredSize(new Dimension(600,600));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
        JFrame frame = new JFrame("Grid of Buttons");
        JPanel panel = new JPanel();

        panel.setBounds(5,5,56,56);
        panel.setLayout(new GridLayout(7, 7, 5, 5)); 

        // Add 12 buttons to the grid
        for (int i = 1; i <= 49; i++) {
            panel.add(new JButton("Button " + i));
        }

        frame.add(panel); // Add the panel to the frame
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}