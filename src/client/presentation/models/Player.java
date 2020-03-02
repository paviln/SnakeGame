package client.presentation.models;

/**
 * @author Jette
 * @since 1.0.0
 */
public class Player
{
    private String name;
    private int score = 0;
    private Snake snake;

    public Player(String name, Square pos)
    {
        this.name = name;
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
        snake.setCurrentDirection(Directions.UP);
    }

    public void moveDown()
    {
        snake.setCurrentDirection(Directions.DOWN);
    }

    public void moveRight()
    {
        snake.setCurrentDirection(Directions.RIGHT);
    }

    public void moveLeft()
    {
        snake.setCurrentDirection(Directions.LEFT);
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }
}
