public class Square
{
    private boolean light;
    private int x, y;

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
}
