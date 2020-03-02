package client;

import client.presentation.controllers.MainController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @version 1.0.0
 * @since 1.0.0
 */
public class App extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        new MainController(primaryStage);
    }
}