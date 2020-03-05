package client;

import client.presentation.controllers.MainController;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

/**
 * @version 1.0.0
 * @since 1.0.0
 */
public class App extends Application
{
    private MediaPlayer mediaPlayer;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        new MainController(primaryStage);

        //background music
        String path = new File("src/LippsIncFunkyTown.mp3").getAbsolutePath();
        Media musicFile = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(musicFile);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(0.1);
    }
}