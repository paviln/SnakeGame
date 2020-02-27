import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @version 1.0.0
 * @since 1.0.0
 */
public class App extends Application
{
    static Stage ps;
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        this.ps = primaryStage;
        Menu menu = new Menu();
        primaryStage.setScene(new Scene(menu, 500, 500));
        primaryStage.setTitle("SnakeGame");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static Stage getPs() {
        return ps;
    }
}
