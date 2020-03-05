package client.presentation.models;

import client.presentation.views.Arena;

import java.util.ArrayList;

/**
 * @author Henrik
 * @since 1.0.0
 */
public class Snake
{
    private Directions currentDirection = Directions.PAUSE; // change left to pause
    private final int SIZE = Arena.getSize();
    private Square head;
    private ArrayList<Square> squares = new ArrayList<>();
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
    private void slide(Square square)
    {
        square = collision(square);
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
         * Move head in direction and remove first element
         */
        if (xMovement != 0 || yMovement != 0)
        {
            slide(head.move(xMovement, yMovement));
            squares.remove(0);
        }
    }

    /**
     * Detect if the head collides with border or itself.
     *
     * @param square
     * @return
     */
    private Square collision(Square square)
    {
        int x = square.getX();
        int y = square.getY();

        if (x >= SIZE || x < 0 || y >= SIZE || y < 0)
        {
            isDead = true;
        }

        if (isSquareInSnake(square))
        {
            isDead = true;
        }
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

    public void setIsDead(Boolean status)
    {
        this.isDead = status;
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
