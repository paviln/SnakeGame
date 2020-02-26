import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * @author Jens
 * @since 1.0.0
 */
public class Arena extends StackPane
{
    static final int SIZE = 25;
    Directions currentDirection = Directions.PAUSE;
    int x = 25;
    int y = 25;
    private Player player;
    private Snake snake = new Snake(100, 100);
    private Timeline gameLoop = new Timeline();
    private Square[][] squares = new Square[15][15];
    private Canvas bg = new Canvas(SIZE * 15, SIZE * 15);
    private Canvas fg = new Canvas(SIZE * 15, SIZE * 15);

    public Arena(Player player)
    {
        this.requestFocus();
        this.getChildren().addAll(bg, fg);
        this.player = player;
        setupGameLoop();
        update();
        generateLevel();
        drawBoard();
        movement();
        snake.grow(75, 100);
    }

    private void generateLevel()
    {
        for (int y = 0; y < 15; y++)
        {
            for (int x = 0; x < 15; x++)
            {
                squares[x][y] = new Square((x + y) % 2 == 0, x * 25, y * 25);
            }
        }
    }

    private void drawBoard()
    {
        GraphicsContext gc = bg.getGraphicsContext2D();
        for (Square[] squares : squares)
        {
            for (Square square : squares)
            {
                if (square.isLight())
                {
                    gc.setFill(Color.valueOf("#fff"));
                } else
                {
                    gc.setFill(Color.valueOf("#3258a8"));
                }

                gc.fillRect(square.getX(), square.getY(), SIZE, SIZE);
            }
        }
    }

    private void setupGameLoop()
    {
        // Refresh 60 times pr. second
        Duration frameRate = Duration.millis(1000.0 / 1.0);

        // Update every frame
        KeyFrame frame = new KeyFrame(frameRate, "Game loop", event ->
        {
            update();
        });

        // Set game loop properties
        gameLoop.setCycleCount(Animation.INDEFINITE);
        gameLoop.getKeyFrames().add(frame);
    }

    private void update()
    {
        GraphicsContext gc = fg.getGraphicsContext2D();

        for (Square point : snake.getPoints())
        {
            gc.clearRect(point.getX(), point.getY(), 25, 25);
        }

        switch (currentDirection)
        {
            case UP:
                snake.moveUp();
                break;
            case DOWN:
                y += 25;
                break;
            case LEFT:
                x -= 25;
                break;
            case RIGHT:
                x += 1;
                break;
            case PAUSE:
                break;
        }
        gc.setFill(Color.BLACK);
        for (Square point : snake.getPoints())
        {
            gc.fillRect(point.getX(), point.getY(), 25, 25);
        }

    }

    public void play()
    {
        gameLoop.play();
    }

    public void movement()
    {
        this.setOnKeyPressed(event ->
        {
            if (event.getCode() == KeyCode.UP && currentDirection != Directions.DOWN)
            {
                currentDirection = Directions.UP;
            }
            if (event.getCode() == KeyCode.DOWN && currentDirection != Directions.UP)
            {
                currentDirection = Directions.DOWN;
            }
            if (event.getCode() == KeyCode.LEFT && currentDirection != Directions.RIGHT)
            {
                currentDirection = Directions.LEFT;
            }
            if (event.getCode() == KeyCode.RIGHT && currentDirection != Directions.LEFT)
            {
                currentDirection = Directions.RIGHT;
            }
        });
    }
}
