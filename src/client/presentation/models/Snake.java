package client.presentation.models;

import client.presentation.views.Arena;
import java.util.ArrayList;

/**
 * @author Henrik
 * @since 1.0.0
 */
public class Snake
{
    Directions currentDirection = Directions.LEFT;
    private int SIZE = Arena.getSize();
    private Square head;
    private ArrayList<Square> squares = new ArrayList<>();;
    private int xMovement = 0;
    private int yMovement = 0;
    private boolean isDead = false;

    public Snake(Square square)
    {
        squares.add(square);
        head = square;
    }

    public void grow()
    {
        slide(head.move(xMovement, yMovement));
    }

    /**
     * Create a square with new coordinates and assign it to head.
     * Check if square already exsits(isDead).
     *
     * @param square
     */
    public void slide(Square square)
    {
        square = outside(square);
        if (squares.contains(square))
        {
            System.out.println("test");
        }
        squares.add(square);
        head = square;
    }

    /**
     * Called on frame update.
     */
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

    public boolean isSquareInSnake(Square testSquare)
    {
        boolean testResult = false;
        for (Square square : squares)
        {
            if (square.equals(testSquare))
            {
                testResult = true;
            }
        }
        ;
        return testResult;
    }

    public Directions getCurrentDirection()
    {
        return currentDirection;
    }

    public void setCurrentDirection(Directions direction)
    {
        this.currentDirection = direction;
    }

    public Boolean getIsDead()
    {
        return isDead;
    }

    public Square getHead()
    {
        return head;
    }

    public ArrayList<Square> getSquares()
    {
        return squares;
    }
}
