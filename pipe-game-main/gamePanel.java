import javax.swing.*;
import java.awt.*;

/**
 * Write a description of class gamePanel here.
 *
 * @author (your name)
 * @version (a version number or a date)
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
    public gamePanel()
    {
        JPanel borderPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 50));
        //borderPanel.setBackground(Color.pink);

        gridPanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
        gridPanel.setBounds(0,0,300,300);

        //gamePanel.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));

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
        //initialiseLevel1();

        Level level1 = new Level(initialiseLevel1());
        grid(level1.getLayout());
    }

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

        // Pipe[][] pipes = new Pipe[GRID_SIZE][GRID_SIZE];
        // JButton[][] buttons = new JButton[GRID_SIZE][GRID_SIZE];

        for(int x= 0;x<=GRID_SIZE-1;x++){
            for (int y = 0; y <= GRID_SIZE-1; y++) {
                JButton button = new JButton();
                button.setActionCommand(x+","+y);
                button.addActionListener(this);

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

        // for(int x=0;x<(GRID_SIZE*50);x+=50){
        // for(int y=0;y<(GRID_SIZE*50);y+=50){

        // }  
        // }
    }

    public void actionPerformed(ActionEvent e){
        // String cmd = b.getActionCommand();

        // if(cmd != null){
        // return (true);
        // }else{
        // return(false);
        // }

        String[] coords = e.getActionCommand().split(",");
        int x = Integer.parseInt(coords[0]);
        int y = Integer.parseInt(coords[1]);

        Pipe pipe = pipes[x][y];
        System.out.println("click at"+x+","+y);
        if (pipe==null){
            return;
        }else{
            System.out.println(pipe.getRotation());
            pipe.rotate();
            System.out.println(pipe.getRotation());

            updateButtonIcon(buttons[x][y],pipe, baseIcons[x][y]);
        }

        if (checkWin()) {
            JOptionPane.showMessageDialog(this, "You win! All sinks have water.");
            System.out.println("win");

        }else{
            printGridConnections();
            explainSink(0, 6);
            explainSink(1, 4);
            explainSink(1, 6);
            explainSink(2, 0);
            explainSink(3, 6);
            explainSink(5, 1);
            explainSink(6, 2);
            explainSink(6, 3);
            explainSink(6, 5);
        }
    }    

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

    public boolean checkWin() {
        boolean[][] visited = new boolean[GRID_SIZE][GRID_SIZE];
        int sourceX = -1, sourceY = -1;

        outer:
        for (int x = 0; x < GRID_SIZE; x++) {
            for (int y = 0; y < GRID_SIZE; y++) {
                if ("X".equals(pipes[x][y].getShape())) {
                    sourceX = x; sourceY = y;
                    break outer;
                }
            }
        }
        System.out.println("source at " + sourceX + "," + sourceY);
        if (sourceX == -1) return false;

        //floodFill(sourceX, sourceY, visited);
        floodFillDebug(sourceX, sourceY, visited);
        
        
        boolean allConnected = true;
        for (int x = 0; x < GRID_SIZE; x++) {
            for (int y = 0; y < GRID_SIZE; y++) {
                if ("O".equals(pipes[x][y].getShape())) {
                    System.out.println("sink (" + x + "," + y + ") visited=" + visited[x][y]);
                    if (!visited[x][y]) allConnected = false;
                }
            }
        }
        return allConnected;
    }

    private void floodFill(int x, int y, boolean[][] visited) {
        if (x < 0 || x >= GRID_SIZE || y < 0 || y >= GRID_SIZE) return;
        if (visited[x][y]) return;
        visited[x][y] = true;

        boolean[] conn = pipes[x][y].getConnections(); // N,E,S,W

        // North neighbor: row above → x - 1
        if (conn[0] && x > 0 && pipes[x-1][y].getConnections()[2]) {
            floodFill(x - 1, y, visited);
        }
        // East neighbor: same row, column to the right → y + 1
        if (conn[1] && y < GRID_SIZE - 1 && pipes[x][y+1].getConnections()[3]) {
            floodFill(x, y + 1, visited);
        }
        // South neighbor: row below → x + 1
        if (conn[2] && x < GRID_SIZE - 1 && pipes[x+1][y].getConnections()[0]) {
            floodFill(x + 1, y, visited);
        }
        // West neighbor: same row, column to the left → y - 1
        if (conn[3] && y > 0 && pipes[x][y-1].getConnections()[1]) {
            floodFill(x, y - 1, visited);
        }
    }

    private void floodFillDebug(int x, int y, boolean[][] visited) {
        if (x < 0 || x >= GRID_SIZE || y < 0 || y >= GRID_SIZE) return;
        if (visited[x][y]) return;
        visited[x][y] = true;
        System.out.println("visit (" + x + "," + y + ") shape=" + pipes[x][y].getShape()
            + " rot=" + pipes[x][y].getRotation());

        boolean[] conn = pipes[x][y].getConnections();
        String[] dirs = {"N", "E", "S", "W"};
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int[] opp = {2, 3, 0, 1};

        for (int i = 0; i < 4; i++) {
            if (!conn[i]) continue;
            int nx = x + dx[i], ny = y + dy[i];
            if (nx < 0 || nx >= GRID_SIZE || ny < 0 || ny >= GRID_SIZE) continue;
            boolean back = pipes[nx][ny].getConnections()[opp[i]];
            if (back) {
                floodFillDebug(nx, ny, visited);
            } else {
                System.out.println("  BLOCKED " + dirs[i] + " -> (" + nx + "," + ny + ") shape="
                    + pipes[nx][ny].getShape() + " rot=" + pipes[nx][ny].getRotation());
            }
        }
    }

    public void printGridConnections() {
        String[] dirs = {"N", "E", "S", "W"};
        for (int x = 0; x < GRID_SIZE; x++) {
            for (int y = 0; y < GRID_SIZE; y++) {
                Pipe p = pipes[x][y];
                boolean[] conn = p.getConnections();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < 4; i++) {
                    if (conn[i]) sb.append(dirs[i]).append(" ");
                }
                System.out.printf("(%d,%d) shape=%s rot=%d open=[%s]%n",
                    x, y, p.getShape(), p.getRotation(), sb.toString().trim());
            }
        }
    }

    public void explainSink(int x, int y) {
        String[] dirs = {"N", "E", "S", "W"};
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int[] opp = {2, 3, 0, 1}; // opposite direction index

        boolean[] conn = pipes[x][y].getConnections();
        System.out.println("Sink (" + x + "," + y + ") rot=" + pipes[x][y].getRotation());
        for (int i = 0; i < 4; i++) {
            if (!conn[i]) continue;
            int nx = x + dx[i], ny = y + dy[i];
            System.out.print("  opening " + dirs[i] + " -> ");
            if (nx < 0 || nx >= GRID_SIZE || ny < 0 || ny >= GRID_SIZE) {
                System.out.println("edge of grid (dead end)");
                continue;
            }
            Pipe neighbor = pipes[nx][ny];
            boolean matches = neighbor.getConnections()[opp[i]];
            System.out.println("neighbor (" + nx + "," + ny + ") shape=" + neighbor.getShape()
                + " rot=" + neighbor.getRotation() + " facing back=" + matches);
        }
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g); 
    }
}