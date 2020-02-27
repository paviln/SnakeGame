/**
 * @author Jette
 * @since 1.0.0
 */
public class Player
{
    private String name;
    private int score;
    private Snake snake;

    public Player(String name, int score, Square pos)
    {
        this.name = name;
        this.score = score;
        this.snake = new Snake(pos);
    }

    public void updateScore()
    {

        //get current info from score class TODO
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Snake getSnake()
    {
        return snake;
    }

    public void moveUp()
    {
        snake.setNewDirection(Directions.UP);
    }

    public void moveDown()
    {
        snake.setNewDirection(Directions.DOWN);
    }

    public void moveRight()
    {
        snake.setNewDirection(Directions.RIGHT);
    }

    public void moveLeft()
    {
        snake.setNewDirection(Directions.LEFT);
    }
}
