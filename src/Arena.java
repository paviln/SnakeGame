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
    private Square[][] squares;
    private Player player;
    private Timeline gameLoop = new Timeline();
    private Canvas bg = new Canvas(SIZE * 15, SIZE * 15);
    private Canvas fg = new Canvas(SIZE * 15, SIZE * 15);

    public Arena(int width, int height, Player player)
    {
        squares = new Square[width / SIZE][height / SIZE];
        this.getChildren().addAll(bg, fg);
        this.player = player;
        movement();
        setupGameLoop();
        update();
        generateLevel();
        drawBoard();

    }

    private void generateLevel()
    {
        for (int y = 0; y < squares.length; y++)
        {
            for (int x = 0; x < squares[y].length; x++)
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
                gc.setFill(Color.valueOf("#424242"));
                gc.fillRect(square.getX(), square.getY(), SIZE, SIZE);
                gc.setFill(Color.valueOf("#808080"));
                gc.fillRect(square.getX() + 2, square.getY() + 2, SIZE - 2, SIZE - 2);
            }
        }
    }

    private void setupGameLoop()
    {
        // Refresh 5 times pr. second
        Duration frameRate = Duration.seconds(0.2);

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

        for (Square square : player.getSnake().getSquares())
        {
            gc.clearRect(square.getX(), square.getY(), SIZE, SIZE);
        }

        player.getSnake().move();

        gc.setFill(Color.valueOf("36648B"));

        for (Square square : player.getSnake().getSquares())
        {
            gc.fillRect(square.getX(), square.getY(), SIZE, SIZE);
        }
    }

    public void play()
    {
        gameLoop.play();
    }

    public void movement()
    {
        setOnKeyPressed(event ->
        {
            if (event.getCode() == KeyCode.UP && player.getSnake().currentDirection != Directions.DOWN)
            {
                player.moveUp();
            }
            if (event.getCode() == KeyCode.DOWN && player.getSnake().currentDirection != Directions.UP)
            {
                player.moveDown();
            }
            if (event.getCode() == KeyCode.LEFT && player.getSnake().currentDirection != Directions.RIGHT)
            {
                player.moveLeft();
            }
            if (event.getCode() == KeyCode.RIGHT && player.getSnake().currentDirection != Directions.LEFT)
            {
                player.moveRight();
            }
        });
    }

    public Square collision(Square square)
    {
        int x = square.getX();
        int y = square.getY();
        if (x >= squares.length) x = 0;
        if (y >= squares.length) y = 0;
        if (x < 0) x = squares.length - 1;
        if (x < 0) y = squares.length - 1;
        return new Square(x, y);
    }
}
