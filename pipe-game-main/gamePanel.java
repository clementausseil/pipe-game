/**
 * Write a description of class gamePanel here.
 * 
 * this class is the class that controls the JPanel, buttons, grid 
 * as well as game logic for filling pipes, checking wins and updating buttons
 * 
 *
 * @author Clement Ausseil
 * @version Final version 14/08/2026
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import java.util.Random;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
public class gamePanel extends JPanel implements ActionListener
{
    JPanel gridPanel;
    public final int GRID_SIZE = 7;
    public final int SQUARE_SIZE = 80;

    Pipe [][] pipes = new Pipe[GRID_SIZE][GRID_SIZE];
    JButton[][] buttons = new JButton[GRID_SIZE][GRID_SIZE];
    ImageIcon[][] baseIcons = new ImageIcon[GRID_SIZE][GRID_SIZE];

    String source = "TSource.png";
    JLabel sourceLabel;
    String sink = "sink.png";
    JLabel sinkLabel;
    String LPipe = "LPipe.png";
    JLabel LPipeLabel;
    String TPipe = "TPipe.png";
    JLabel TPipeLabel;
    String IPipe = "IPipe.png";
    JLabel IPipeLabel;

    Image backgroundImage;
    
    public gamePanel()
    {
        JPanel borderPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 50));

        gridPanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
        gridPanel.setBounds(0,0,300,300);

        gridPanel.setOpaque(false);
        borderPanel.setOpaque(false);
        this.add(borderPanel);
        borderPanel.add(gridPanel);

        ImageIcon sourceImage = new ImageIcon(source);
        sourceLabel = new JLabel(sourceImage);

        ImageIcon sinkImage = new ImageIcon(sink);
        sinkLabel = new JLabel(sinkImage);

        ImageIcon LPipeImage = new ImageIcon(LPipe);
        LPipeLabel = new JLabel(LPipeImage);

        ImageIcon IPipeImage = new ImageIcon(IPipe);
        IPipeLabel = new JLabel(IPipeImage);

        ImageIcon TPipeImage = new ImageIcon(TPipe);
        TPipeLabel = new JLabel(TPipeImage);

        backgroundImage = new ImageIcon("conc.jpg").getImage();

        Level level1 = new Level(initialiseLevel1());
        grid(level1.getLayout());
    }

    /*
     * method for drawing the grid and adding the pipe images
     */
    public void grid (String[][] level){
        Dimension buttonSize = new Dimension(80, 80); 

        ImageIcon sourceImage = new ImageIcon(source);
        Image mySource = sourceImage.getImage().getScaledInstance(SQUARE_SIZE,SQUARE_SIZE, Image.SCALE_SMOOTH); 
        ImageIcon scaledSource = new ImageIcon(mySource);

        ImageIcon sinkImage = new ImageIcon(sink);
        Image mySink = sinkImage.getImage().getScaledInstance(SQUARE_SIZE,SQUARE_SIZE, Image.SCALE_SMOOTH); 
        ImageIcon scaledSink = new ImageIcon(mySink);

        ImageIcon LPipeImage = new ImageIcon(LPipe);
        Image myLPipe = LPipeImage.getImage().getScaledInstance(SQUARE_SIZE,SQUARE_SIZE, Image.SCALE_SMOOTH); 
        ImageIcon scaledLPipe = new ImageIcon(myLPipe);        

        ImageIcon IPipeImage = new ImageIcon(IPipe);
        Image myIPipe = IPipeImage.getImage().getScaledInstance(SQUARE_SIZE,SQUARE_SIZE, Image.SCALE_SMOOTH); 
        ImageIcon scaledIPipe = new ImageIcon(myIPipe); 

        ImageIcon TPipeImage = new ImageIcon(TPipe);
        Image myTPipe = TPipeImage.getImage().getScaledInstance(SQUARE_SIZE,SQUARE_SIZE, Image.SCALE_SMOOTH); 
        ImageIcon scaledTPipe = new ImageIcon(myTPipe); 

        Random rand = new Random();
        int imageCenter = SQUARE_SIZE / 2;

        for(int x= 0;x<=GRID_SIZE-1;x++){
            for (int y = 0; y <= GRID_SIZE-1; y++) {
                JButton button = new JButton();
                button.setActionCommand(x+","+y);
                button.addActionListener(this);

                button.setOpaque(false);
                button.setContentAreaFilled(false);
                button.setBorderPainted(false);
                int randomIndex = rand.nextInt(4);
                int randomRotation = randomIndex*90;

                buttons[x][y] = button;
                pipes[x][y] = new Pipe("e", x, y, randomRotation);

                ImageIcon baseIcon = null; // track which base image this cell uses
                if ("sink".equals(level[x][y])) {
                    pipes[x][y].setShape("O");
                    baseIcon = scaledSink;
                } else if ("source".equals(level[x][y])) {
                    pipes[x][y].setShape("X");
                    baseIcon = scaledSource;
                } else if ("L".equals(level[x][y])) {
                    pipes[x][y].setShape("L");
                    baseIcon = scaledLPipe;
                } else if ("I".equals(level[x][y])) {
                    pipes[x][y].setShape("I");
                    baseIcon = scaledIPipe;
                } else if ("T".equals(level[x][y])) {
                    pipes[x][y].setShape("T");
                    baseIcon = scaledTPipe;
                }

                baseIcons[x][y] = baseIcon;
                updateButtonIcon(button, pipes[x][y], baseIcon); // render at the correct random rotation immediately

                button.setPreferredSize(buttonSize);
                gridPanel.add(button);
            }
        }

    }

    /*
     * method for reading button clicks
     */
    public void actionPerformed(ActionEvent e){
        String[] coords = e.getActionCommand().split(",");
        int x = Integer.parseInt(coords[0]);
        int y = Integer.parseInt(coords[1]);

        Pipe pipe = pipes[x][y];
        System.out.println("click at"+x+","+y);
        if (pipe==null){
            return;
        }else{
            //System.out.println(pipe.getRotation());
            pipe.rotate();
            //System.out.println(pipe.getRotation());

            updateButtonIcon(buttons[x][y],pipe, baseIcons[x][y]);
        }

        if (checkWin()) {
            int choice = JOptionPane.showOptionDialog(
                    this,
                    "you win! all sinks are full",
                    "you win!",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    new String[]{"play level again?", "close"},
                    "play again");
            if (choice == JOptionPane.YES_OPTION) {
                resetGame();
            }
        }
    }    

    /*
     * updates the pipe icons after they are rotated
     * redraws them at their new rotation
     */

    private void updateButtonIcon(JButton button, Pipe pipe, ImageIcon baseIcon){
        Image original = baseIcon.getImage();
        BufferedImage buffered = new BufferedImage(SQUARE_SIZE, SQUARE_SIZE, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = buffered.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        AffineTransform tx = new AffineTransform();
        tx.rotate(Math.toRadians(pipe.getRotation()), SQUARE_SIZE / 2.0, SQUARE_SIZE / 2.0);
        g2d.drawImage(original, tx, null);
        g2d.dispose();

        button.setIcon(new ImageIcon(buffered));
    }

    /*
     * checks that all the 'sink' pipes are full
     */
    public boolean checkWin() {
        boolean[][] full = new boolean[GRID_SIZE][GRID_SIZE];
        int sourceX = -1;
        int sourceY = -1;

        //searches for source, 
        //technically not neccessary if I know the source is at 3,3 
        //but useful if I want to change the layout or add levels
        for (int x = 0; x < GRID_SIZE; x++) {
            for (int y = 0; y < GRID_SIZE; y++) {
                if ("X".equals(pipes[x][y].getShape())) {
                    sourceX = x; sourceY = y;
                }
            }
        }

        //call floodfill on the source pipe, floodfill starts from there
        floodFill(sourceX, sourceY, full);

        boolean allConnected = true;
        for (int x = 0; x < GRID_SIZE; x++) {
            for (int y = 0; y < GRID_SIZE; y++) {
                if ("O".equals(pipes[x][y].getShape())) {
                    if (!full[x][y]) allConnected = false;
                }
            }
        }
        return allConnected;
    }

    /*
     * floofill method 
     * immitates water flowing through pipes
     *  checks each side of the cell to see if:
    the cell has a pipe opening on its that side
    the cell isn't against a wall
    the cell neighbouring it on this side has an opening on its opposite side
    if so will call the floodfill method on that adjacent cell
     */
    private void floodFill(int x, int y, boolean[][] full) {
        if (x < 0 || x >= GRID_SIZE || y < 0 || y >= GRID_SIZE){
            return;
        }

        if (full[x][y]){
            return;
        }

        full[x][y] = true;

        boolean[] openings = pipes[x][y].getOpenings(); // N,E,S,W

        //checks 'north' neighbour
        if (openings[0] && x > 0 && pipes[x-1][y].getOpenings()[2]) {
            floodFill(x - 1, y, full);
        }
        // same for 'east' neighbour
        if (openings[1] && y < GRID_SIZE - 1 && pipes[x][y+1].getOpenings()[3]) {
            floodFill(x, y + 1, full);
        }
        // south
        if (openings[2] && x < GRID_SIZE - 1 && pipes[x+1][y].getOpenings()[0]) {
            floodFill(x + 1, y, full);
        }
        // and west
        if (openings[3] && y > 0 && pipes[x][y-1].getOpenings()[1]) {
            floodFill(x, y - 1, full);
        }
    }

    /*
     * method for restarting
     * clears panel then reprints the grid
     */
    public void resetGame() {
        gridPanel.removeAll(); // clear old buttons off the panel

        Level level1 = new Level(initialiseLevel1());
        grid(level1.getLayout());

        gridPanel.revalidate();
        gridPanel.repaint();
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g); 
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

    }

    /*
     * method to hold the information for the level
     */
    public String[][] initialiseLevel1(){
        String[][] level1 = new String[GRID_SIZE][GRID_SIZE];

        level1[0][0] = "sink";
        level1[0][1] = "sink"; 
        level1[0][2] = "sink"; 
        level1[0][3] = "T"; 
        level1[0][4] = "sink";
        level1[0][5] = "L";
        level1[0][6] = "sink";

        level1[1][0] = "L"; 
        level1[1][1] = "T"; 
        level1[1][2] = "T"; 
        level1[1][3] = "L"; 
        level1[1][4] = "sink";
        level1[1][5] = "T";
        level1[1][6] = "sink";

        level1[2][0] = "sink"; 
        level1[2][1] = "I"; 
        level1[2][2] = "T"; 
        level1[2][3] = "T"; 
        level1[2][4] = "I";
        level1[2][5] = "T";
        level1[2][6] = "T";

        level1[3][0] = "L";         
        level1[3][1] = "T"; 
        level1[3][2] = "T"; 
        level1[3][3] = "source"; 
        level1[3][4] = "T";
        level1[3][5] = "L";
        level1[3][6] = "sink";

        level1[4][0] = "I"; 
        level1[4][1] = "sink"; 
        level1[4][2] = "T"; 
        level1[4][3] = "sink"; 
        level1[4][4] = "I";
        level1[4][5] = "T";
        level1[4][6] = "sink";

        level1[5][0] = "T";
        level1[5][1] = "sink"; 
        level1[5][2] = "L"; 
        level1[5][3] = "sink"; 
        level1[5][4] = "I";
        level1[5][5] = "L";
        level1[5][6] = "L";

        level1[6][0] = "L";
        level1[6][1] = "I"; 
        level1[6][2] = "sink"; 
        level1[6][3] = "sink"; 
        level1[6][4] = "T";
        level1[6][5] = "sink";
        level1[6][6] = "sink";

        return level1;
    }
}