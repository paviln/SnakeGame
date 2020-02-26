import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @author Jette
 * @since 1.0.0
 */
public class Menu extends Pane {

    public Menu()
    {

        // design TODO
        //this.setStyle(insert stylesheet?);
        this.setStyle("-fx-background-color: BLACK; -fx-color: ORANGE");

        // Lable welcome
        Label label = new Label("HIT THAT WORM");
        label.setLayoutY(0);
        label.setLayoutX(0);
        //label.setStyle("");


        //TextField PlayerName
        TextField tfPlayerName = new TextField();
        tfPlayerName.setLayoutX(100);
        tfPlayerName.setLayoutY(100);
        tfPlayerName.setPromptText("Insert Player Name");
        tfPlayerName.setFocusTraversable(false);
        //tfPlayerName.setStyle("-fx-border-color: LIGHTGREEN; -fx-fill: LIGHTGREY");
        //Create new player
        Player player1 = new Player(tfPlayerName.getText(), 0);

        //Button play
        Button btnPlay = new Button();
        btnPlay.setLayoutX(100);
        btnPlay.setLayoutY(200);
        btnPlay.setText("PLAY");
        btnPlay.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event){

                //Change to arena scene
                Arena arena = new Arena(player1);
                App.getPs().setScene(new Scene(arena));
                arena.play();
            }
        });
        this.getChildren().addAll(tfPlayerName, btnPlay, label);
    }
}