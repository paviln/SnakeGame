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

/**
 * @author Jette
 * @since 1.0.0
 */
public class Menu extends Pane {

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
        Player player1 = new Player(tfPlayerName.getText(), 0, new Square(100,100));

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
                Arena arena = new Arena(500, 500, player1);
                App.getPs().setScene(new Scene(arena));
                arena.play();
                arena.requestFocus();
                //only if a player name was entered, it should be possible to press play
                }
            }
        });

        this.getChildren().addAll(tfPlayerName, btnPlay, label1, label2);

        Bounds bounds = this.getBoundsInLocal();
        System.out.println(bounds);

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