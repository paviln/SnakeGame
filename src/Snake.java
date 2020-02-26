import java.util.LinkedList;


/**
 * @author Henrik
 * @since 1.0.0
 */
public class Snake
{
    private int length;
    private LinkedList<Square> points = new LinkedList<>();
    private int xMovement;
    private int yMovement;
    private int head;

    public Snake(int x, int y)
    {
        grow(x,y);
    }

    public LinkedList<Square> getPoints()
    {
        return points;
    }

    public void grow(int x, int y)
    {
        points.add(new Square(x,y));
    }



    public void moveUp()
    {
        xMovement = 0;
        yMovement = -1;
        points.push(new Square(points.getFirst().getX(), points.getFirst().getY() - 25));
        points.removeLast();

    }

    public void moveDown()
    {
        if (yMovement == -1) return;
        xMovement = 0;
        yMovement = -1;
    }

    public void moveRight()
    {
        for (Square point : points)
        {
            point.setX(point.getY() + 1);
        }
    }

    public void moveLeft()
    {
        if (xMovement == -1) return;
        xMovement = -1;
        yMovement = 0;
    }


    //Grow
}
