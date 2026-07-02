
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
    //public void setType(String newType){this.type=newType;}
    
    public void rotate(){
        
    }
}
