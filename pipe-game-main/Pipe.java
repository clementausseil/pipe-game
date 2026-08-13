
/**
 * Write a description of class Pipe here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Pipe
{
    String shape;//T,L,I or O (sources and sinks)
    int xPosition;
    int yPosition;
    int rotation; //in degrees (0,90,180,270)
    String type;//pipe, source or sink
    /**
     * Constructor for objects of class Pipe
     */
    public Pipe(String shape, int x, int y, int rotation/*,String type*/)
    {
        this.shape = shape;
        this.xPosition = x;
        this.yPosition = y;
        this.rotation = rotation;
        //this.type = type;
    }

    //# Getter methods
    public String getShape(){return(this.shape);}

    public int getRotation(){return(this.rotation);}

    public int getX(){return(this.xPosition);}

    public int getY(){return(this.yPosition);}
    //public String getType(){return(this.type);}

    //# Setter methods
    public void setShape(String newShape){this.shape=newShape;}

    public void setX(int newX){this.xPosition=newX;}

    public void setY(int newY){this.yPosition=newY;}

    public void setRotation(int newRotation){this.rotation=newRotation;}

    public void rotate(){
        this.rotation = (this.rotation+90)%360;
    }

    public boolean[] getConnections() {
        boolean[] base;
        // Order: N, E, S, W
        switch (shape) {
            case "I": base = new boolean[]{false, true, false, true}; break;   // N-S
            case "L": base = new boolean[]{false, true, true, false}; break;  // N-E
            case "T": base = new boolean[]{true, true, false, true}; break;   // N-E-S
            case "O": base = new boolean[]{true, false, false, false}; break; // sink: 1 opening
            case "X": base = new boolean[]{true, true, false, true}; break; // source: 1 opening
            default: base = new boolean[]{false, false, false, false};
        }
        int shift = rotation / 90; // how many 90° steps
        boolean[] rotated = new boolean[4];
        for (int i = 0; i < 4; i++) {
            rotated[(i + shift) % 4] = base[i];
        }
        return rotated;
    }
}
