/**
 * @author Henrik
 * @since 1.0.0
 */
public class Snake
{

    private int xMovement;
    private int yMovement;



    //Movement

    private void moveUp()
    {
        if (yMovement == 1) return;
        xMovement = 0;
        yMovement = 1;
    }

    private void moveDown()
    {
        if (yMovement == -1) return;
        xMovement = 0;
        yMovement = -1;
    }

    private void moveRight()
    {
        if (xMovement == 1) return;
        xMovement = 1;
        yMovement = 0;
    }

    private void moveLeft()
    {
        if (xMovement == -1) return;
        xMovement = -1;
        yMovement = 0;
    }


    //Grow
}
