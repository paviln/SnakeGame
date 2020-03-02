import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.Random;

/**
 * @author Jens
 * @since 1.0.0
 */
public class Arena extends BorderPane
{
    static final int SIZE = 20;
    private final int SQUARESIZE = 25;
    private Player player;
    private Timeline gameLoop = new Timeline();
    private Canvas bg, fg;
    private Food foodInArena;
    private int foodCounter = 0;

    /**
     * Constructor
     *
     * @param player takes a player
     */
    public Arena(Player player)
    {
        this.player = player;
        GUI();
        drawField();
        movement();
        setupGameLoop();
    }

    /**
     *
     */
    private void GUI()
    {
        HBox topBar = new HBox();
        Label score = new Label("Score: ");
        topBar.getChildren().add(score);
        setTop(topBar);

        StackPane field = new StackPane();
        bg = new Canvas(SQUARESIZE * SIZE, SQUARESIZE * SIZE);
        fg = new Canvas(SQUARESIZE * SIZE, SQUARESIZE * SIZE);
        field.getChildren().addAll(bg, fg);
        setCenter(field);

        HBox bottomBar = new HBox();
        Label level = new Label("Level: ");
        bottomBar.getChildren().add(level);
        setBottom(bottomBar);
    }

    /**
     * Draw the game field
     */
    private void drawField()
    {
        GraphicsContext gc = bg.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 500, 500);
        for (int y = 0; y < SIZE; y++)
        {
            for (int x = 0; x < SIZE; x++)
            {
                gc.setFill(Color.valueOf("#808080"));
                gc.fillRect(x * SQUARESIZE, y * SQUARESIZE, SQUARESIZE - 1, SQUARESIZE - 1);
            }
        }
    }

    /**
     * Construct the game loop, with a refresh rate of 5 times pr. second.
     */
    private void setupGameLoop()
    {
        Duration frameRate = Duration.millis(1000 / 8);
        KeyFrame frame = new KeyFrame(frameRate, "Game loop", event ->
        {
            update();
        });
        gameLoop.setCycleCount(Animation.INDEFINITE);
        gameLoop.getKeyFrames().add(frame);
    }

    /**
     * Start the game loop.
     */
    public void play()
    {
        gameLoop.play();
    }

    /**
     * Stop the game loop.
     */
    public void stop()
    {
        gameLoop.stop();
    }

    /**
     * Called every frame, to update changes.
     */
    private void update()
    {
        GraphicsContext gc = fg.getGraphicsContext2D();

        for (Square square : player.getSnake().getSquares())
        {
            gc.clearRect(square.getX() * SQUARESIZE, square.getY() * SQUARESIZE, SQUARESIZE, SQUARESIZE);
        }

        player.getSnake().move();

        if (player.getSnake().getIsDead())
        {
            System.out.println("yes");
        }

        gc.setFill(Color.valueOf("36648B"));

        for (Square square : player.getSnake().getSquares())
        {
            gc.fillRect(square.getX() * SQUARESIZE, square.getY() * SQUARESIZE, SQUARESIZE, SQUARESIZE);
        }

        //if there is no food in Arena, then insert food
        if (foodInArena == null)
        {
            insertNewFood();
        }

        //check if snake head is on the food square
        if (foodInArena.pos.equals(player.getSnake().head))
        {
            foodInArena.handlePlayer(player);
            insertNewFood();
            foodCounter = foodCounter + 1;
        }
    }

    //insert new food in a random square of the Arena
    public void insertNewFood()
    {
        int newXsquare = new Random().nextInt(SIZE - 1);
        int newYsquare = new Random().nextInt(SIZE - 1);

        //check if there is a snake square before inserting food
        while (player.getSnake().isSquareInSnake(new Square(newXsquare, newYsquare)))
        {
            newXsquare = new Random().nextInt(SIZE - 1);
            newYsquare = new Random().nextInt(SIZE - 1);
        }
        ;

        //draw food Image on fg canvas
        foodInArena = new Food(new Square(newXsquare, newYsquare));
        fg.getGraphicsContext2D().drawImage(foodInArena.foodImage, foodInArena.pos.getX() * SQUARESIZE, foodInArena.pos.getY() * SQUARESIZE, SQUARESIZE, SQUARESIZE);
    }

    /**
     * Listen for direction key pressed.
     * Only alow non opposite direction, and move player.
     */
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
}
