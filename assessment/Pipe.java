
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
    
    
    
}
