import java.util.ArrayList;


/**
 * @author Henrik
 * @since 1.0.0
 */
public class Snake
{
    Directions currentDirection = Directions.PAUSE;
    Square head;
    private ArrayList<Square> squares; //rename to snakeSquares? Otherwise it has the same name as the 2D array in Arena.
    private int xMovement = 0;
    private int yMovement = 0;
    private int SIZE = Arena.SIZE;
    private boolean isDead = false;

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
        grow();
        grow();
        grow();
        grow();
        grow();
        grow();
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

    /**
     * Create a square with new coordinates and assign it to head.
     * Check if square already exsits(isDead).
     * @param square
     */
    public void slide(Square square)
    {
        square = outside(square);
        squares.add(square);
        head = square;
        isDead = squares.contains(square);
    }

    /**
     * Called on frame update.
     */
    public void move()
    {
        /**
         * Set the current movement direction axis.
         */
        switch (currentDirection)
        {
            case UP:
                xMovement = 0;
                yMovement = -1;
                isDead = false;
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

        /**
         *
         */
        slide(head.move(xMovement, yMovement));
        squares.remove(0);
    }

    public Square outside(Square square)
    {
        int x = square.getX();
        int y = square.getY();
        if (x >= SIZE) x = 0;
        if (y >= SIZE) y = 0;
        if (x < 0) x = SIZE - 1;
        if (y < 0) y = SIZE - 1;
        return new Square(x, y);
    }

    private void collision()
    {
        for (int i = 0; i < squares.size() - 1; i++)
        {
            if (squares.get(squares.size() - 1).getX() == squares.get(i).getX() && squares.get(squares.size() - 1).getY() == squares.get(i).getY())
            {
                System.out.println("yes");
            }
        }
    }

    public Directions getCurrentDirection()
    {
        return currentDirection;
    }

    public boolean isSquareInSnake(Square testSquare){
        boolean testResult = false;
        for (Square square : squares) {
            if(square.equals(testSquare)){
                testResult = true;
            }
        };
        return testResult;
    }


    public void setCurrentDirection(Directions direction)
    {
        this.currentDirection = direction;
    }

    public Boolean getIsDead()
    {
        return isDead;
    }
}
