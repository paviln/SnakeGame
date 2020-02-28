import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;
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

            //locate the food according to the positions of squares
            Random random = new Random();
            List<Integer> foodPositions = new ArrayList<>();
            for (int i = 0; i <= 350; i++) {
                if (i % 25 == 0) {
                    foodPositions.add(i);
                }
            }
            apple_x = foodPositions.get(random.nextInt(foodPositions.size()));
            apple_y = foodPositions.get(random.nextInt(foodPositions.size()));

            viewApple.setScaleX(0.2);           //TODO: fit to size of square
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
