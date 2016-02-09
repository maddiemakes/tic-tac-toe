import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import javafx.event.ActionEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

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

    Text move = new Text("O");
    Integer colIndex;
    Integer rowIndex;
    boolean player = true;
    boolean added = false;

//  Contains code repeated by several mouse event functions
    private void mouseEvent(MouseEvent e) {
        Node source = (Node)e.getSource();
        colIndex = GridPane.getColumnIndex(source);
        rowIndex = GridPane.getRowIndex(source);
        if(colIndex == null)
            colIndex = 0;
        if(rowIndex == null)
            rowIndex = 0;
        move.setFont(Font.font("Arial", FontWeight.BOLD, 80));
        GridPane.setHalignment(move, HPos.CENTER);
    }

    @FXML
    private void handleMouseEntered(MouseEvent e) {
        mouseEvent(e);
        System.out.printf("Mouse entered cell [%d, %d]%n", colIndex, rowIndex);
//    Prevents multiple additions
        if(!added) {
//    Adds the circle to the box
            gridPane.add(move, colIndex, rowIndex);
//    Prevents mouse over bug
            move.toBack();
            added = true;
        }
    }

    @FXML
    private void handleMouseExited(MouseEvent e) {
        mouseEvent(e);
        System.out.printf("Mouse exited cell [%d, %d]%n", colIndex, rowIndex);
//      Removes the circle from the box
        gridPane.getChildren().remove(move);
        added = false;
    }

    @FXML
    private void handleMouseClick(MouseEvent e) {
        mouseEvent(e);
        System.out.printf("Clicked [%d, %d]%n", colIndex, rowIndex);
        player = !player;
        if(player)
            move = new Text("O");
        else
            move = new Text("X");
    }

    @FXML
    private void handleClose(ActionEvent event) {
        System.exit(0);
        Platform.exit();
    }

}