package client.presentation.views;

import client.App;
import client.presentation.controllers.MainController;
import client.presentation.models.Player;
import client.presentation.models.Square;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.animation.AnimationTimer;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

/**
 * @author Jette
 * @since 1.0.0
 */
public class Menu extends Pane {
    MediaPlayer mediaPlayer;

    public Menu()
    {
        this.getStyleClass().addAll("pane", "label", "textField", "button");

        // Label 1
        Label label1 = new Label("Feed the worm, yum yum!");
        label1.relocate(0, 10);

        //Label 2
        Label label2 = new Label("Feed the worm, yum yum!");
        label2.relocate(200, 0);

        //TextField PlayerName
        TextField tfPlayerName = new TextField();
        tfPlayerName.setAlignment(Pos.CENTER); // centers the text inside the textfield
        tfPlayerName.setLayoutX(200);
        tfPlayerName.setLayoutY(200);
        tfPlayerName.setPromptText("Insert Player Name");
        tfPlayerName.setFocusTraversable(false);

        //Create new player
        Player player1 = new Player(tfPlayerName.getText(), new Square(5,5));

        //Button play
        Button btnPlay = new Button();
        btnPlay.setLayoutX(200);
        btnPlay.setLayoutY(250);
        btnPlay.setText("PLAY");
        btnPlay.setFocusTraversable(false);

        btnPlay.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event){
                //only if a player name was entered, it should be possible to press play
                if (!tfPlayerName.getText().isEmpty()) {
                //Change to arena scene
                Arena arena = new Arena(player1);
                MainController.changeScene(new Scene(arena));

                arena.play();
                }
            }
        });

        //background music
        String path = new File("src/LippsIncFunkyTown.mp3").getAbsolutePath();
        Media musicFile = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(musicFile);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(0.1);

        this.getChildren().addAll(tfPlayerName, btnPlay, label1, label2);

        Bounds bounds = this.getBoundsInLocal();

        new AnimationTimer() {

            @Override
            public void handle(long now) {
                label1.setTranslateX(label1.getTranslateX() +2);
                label1.setTranslateY(label1.getTranslateY() +2);
                label2.setTranslateX(label2.getTranslateX() +1);
                label2.setTranslateY(label2.getTranslateY() +1);

                if (label1.getTranslateX() > (bounds.getMaxX()) && label1.getTranslateY() > (bounds.getMaxY())) {
                    label1.setTranslateX(0);
                    label1.setTranslateY(0);
                }

                if (label2.getTranslateX() > (bounds.getMaxX()) && label2.getTranslateY() > (bounds.getMaxY())) {
                    label2.setTranslateX(0);
                    label2.setTranslateY(0);
                }
            }
        }.start();
    }
}