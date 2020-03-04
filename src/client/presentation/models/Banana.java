package client.presentation.models;

import javafx.scene.image.Image;

/**
 * @author Line
 */

public class Banana extends Food
{
    public Banana(Square initPos)
    {
        super(initPos);
        foodImage = new Image("client/presentation/images/banana.PNG");
    }

    //it is possible to make score depend on type of food
    public void handlePlayer(Player p)
    {
        p.setScore(p.getScore() + 3);
    }

    //to handle velocity or size of snake; depending on type of food
    public void handleSnake()
    {

    }
}
