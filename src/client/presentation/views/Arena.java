package client.presentation.views;

import client.presentation.controllers.MainController;
import client.presentation.models.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author Jens
 * @since 1.0.0
 */
public class Arena extends BorderPane
{
    static final int SIZE = 20;
    private final int SQUARESIZE = 25;
    Timeline respawnLoop = new Timeline();
    private static Timer timer;
    long delay = 4000L;
    int angle = 0;
    int frames;
    private Player player;
    private Timeline gameLoop = new Timeline();
    private Canvas bg, fg;
    private Food foodInArena;
    private boolean insaneMode = false;

    /**
     * Constructor
     *
     * @param player takes a player
     */
    public Arena(Player player)
    {
        this.player = player;

        setupGameLoop();
        respawn();
        arenaGUI();
    }

    public static int getSize()
    {
        return SIZE;
    }

    private void arenaGUI()
    {
        GUI();
        drawField();
        movement();
    }

    /**
     *
     */
    private void GUI()
    {
        AnchorPane topBar = new AnchorPane();
        topBar.getStyleClass().addAll("pane", "label", "textField", "button");
        topBar.getStylesheets().add("Styles.css");
        HBox score = new HBox();
        Label scoreText = new Label("Score: ");
        Label scorePoints = new Label();
        scorePoints.textProperty().bind(player.scoreProperty().multiply(100).asString());
        score.getChildren().addAll(scoreText, scorePoints);

        HBox level = new HBox();
        Label levelText = new Label("Level: ");
        Label levelPoints = new Label();
        levelPoints.textProperty().bind(player.scoreProperty().asString());
        level.getChildren().addAll(levelText, levelPoints);
        topBar.getChildren().addAll(score, level);
        AnchorPane.setLeftAnchor(score, 0.0);
        AnchorPane.setTopAnchor(score, 0.0);
        AnchorPane.setRightAnchor(level, 0.0);
        AnchorPane.setTopAnchor(level, 0.0);
        setTop(topBar);

        StackPane field = new StackPane();
        bg = new Canvas(SQUARESIZE * SIZE, SQUARESIZE * SIZE);
        fg = new Canvas(SQUARESIZE * SIZE, SQUARESIZE * SIZE);
        field.getChildren().addAll(bg, fg);
        setCenter(field);
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
     * Construct the game loop.
     */
    private void setupGameLoop()
    {
        Duration frameRate = Duration.seconds(.15);
        KeyFrame frame = new KeyFrame(frameRate, "Game loop", event ->
        {
            update();
        });
        gameLoop.setCycleCount(Animation.INDEFINITE);
        gameLoop.getKeyFrames().add(frame);
    }

    /**
     * Construct the game loop.
     */
    private void changeSpeed(double speed)
    {
        Duration frameRate = Duration.seconds(speed);
        KeyFrame frame = new KeyFrame(frameRate, "Game loop", event ->
        {
            update();
        });
        gameLoop.stop();
        gameLoop.getKeyFrames().setAll(frame);
        gameLoop.play();
    }

    /**
     * Set the refresh speed of game loop
     * @param speed
     */
    private void speed(double speed)
    {
        TimerTask task = new TimerTask()
        {
            @Override
            public void run()
            {
                changeSpeed(speed);
            }
        };
        timer = new Timer();
        timer.schedule(task, 4000);
    }


    /**
     * Start the game loop.
     */
    public void play()
    {
        requestFocus();
        gameLoop.play();
        respawnLoop.play();
    }

    /**
     * Stop the game loop.
     */
    public void stop()
    {
        gameLoop.stop();
        respawnLoop.stop();
    }

    /**
     * Construct the respawn loop
     */
    private void respawn()
    {
        Duration frameRate = Duration.seconds(5);
        KeyFrame frame = new KeyFrame(frameRate, "Game loop", event ->
        {
            GraphicsContext gc = fg.getGraphicsContext2D();
            gc.clearRect(foodInArena.getPos().getX() * SQUARESIZE, foodInArena.getPos().getY() * SQUARESIZE, SQUARESIZE, SQUARESIZE);
            insertNewFood();
        });
        respawnLoop.setCycleCount(Animation.INDEFINITE);
        respawnLoop.getKeyFrames().add(frame);
        respawnLoop.play();
    }

    private int randomTurn()
    {
        int value = 0;
        int rnd = new Random().nextInt(3);

        switch (rnd)
        {
            case 0:
                value = 90;
                break;
            case 1:
                value = 180;
                break;
            case 2:
                value = 270;
                break;
        }
        return value;
    }

    private void update()
    {
        GraphicsContext gc = fg.getGraphicsContext2D();

        gc.clearRect(player.getSnake().getSquares().get(0).getX() * SQUARESIZE, player.getSnake().getSquares().get(0).getY() * SQUARESIZE, SQUARESIZE, SQUARESIZE);

        if (player.getSnake().getIsDead())
        {
            stop();
            gameOver();
            insaneMode = false;
        }

        if (player.getScore() % 5 == 0 && player.getScore() != 0 && insaneMode == false)
        {
            frames = 0;
            insaneMode = true;
            angle = randomTurn();
        }

        if (insaneMode && frames == 0)
        {
            Rotate r = new Rotate(angle, 250, 250);
            gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
            gc.clearRect(0, 0, SIZE * SQUARESIZE, SIZE * SQUARESIZE);
            fg.getGraphicsContext2D().drawImage(foodInArena.getFoodImage(), foodInArena.getPos().getX() * SQUARESIZE, foodInArena.getPos().getY() * SQUARESIZE, SQUARESIZE, SQUARESIZE);
        }
        if (insaneMode && frames == 15)
        {
            Rotate r = new Rotate(0, 250, 250);
            gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
            gc.clearRect(0, 0, SIZE * SQUARESIZE, SIZE * SQUARESIZE);
            fg.getGraphicsContext2D().drawImage(foodInArena.getFoodImage(), foodInArena.getPos().getX() * SQUARESIZE, foodInArena.getPos().getY() * SQUARESIZE, SQUARESIZE, SQUARESIZE);

            frames = 0;
            insaneMode = false;
            player.setScore(player.getScore() + 1);
        }

        player.getSnake().move();

        gc.setFill(Color.valueOf("36648B"));

        for (Square square : player.getSnake().getSquares())
        {
            gc.fillRect(square.getX() * SQUARESIZE, square.getY() * SQUARESIZE, SQUARESIZE, SQUARESIZE);
        }


        //if there is no food in client.presentation.views.Arena, then insert food
        if (foodInArena == null)
        {
            insertNewFood();
        }


        //check if snake head is on the food square
        if (foodInArena.getPos().equals(player.getSnake().getHead()))
        {
            if (foodInArena.getId() == 3)
            {
                changeSpeed(.075);

                speed(0.15);
            }
            foodInArena.handlePlayer(player);
            player.getSnake().grow();
            respawnLoop.stop();
            respawnLoop.play();
            insertNewFood();
        }
        frames++;
    }

    /**
     * insert new food in a random square of the client.presentation.views.Arena
     */
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


        //draw random food Image on fg canvas
        switch (new Random().nextInt(3))
        {
            case 0:
                foodInArena = new Apple(new Square(newXsquare, newYsquare));
                break;
            case 1:
                foodInArena = new Orange(new Square(newXsquare, newYsquare));
                break;
            case 2:
                foodInArena = new Banana(new Square(newXsquare, newYsquare));
                break;
        }
        fg.getGraphicsContext2D().drawImage(foodInArena.getFoodImage(), foodInArena.getPos().getX() * SQUARESIZE, foodInArena.getPos().getY() * SQUARESIZE, SQUARESIZE, SQUARESIZE);
    }

