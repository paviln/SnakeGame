public class Square
{
    private boolean light;
    private int x, y;

    public Square(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public Square(boolean light, int x, int y)
    {
        this.light = light;
        this.x = x;
        this.y = y;
    }

    public boolean isLight()
    {
        return light;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }
}
