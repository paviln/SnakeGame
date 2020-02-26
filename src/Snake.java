import java.util.ArrayList;


/**
 * @author Henrik
 * @since 1.0.0
 */
public class Snake
{
    private Square head;
    private ArrayList<Square> squares;
    Directions currentDirection, newDirection = Directions.LEFT;
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
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
            case LEFT:
                moveLeft();
                break;
            case RIGHT:
                moveRight();
                break;
        }
        test(head.move(xMovement, yMovement));
        squares.remove(0);
    }

    public void moveUp()
    {
        xMovement = 0;
        yMovement = -25;
    }

    public void moveDown()
    {
        xMovement = 0;
        yMovement = 25;
    }

    public void moveRight()
    {
        yMovement = 0;
        xMovement = 25;
    }

    public void moveLeft()
    {
        yMovement = 0;
        xMovement = -25;
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
