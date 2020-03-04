package client.presentation.controllers;

import client.presentation.views.Menu;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController
{
    private static Stage primaryStage;
    private final int WIDTH = 500;
    private final int HEIGHT = 500;

    public MainController(Stage ps)
    {
        Menu menu = new Menu();
        primaryStage = ps;
        Scene scene = new Scene(menu, WIDTH, HEIGHT);
        scene.getStylesheets().add("Styles.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("SnakeGame");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public static void changeScene(Scene scene)
    {
        primaryStage.setScene(scene);
    }
}
