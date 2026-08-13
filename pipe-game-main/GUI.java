/**
 * Write a description of class GUI here. 
 * 
 * creates GUI window, runs JFrame, menu and menuBar, and calls gamePanel
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

import java.awt.Graphics;
public class GUI extends JFrame implements ActionListener
{
    public final int GRID_SIZE = 7;
    public final int SQUARE_SIZE = 80;

    int[][] grid = new int[GRID_SIZE][GRID_SIZE];

    public String[][] level1 = new String[8][8];

    public int[] rotations = {0,90,180,270};

    gamePanel panel = new gamePanel();

    JMenuBar menuBar;
    JMenu menu;
    JMenuItem menuItemStory;
    JMenuItem menuItemTuto;

    JPanel gridPanel;
    JPanel borderPanel;

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
        menuBar.add(menu);

        //adding the menu items
        menuItemStory = new JMenuItem ("story");
        menuItemStory.addActionListener(this);
        menuItemTuto = new JMenuItem ("tutorial");
        menuItemTuto.addActionListener(this);
        menu.add(menuItemStory);
        menu.add(menuItemTuto);

        //panels
        this.add(panel);

        //dunno what this does but I guess its important
        this.pack();
        this.toFront();
        this.setVisible(true);
    }

    /*
     * method for actions (when menus are clicked)
     */
    public void actionPerformed(ActionEvent e){
        String cmd = e.getActionCommand();
        System.out.println(cmd);
        if(cmd.equals("story")){
            createStoryDialogBox();
        }else if (cmd.equals("tutorial")){
            createTutoDialogBox();
        }
    }

    /*
     * creating the dialog box for story line
     */
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

    /*
     * creating the dialog box for tutorial
     */
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