/**
 * Write a description of class level here.
 * 
 * class for creating level objects
 *
 * @author Clement Ausseil
 * @version Final version 14/08/2026
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