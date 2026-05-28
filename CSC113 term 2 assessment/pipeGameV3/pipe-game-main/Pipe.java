
/**
 * Write a description of class Pipe here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Pipe
{
    char shape;//T,L,I or O (sources and sinks)
    int xPosition;
    int yPosition;
    char rotation; //N,E,S,W
    String type;//pipe, source or sink
    /**
     * Constructor for objects of class Pipe
     */
    public Pipe(char shape, int x, int y, char rotation, String type)
    {
        this.shape = shape;
        this.xPosition = x;
        this.yPosition = y;
        this.rotation = rotation;
        this.type = type;
    }
    
    //# Getter methods
    public char getShape(){return(this.shape);}
    public char getRotation(){return(this.rotation);}
    public int getX(){return(this.xPosition);}
    public int getY(){return(this.yPosition);}
    public String getType(){return(this.type);}
    
    //# Setter methods
    public void setShape(char newShape){this.shape=newShape;}
    public void setX(int newX){this.xPosition=newX;}
    public void setY(int newY){this.yPosition=newY;}
    public void setRotation(char newRotation){this.rotation=newRotation;}
    public void setType(String newType){this.type=newType;}
}
