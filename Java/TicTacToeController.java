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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class TicTacToeController implements Initializable {

//  State variables
    Text move = new Text("O");
    Integer colIndex;
    Integer rowIndex;
    boolean player = true;
    boolean added = false;
    int board[][] = new int[3][3];
    Tree aiMap;

//  Private declarations @FXML
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
    public void initialize(URL location, ResourceBundle resources) {
        newGame();
    }

    public void newGame() {
        for(int i = 0; i < 3; i++) {
            for(int k = 0; k < 3; k++) {
                board[k][i] = 0;
//                gridPane.getChildren().remove(circle);
//                gridPane.getChildren().remove(cross);
            }
        }
        gridPane.setGridLinesVisible(true);
        player = true;
        added = false;
        move = new Text("O");
        System.out.printf("New game...%n");
    }
    public void buildTree(Tree aiMap)
    {
        //TODO
    }
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
        move.setFill(Color.GRAY);
        System.out.printf("Mouse entered cell [%d, %d]%n", colIndex, rowIndex);
        if(board[colIndex][rowIndex] != 0) {
            move.setFill(Color.WHITE);
            Node source = (Node)e.getSource();
            source.setStyle("-fx-background-color:#fd3f3f; -fx-opacity:.4");
        }
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
        if(board[colIndex][rowIndex] != 0) {
            Node source = (Node)e.getSource();
            source.setStyle("-fx-background-color:#fd3f3f; -fx-opacity:0");
        }
//      Removes the circle from the box
        gridPane.getChildren().remove(move);
        added = false;
    }

    @FXML
    private void handleMouseClick(MouseEvent e) {
        mouseEvent(e);
        if(board[colIndex][rowIndex] == 0) {
            move.setFill(Color.BLACK);
            System.out.printf("Clicked [%d, %d]%n", colIndex, rowIndex);
            if (player)
                board[colIndex][rowIndex] = 1;
            else
                board[colIndex][rowIndex] = 2;
            player = !player;
            if (player)
                move = new Text("O");
            else
                move = new Text("X");
            aiMap = new Tree(new TreeNode(board));
            System.out.println(aiMap);
            System.out.println(board);
        }
    }

    @FXML
    private void handleClose(ActionEvent event) {
        System.exit(0);
        Platform.exit();
    }

}
