import java.util.ArrayList;


/**
 * @author Henrik
 * @since 1.0.0
 */
public class Snake
{
    Directions currentDirection = Directions.PAUSE;
    private Square head;
    private ArrayList<Square> squares; //rename to snakeSquares? Otherwise it has the same name as the 2D array in Arena.
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
                xMovement = -25;
                yMovement = 0;
                break;
            case RIGHT:
                xMovement = 25;
                yMovement = 0;
                break;
            case PAUSE:
                xMovement = 0;
                yMovement = 0;
                break;
        }
        test(head.move(xMovement, yMovement));
        squares.remove(0);
    }

    public void setNewDirection(Directions direction)
    {
        this.currentDirection = direction;
    }

    public Directions getCurrentDirection()
    {
        return currentDirection;
    }
}
