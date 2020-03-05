package client.presentation.models;

import javafx.scene.image.Image;

public class Apple extends Food
{
    public Apple(Square initPos)
    {
        super(initPos);
        foodImage = new Image("client/presentation/images/apple.PNG");
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