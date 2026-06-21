
/**
 * Write a description of class level here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Level
{
    private String[][] layout;
    
    public Level(String [][] layout)
    {
        this.layout = layout;
    }

    public String[][] getLayout(){
        return(this.layout);
    }
    
    public void setLayout(String[][] newLayout){
        this.layout = newLayout;
    }
    
    
    
}