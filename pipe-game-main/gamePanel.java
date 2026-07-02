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

import java.awt.Graphics;
public class gamePanel extends JPanel
{
    JPanel gridPanel;
    public final int GRID_SIZE = 7;
    public final int SQUARE_SIZE = 80;
    
    
    
    String source = "XSource.png";
    JLabel sourceLabel;
    String sink = "sink.png";
    JLabel sinkLabel;
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
        
        //Level level1 = new Level(initialiseLevel1());
        //grid(level1.getLayout());
    }
    public String[][] initialiseLevel1(){
        String[][] level1 = new String[GRID_SIZE][GRID_SIZE];
        
        
        level1[1][1] = "source"; 
        level1[1][2] = "source"; 
        level1[1][3] = "source"; 
        level1[1][4] = "sink"; 
        level1[5][3] = "sink"; 
        level1[4][3] = "source"; 
        level1[3][3] = "sink"; 
        
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
        
        Random rand = new Random();
        int imageCenter = SQUARE_SIZE / 2;
        
        Pipe[][] pipes = new Pipe[GRID_SIZE][GRID_SIZE];
        JButton[][] buttons = new JButton[GRID_SIZE][GRID_SIZE];
        
        
        initialiseLevel1();
        
        for(int x= 0;x<=GRID_SIZE-1;x++){
            for (int y = 0; y <= GRID_SIZE-1; y++) {
                JButton button = new JButton();
                //button.addActionListener(this);
                button.setActionCommand(x+","+y);
                
                int randomIndex = rand.nextInt(4);
                //int randomDirection = rotations[randomIndex];
                
                buttons[x][y] = button;
                //pipes[x][y] = new Pipe("e",x,y,randomDirection);
                
                if("sink".equals(level[x][y])){
                    button.setIcon(scaledSink);
                    pipes[x][y].setShape("o");
                    
                }else if("source".equals(level[x][y])){
                    
                    button.setIcon(scaledSource);
                    pipes[x][y].setShape("x");
                }
                
                //pipes[x][y].setRotation(randomDirection);

                
                
                
                
                
                button.setPreferredSize(buttonSize);
                gridPanel.add(button);
            }
        }
        
        
        // for(int x=0;x<(GRID_SIZE*50);x+=50){
            // for(int y=0;y<(GRID_SIZE*50);y+=50){
                
            // }  
        // }
    }
    
    
    protected void paintComponent(Graphics g){
        super.paintComponent(g); 
    }
}