    /**
     * Listen for direction key pressed.
     * Only allow non opposite direction, and move player.
     */
    public void movement()
    {
        setOnKeyPressed(event ->
        {
            if (event.getCode() == KeyCode.UP && player.getSnake().getCurrentDirection() != Directions.DOWN)
            {
                player.moveUp();
            }
            if (event.getCode() == KeyCode.DOWN && player.getSnake().getCurrentDirection() != Directions.UP)
            {
                player.moveDown();
            }
            if (event.getCode() == KeyCode.LEFT && player.getSnake().getCurrentDirection() != Directions.RIGHT)
            {
                player.moveLeft();
            }
            if (event.getCode() == KeyCode.RIGHT && player.getSnake().getCurrentDirection() != Directions.LEFT)
            {
                player.moveRight();
            }
        });
    }

    private void gameOver()
    {
        VBox options = new VBox();
        options.setAlignment(Pos.CENTER);
        Button restartBtn = new Button("Restart");
        Button menuBtn = new Button("Menu");
        Button quitBtn = new Button("Quit");

        options.getChildren().addAll(restartBtn, menuBtn, quitBtn);
        options.getStyleClass().addAll("pane", "label", "textField", "button"); // deleted vbox

        this.getStylesheets().add("Styles.css");
        this.setCenter(options);

        restartBtn.setOnAction(event ->
        {
            player = new Player(player.getName(), new Square(10, 10));
            arenaGUI();
            play();
            insertNewFood();
        });
        menuBtn.setOnAction(event ->
        {
            Menu menu = new Menu();
            Scene scene = new Scene(menu, 500, 500);
            scene.getStylesheets().add("Styles.css");
            MainController.changeScene(scene);
        });
        quitBtn.setOnAction(event ->
        {
            System.exit(0);
        });
    }
}
