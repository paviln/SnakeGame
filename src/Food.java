import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

/**
 * @author Line
 * @since 1.0.0
 */
public class Food{

    public int apple_x;
    public int apple_y;
    //ImageView;

        //randomly locate an apple on the board
        public void locateApple() {
            ImageView viewApple = new ImageView("apple.PNG");
            Random random = new Random();
            apple_x = random.nextInt(400);
            apple_y = random.nextInt(400);
            viewApple.setScaleX(0.2);           //TO DO: fit to size of square
            viewApple.setScaleY(0.2);
            viewApple.setLayoutX(apple_x);
            viewApple.setLayoutY(apple_y);
            //fg.getChildren().add(viewApple);
        }

        private void checkApple(){
            //If the apple collides with the head of the snake, we increase the number of joints of the snake and locate a new apple
            locateApple();
        }
}
