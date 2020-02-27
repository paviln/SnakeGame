import java.util.ArrayList;


/**
 * @author Henrik
 * @since 1.0.0
 */
public class Snake
{
    Directions currentDirection, newDirection = Directions.LEFT;
    private Square head;
    private ArrayList<Square> squares;
    private int xMovement = 0;
    private int yMovement = 0;

    public Snake(Square square)
    {
        squares = new ArrayList<>();
        squares.add(square);
        head = square;
        grow();
        grow();
    }

    public ArrayList<Square> getSquares()
    {
        return squares;
    }

    public void grow()
    {
        test(head.move(xMovement, yMovement));
    }

    public void test(Square square)
    {
        head = square;
        squares.add(head);
    }

    public void move()
    {
        if (head.getX() % 25 == 0 && head.getY() % 25 == 0)
        {
            currentDirection = newDirection;
        }
        switch (currentDirection)
        {
            case UP:
                xMovement = 0;
                yMovement = -25;
                break;
            case DOWN:
                xMovement = 0;
                yMovement = 25;
                break;
            case LEFT:
                yMovement = 0;
                xMovement = 25;
                break;
            case RIGHT:
                yMovement = 0;
                xMovement = -25;
                break;
        }
        test(head.move(xMovement, yMovement));
        squares.remove(0);
    }


    public void setNewDirection(Directions direction)
    {
        this.newDirection = direction;
    }

    public Directions getCurrentDirection()
    {
        return currentDirection;
    }
}
