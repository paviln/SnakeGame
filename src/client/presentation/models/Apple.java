package client.presentation.models;

import client.presentation.views.Arena;
import javafx.scene.image.Image;

public class Apple extends Food
{
    public Apple(Square initPos)
    {
        super(initPos);
        super.setFoodImage(new Image("client/presentation/images/apple.PNG"));
    }

    //it is possible to make score depend on type of food
    public void handlePlayer(Player p)
    {
        p.setScore(p.getScore() + 1);
    }

    //to handle velocity or size of snake; depending on type of food
    public void handleSnake()
    {

    }
}
