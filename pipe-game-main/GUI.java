
/**
 * Write a description of class GUI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
public class GUI extends JFrame implements ActionListener,MouseListener
{
    public final int GRID_SIZE = 7;
    
    int[][] grid = new int[GRID_SIZE][GRID_SIZE];
    
    public String[][] level1 = new String[8][8];
    
    
    JMenuBar menuBar;
    JMenu menu;
    JMenuItem menuItemStory;
    JMenuItem menuItemTuto;

    JPanel gridPanel;
    JPanel borderPanel;
    
    String source = "XSource.png";
    JLabel sourceLabel;
    String sink = "sink.png";
    JLabel sinkLabel;
    /**
     * Constructor for objects of class GUI
     */
    public GUI()
    {
        //creating the window
        setTitle("Wellington Water Woes");       
        this.getContentPane().setPreferredSize(new Dimension(800,800));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //creating the menuBar
        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        //creating the "info" menu
        menu = new JMenu("?");
        //menu.addActionListener(this);
        menuBar.add(menu);

        //adding the menu items
        menuItemStory = new JMenuItem ("story");
        menuItemStory.addActionListener(this);
        menuItemTuto = new JMenuItem ("tutorial");
        menuItemTuto.addActionListener(this);
        menu.add(menuItemStory);
        menu.add(menuItemTuto);

        //mouse listener
        addMouseListener(this);

        JFrame frame = new JFrame ("cuh");
        frame.setSize(600, 400);
        frame.setLayout(null);

        //panels
        
        JPanel borderPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 50));
        //borderPanel.setBackground(Color.pink);
        
        gridPanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
        gridPanel.setBounds(0,0,300,300);
        
        //gamePanel.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
        
        
        this.add(borderPanel);
        borderPanel.add(gridPanel);

        //declare images
        ImageIcon sourceImage = new ImageIcon(source);
        sourceLabel = new JLabel(sourceImage);
        
        ImageIcon sinkImage = new ImageIcon(sink);
        sinkLabel = new JLabel(sinkImage);
        
        
        
        grid();
        //gridPanel.add(sourceLabel);
        //grid();
        
        //dunno what this does but I guess its important
        this.pack();
        this.toFront();
        this.setVisible(true);
    }

    public void mouseEntered(MouseEvent e){System.out.println("enter");}
    public void mouseExited(MouseEvent e){System.out.println("exit");}
    public void mousePressed(MouseEvent e){System.out.println("press");}
    public void mouseReleased(MouseEvent e){System.out.println("release");}
    public void mouseClicked(MouseEvent e){
        int mouseX = e.getX();
        int mouseY = e.getY();
        System.out.println("click at"+mouseX+","+mouseY);
    }

    public void actionPerformed(ActionEvent e){
        String cmd = e.getActionCommand();
        System.out.println(cmd);
        if(cmd == "story"){
            createStoryDialogBox();
        }else if (cmd=="tutorial"){
            createTutoDialogBox();
        }else {
            String[] coords = e.getActionCommand().split(",");
            System.out.println("button pressed at "+coords[0]+","+ coords[1]);
        }
    }
    
    public boolean buttonPressed(ActionEvent b){
        String cmd = b.getActionCommand();
        
        if(cmd != null){
            return (true);
        }else{
            return(false);
        }
    }    

    void createStoryDialogBox(){
        //creating dialog box
        JDialog box =new JDialog(this);
        box.setBounds (400,400,520,150);

        //adding text to box
        TextArea boxInfo = new TextArea("Wellington Water Woes! \nWellington has a problem with aging water infrastructure and needs YOUR help to fix it!\nReturn the flow to all the places in the city that need water");
        box.add(boxInfo);

        //important probably
        box.toFront();
        box.setVisible(true);
        box.setTitle("backround info");
    }

    void createTutoDialogBox(){
        //creating dialog box
        JDialog box =new JDialog(this);
        box.setBounds (400,400,600,150);
        //adding text to box
        TextArea boxInfo = new TextArea("Aim: return flow to all sinks by connecting them all to the source\n - Rotate pipes by clicking them\n - Ensure all sinks (grey circles) are connected to the SOURCE(blue circle), to return flow  \n- Sinks connected to other sinks without being connected to the source don't count as having flow");
        box.add(boxInfo);

        //important probably
        box.toFront();
        box.setVisible(true);
        box.setTitle("how to play");
    }

    public void initialiseLevel1(){
        level1[5][3] = "sink"; 
        level1[4][3] = "source"; 
    }
    
    
    
    public void grid (){
        Dimension buttonSize = new Dimension(80, 80); 
        
        ImageIcon sourceImage = new ImageIcon(source);
        Image mySource = sourceImage.getImage().getScaledInstance(80,80, Image.SCALE_SMOOTH); 
        ImageIcon scaledSource = new ImageIcon(mySource);
        
        ImageIcon sinkImage = new ImageIcon(sink);
        Image mySink = sinkImage.getImage().getScaledInstance(80,80, Image.SCALE_SMOOTH); 
        ImageIcon scaledSink = new ImageIcon(mySink);
        
        initialiseLevel1();
        
        for(int x= 1;x<=GRID_SIZE;x++){
            for (int y = 1; y <= GRID_SIZE; y++) {
                JButton button = new JButton();
                button.addActionListener(this);
                button.setActionCommand(x+","+y);
                Pipe pipe = new Pipe("e",x,y,"n");
                
                if(level1[x][y] == "sink"){
                    button.setIcon(scaledSink);
                    pipe.setShape("o");
                }else if(level1[x][y] == "source"){
                    button.setIcon(scaledSource);
                    pipe.setShape("x");
                }
                
                button.setPreferredSize(buttonSize);
                gridPanel.add(button);
            }
        }
        
        
        // for(int x=0;x<(GRID_SIZE*50);x+=50){
            // for(int y=0;y<(GRID_SIZE*50);y+=50){
                
            // }  
        // }
    }
}