import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import javafx.event.ActionEvent;
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

    int rowIndex = 0;
    int colIndex = 0;
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        gridPane.setGridLinesVisible(true);
    }
    @FXML
    private void mouseEntered(MouseEvent e) {
        Node source = (Node)e.getSource() ;
        Integer colIndex = GridPane.getColumnIndex(source);
        Integer rowIndex = GridPane.getRowIndex(source);
        if(colIndex == null)
            colIndex = 0;
        if(rowIndex == null)
            rowIndex = 0;
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
        System.out.printf("Mouse entered cell [%d, %d]%n", colIndex, rowIndex);
    }

    @FXML
    private void handleClose(ActionEvent event) {
        System.exit(0);
        Platform.exit();
    }

}