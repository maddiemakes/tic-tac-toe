import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class TicTacToeController implements Initializable {

    @FXML
    private MenuItem aboutItem;

    @FXML
    private VBox vbox;

    @FXML
    private MenuItem closeItem;

    @FXML
    private MenuBar mbar;

    @FXML
    private Menu helpMenu;

    @FXML
    private GridPane gridPane;

    @FXML
    private Menu fileMenu;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        gridPane.setGridLinesVisible(true);
    }
}