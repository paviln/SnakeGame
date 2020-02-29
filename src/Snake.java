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
    private int SIZE = Arena.SIZE;

    public Snake(Square square)
    {
        squares = new ArrayList<>();
        squares.add(square);
        head = square;
        grow();
        grow();
        grow();
        grow();
        grow();
    }

    public ArrayList<Square> getSquares()
    {
        return squares;
    }

    public Square getHead()
    {
        return head;
    }

    public void grow()
    {
        slide(head.move(xMovement, yMovement));
    }

    public void slide(Square square)
    {
        head = collision(square);
        squares.add(head);
    }

    public void move()
    {
        switch (currentDirection)
        {
            case UP:
                xMovement = 0;
                yMovement = -1;
                break;
            case DOWN:
                xMovement = 0;
                yMovement = 1;
                break;
            case LEFT:
                xMovement = -1;
                yMovement = 0;
                break;
            case RIGHT:
                xMovement = 1;
                yMovement = 0;
                break;
            case PAUSE:
                xMovement = 0;
                yMovement = 0;
                break;
        }
        slide(head.move(xMovement, yMovement));
        squares.remove(0);
    }

    public Square collision(Square square)
    {
        int x = square.getX();
        int y = square.getY();
        if (x >= SIZE) x = 0;
        if (y >= SIZE) y = 0;
        if (x < 0) x = SIZE - 1;
        if (y < 0) y = SIZE - 1;
        return new Square(x, y);
    }

    public Directions getCurrentDirection()
    {
        return currentDirection;
    }

    public void setCurrentDirection(Directions direction)
    {
        this.currentDirection = direction;
    }
}
