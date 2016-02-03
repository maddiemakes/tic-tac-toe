import javafx.stage.Stage;
import javafx.application.Application;
import java.io.IOException;
import javafx.scene.Scene;
//import javafx.scene.layout.Vbox;
import javafx.scene.layout.GridPane;
public class TicTacToe extends Application
{
    @Override
    public void start(Stage primary)
    {
        primary.setTitle("Tic-Tac-Toe");
        primary.show();
    }
    public static void main(String [] args)
    {
        launch(args);
    }
}
