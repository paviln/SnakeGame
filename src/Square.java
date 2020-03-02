/**
 * Represents a square, which used for grids.
 * @author Jens
 * @since 1.0.0
 */
public class Square
{
    // Coordinates
    private int x, y;

    /**
     * Constructor of the Square class, it takes:
     * @param x
     * @param y
     */
    public Square(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Get method
     * @return x
     */
    public int getX()
    {
        return x;
    }

    /**
     * Set method
     * @param x coordinate
     */
    public void setX(int x)
    {
        this.x = x;
    }

    /**
     * Get method
     * @return y coordinate
     */
    public int getY()
    {
        return y;
    }

    /**
     * Set method
     * @param y
     */
    public void setY(int y)
    {
        this.y = y;
    }

    /**
     * Make a duplicate square
     * @param x
     * @param y
     * @return Square with new direction coordinates
     */
    public Square move(int x, int y)
    {
        return new Square(getX() + x, getY() + y);
    }

    public boolean equals(Square o) {
        return (this.getX()==o.getX() && this.getY()==o.getY());
    }
}
