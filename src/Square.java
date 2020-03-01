/**
 * @author Jens
 * @since 1.0.0
 */
public class Square
{
    private int x, y;

    public Square(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public Square move(int x, int y)
    {
        return new Square(getX() + x, getY() + y);
    }

    public boolean equals(Square o) {
        return (this.getX()==o.getX() && this.getY()==o.getY());
    }
}
