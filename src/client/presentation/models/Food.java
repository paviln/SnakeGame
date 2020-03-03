package client.presentation.models;

import javafx.scene.image.Image;


/**
 * @author Line
 * @since 1.0.0
 */
public class Food
{

    Square pos;
    Image foodImage;


    public Food(Square initPos)
    {
        pos = initPos;
        foodImage = new Image("client/presentation/images/apple.PNG");
    }

    //handle score
    public void handlePlayer(Player player)
    {
        player.setScore(player.getScore() + 1);
    }

    //to handle velocity or size of client.presentation.models.Snake; depending on type of food
    public void handleSnake()
    {

    }

    public Image getFoodImage()
    {
        return foodImage;
    }

    public void setFoodImage(Image foodImage)
    {
        this.foodImage = foodImage;
    }

    public Square getPos()
    {
        return pos;
    }

    public void setPos(Square pos)
    {
        this.pos = pos;
    }
}
