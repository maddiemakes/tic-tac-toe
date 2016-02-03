import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class TicTacToe extends Application
{
    public static void main(String[] args) {
        launch(args);
    }

    //@Override
    public void start(Stage primary) throws Exception
    {
        primary.setTitle("Tic-Tac-Toe");
        VBox vbox = new VBox();
        GridPane board = new GridPane();
        vbox.getChildren().add(board);
        Scene scene = new Scene(vbox, 800, 800);
        primary.setScene(scene);
        primary.show();
    }
}
