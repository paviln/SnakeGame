import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.Random;

/**
 * @author Jens
 * @since 1.0.0
 */
public class Arena extends VBox
{
    static final int SIZE = 20;
    private final int SQUARESIZE = 25;
    private Square[][] squares;
    private Player player;
    private Timeline gameLoop = new Timeline();
    private Canvas bg = new Canvas(SQUARESIZE * SIZE, SQUARESIZE * SIZE);
    private Canvas fg = new Canvas(SQUARESIZE * SIZE, SQUARESIZE * SIZE);
    private Food foodInArena;
    private int foodCounter = 0;

    public Arena(Player player)
    {
        HBox topBar = new HBox();
        Label score = new Label("Score: ");
        topBar.getChildren().add(score);
        HBox bottomBar = new HBox();
        Label level = new Label("Level: ");
        bottomBar.getChildren().add(level);
        StackPane field = new StackPane();
        squares = new Square[SIZE][SIZE];
        field.getChildren().addAll(bg, fg);
        this.getChildren().addAll(topBar, field, bottomBar);
        this.player = player;
        movement();
        setupGameLoop();
        generateLevel();
        update();
        drawBoard();
    }

    private void generateLevel()
    {
        for (int y = 0; y < SIZE; y++)
        {
            for (int x = 0; x < SIZE; x++)
            {
                squares[x][y] = new Square(x, y);
            }
        }
    }

    private void drawBoard()
    {
        GraphicsContext gc = bg.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,500,500);
        for (Square[] squares : squares)
        {
            for (Square square : squares)
            {
                gc.setFill(Color.valueOf("#808080"));
                gc.fillRect(square.getX()*SQUARESIZE, square.getY()*SQUARESIZE, SQUARESIZE-1, SQUARESIZE-1);
            }
        }
    }

    private void setupGameLoop()
    {
        // Refresh 5 times pr. second
        Duration frameRate = Duration.millis(5000/8);

        // Update every frame
        KeyFrame frame = new KeyFrame(frameRate, "Game loop", event ->
        {
            update();
        });

        // Set game loop properties
        gameLoop.setCycleCount(Animation.INDEFINITE);
        gameLoop.getKeyFrames().add(frame);
    }

    public void play()
    {
        gameLoop.play();
    }

    private void update()
    {
        GraphicsContext gc = fg.getGraphicsContext2D();

        for (Square square : player.getSnake().getSquares())
        {
            gc.clearRect(square.getX()*SQUARESIZE, square.getY()*SQUARESIZE, SQUARESIZE, SQUARESIZE);
        }

        player.getSnake().move();

        gc.setFill(Color.valueOf("36648B"));

        for (Square square : player.getSnake().getSquares())
        {
            gc.fillRect(square.getX()*SQUARESIZE, square.getY()*SQUARESIZE, SQUARESIZE, SQUARESIZE);
        }

        //if there is no food in Arena, then insert food
        if(foodInArena == null ){
            insertNewFood();
        }

        //check if snake head is on the food square
        if(foodInArena.pos.equals(player.getSnake().head)){
            foodInArena.handlePlayer(player);
            insertNewFood();
            foodCounter = foodCounter+1;
            System.out.println("foodCounter: " + foodCounter);
        }
    }

    //insert new food in a random square of the Arena
    public void insertNewFood(){
        int newXsquare = new Random().nextInt(SIZE-1);
        int newYsquare = new Random().nextInt(SIZE-1);

        //check if there is a snake square before inserting food
        while (player.getSnake().isSquareInSnake(squares[newXsquare][newYsquare])) {
            newXsquare = new Random().nextInt(SIZE-1);
            newYsquare = new Random().nextInt(SIZE-1);
        };

        //draw food Image on fg canvas
        foodInArena = new Food(squares[newXsquare][newYsquare]);
        fg.getGraphicsContext2D().drawImage(foodInArena.foodImage,foodInArena.pos.getX()*SQUARESIZE,foodInArena.pos.getY()*SQUARESIZE,SQUARESIZE,SQUARESIZE);
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
}
