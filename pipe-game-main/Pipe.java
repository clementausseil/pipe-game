/**
 * Write a description of class Pipe here.
 * 
 * class for creating pipe objects
 * getters and setters and logic for finding where pipe openings are
 *
 * @author Clement Ausseil
 * @version Final version 14/08/2026
 */
public class Pipe
{
    String shape;//T,L,I or O (sources and sinks)
    int xPosition;
    int yPosition;
    int rotation; //in degrees (0,90,180,270)
    /**
     * Constructor for objects of class Pipe
     */
    public Pipe(String shape, int x, int y, int rotation/*,String type*/)
    {
        this.shape = shape;
        this.xPosition = x;
        this.yPosition = y;
        this.rotation = rotation;
    }

    //# Getter methods
    public String getShape(){return(this.shape);}

    public int getRotation(){return(this.rotation);}

    public int getX(){return(this.xPosition);}

    public int getY(){return(this.yPosition);}

    //# Setter methods
    public void setShape(String newShape){this.shape=newShape;}

    public void setX(int newX){this.xPosition=newX;}

    public void setY(int newY){this.yPosition=newY;}

    public void setRotation(int newRotation){this.rotation=newRotation;}

    //method that changes the rotation
    public void rotate(){
        this.rotation = (this.rotation+90)%360;
        //adds 90° dregrees, meaning quarter turn clockwise
        //'%' resets when rotation gets to 360°, back to 0°
    }

    /*
     * method to define where the pipe openings are for different shapes
     */
    public boolean[] getOpenings() {
        //start with unrotated pipe (0°)
        boolean[] base;
        // Order: N, E, S, W
        //true = opening
        switch (shape) {
            case "I": base = new boolean[]{false, true, false, true}; break;   
            case "L": base = new boolean[]{false, true, true, false}; break;  
            case "T": base = new boolean[]{true, true, false, true}; break;  
            case "O": base = new boolean[]{true, false, false, false}; break; 
            case "X": base = new boolean[]{true, true, false, true}; break; 
            default: base = new boolean[]{false, false, false, false};
        }
        int shift = rotation / 90; // how many 90° steps
        boolean[] rotated = new boolean[4];
        //rotating the pipe is the same as shifting the array values over
        //{f,f,t,t} --> {t,f,f,t}
        for (int i = 0; i < 4; i++) {
            rotated[(i + shift) % 4] = base[i];
        }
        return rotated;
    }
}
