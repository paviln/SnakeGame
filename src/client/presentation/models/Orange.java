package client.presentation.models;

import javafx.scene.image.Image;

/**
 * @author Line
 */

public class Orange extends Food
{
    public Orange(Square initPos)
    {
        super(initPos);
        setId(2);
        super.setFoodImage(new Image("client/presentation/images/orange.PNG"));
    }

    //it is possible to make score depend on type of food
    public void handlePlayer(Player p)
    {
        p.setScore(p.getScore() + 2);
    }

    //to handle velocity or size of snake; depending on type of food
    public void handleSnake()
    {

    }
}
