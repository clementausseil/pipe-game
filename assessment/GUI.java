
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
    // instance variables - replace the example below with your own
    JMenuBar menuBar;
    JMenu menu;
    JMenuItem menuItemStory;
    JMenuItem menuItemTuto;
    
    JPanel gamePanel;
    JPanel rightBorderPanel;
    JPanel topBorderPanel;
    int[][] grid = new int[7][7];
    
    /**
     * Constructor for objects of class GUI
     */
    public GUI()
    {
        //creating the window
        setTitle("Wellington Water Woes");       
        this.getContentPane().setPreferredSize(new Dimension(600,600));
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
        
        //panels
        gamePanel = new JPanel();
        
        gamePanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,5));
        this.add(gamePanel,BorderLayout.CENTER);
        gamePanel.setBackground(Color.pink);
        
        rightBorderPanel = new JPanel();
        rightBorderPanel.setLayout(new FlowLayout(FlowLayout.CENTER,50,5));
        this.add(rightBorderPanel,BorderLayout.EAST);
        rightBorderPanel.setBackground(Color.green);
        
        topBorderPanel = new JPanel();
        topBorderPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,50));
        this.add(topBorderPanel,BorderLayout.NORTH);
        topBorderPanel.setBackground(Color.green);
        
        
        
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
        
        if(cmd == "story"){
            createStoryDialogBox();
        }else if (cmd=="tutorial"){
            createTutoDialogBox();
        }
    }

    void createStoryDialogBox(){
        //creating dialog box
        JDialog box =new JDialog(this);
        box.setBounds (400,400,510,150);
        
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
    
}
