import java.util.ArrayList;


/**
 * @author Henrik
 * @since 1.0.0
 */
public class Snake
{
    private int length;
    private ArrayList<Square> points;
    private int xMovement;
    private int yMovement;
    private int head;

    public Snake(int x, int y)
    {
      grow(x,y);
    }

    private ArrayList<Square> getPoints()
    {
        return points;
    }

    public void grow(int x, int y)
    {
        points.add(new Square(x,y));
    }

    

    private void moveUp()
    {
        if (yMovement == 1) return;
        xMovement = 0;
        yMovement = 1;
    }

    private void moveDown()
    {
        if (yMovement == -1) return;
        xMovement = 0;
        yMovement = -1;
    }

    private void moveRight()
    {
        if (xMovement == 1) return;
        xMovement = 1;
        yMovement = 0;
    }

    private void moveLeft()
    {
        if (xMovement == -1) return;
        xMovement = -1;
        yMovement = 0;
    }


    //Grow
}
