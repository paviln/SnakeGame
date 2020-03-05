package client.presentation.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * @author Jette
 * @since 1.0.0
 */
public class Player
{
    private String name;
    private IntegerProperty score = new SimpleIntegerProperty(0);
    private Snake snake;

    public Player(String name, Square pos)
    {
        this.name = name;
        this.snake = new Snake(pos);
    }

    public String getName()
    {
        return name;
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
        return score.get();
    }

    public void setScore(int score)
    {
        this.score.set(score);
    }
    public IntegerProperty scoreProperty()
    {
        return score;
    }
}
