import javafx.scene.image.Image;


/**
 * @author Line
 */

public class Banana extends Food {


    public Banana(Square initPos){
        super(initPos);
        foodImage = new Image("banana.PNG");
    }

    //it is possible to make score depend on type of food
    public void handlePlayer(Player p){
        p.setScore(p.getScore() + 1);
    }

    //to handle velocity or size of snake; depending on type of food
    public void handleSnake(){

    }
}
