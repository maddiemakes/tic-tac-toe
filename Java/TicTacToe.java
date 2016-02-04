import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
public class TicTacToe extends Application
{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primary) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("TicTacToe.fxml"));
        Scene scene = new Scene(root);
        primary.setScene(scene);
        primary.setTitle("Tic-Tac-Toe");
        primary.show();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tic-Tac-Toe");
        alert.setHeaderText(null);
        alert.setContentText("This is Tic-Tac-Toe. Click on a square to start!");
        alert.showAndWait();
    }
}
