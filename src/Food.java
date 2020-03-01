import javafx.scene.image.Image;


/**
 * @author Line
 * @since 1.0.0
 */
public class Food{

    Square pos;
    Image foodImage;


    public Food(Square initPos){
        pos = initPos;
        foodImage = new Image("apple.PNG");
    }

    //handle score
    public void handlePlayer(Player p){
        p.score = p.score +1;
    }

    //to handle velocity or size of Snake; depending on type of food
    public void handleSnake(){

    }

}
