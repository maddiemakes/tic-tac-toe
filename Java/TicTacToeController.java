import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.RadialGradient;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;

public class TicTacToeController implements Initializable {

//  State variables
    Text move = new Text("O");
    Integer colIndex;
    Integer rowIndex;
    boolean player = true;
    boolean added = false;
    int gameWin = 0;
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
        //newGame();
        gridPane.setGridLinesVisible(true);
    }

    public void newGame() {
        for(int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                board[k][i] = 0;
//                gridPane.getChildren().remove(circle);
//                gridPane.getChildren().remove(cross);
            }
        }
        player = true;
        added = false;
        move = new Text("O");
        System.out.printf("New game...%n");
        try {
            TicTacToe.scene.setRoot(FXMLLoader.load(getClass().getResource("TicTacToe.fxml")));
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
    public void buildTree(Tree aiMap)
    {

        for(int i = 0;i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                int[][] tempBoard = new int[3][3];
                for(int k = 0; k < 3; k++)
                {
                    for(int l = 0; l < 3; l++)
                    {
                        tempBoard[k][l] = aiMap.root.board[k][l];
                    }
                }
                if(tempBoard[i][j] == 0)
                {
                    tempBoard[i][j] = 2;
                    aiMap.root.childList.add(new TreeNode(tempBoard, i, j));
                    //System.out.println(tempBoard[i][j]);
                    //System.out.println(board[i][j]);
                }
            }

        }
    }
    //TODO score boards in aiMap.root.childList

    private void gameOver() {
        for(int a = 1; a < 3; a++) {
            if ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == a) || (board[2][0] == board[1][1] && board[2][0] == board[0][2] && board[2][0] == a)) {
                //System.out.println("X Diagonal Win");
                gameWin = a;
            }
            for (int i = 0; i < 3; ++i) {
                if (((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == a || (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == a)))) {
                    // System.out.println("X Row or Column win");
                    gameWin = a;
                }
            }
        }
        if(gameWin!=0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setHeaderText("Look, a Confirmation Dialog with Custom Actions");
//            alert.setContentText("Choose your option.");

            alert.setHeaderText(null);
            if(gameWin == 1) {
                alert.setTitle("Game Win");
                alert.setContentText("A satisfying victory indeed! Play again?");
            }
            else {
                alert.setTitle("Game Lose");
                alert.setContentText("What a grueling defeat! Play again?");
            }
            ButtonType buttonTypeOne = new ButtonType("New Game");
            ButtonType buttonTypeTwo = new ButtonType("Exit");
//            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeOne){
                newGame();
            }
            else {
                System.exit(0);
                Platform.exit();
            }
        }
    }

//  Contains code repeated by several mouse event functions
    public TreeNode scoreTree()
    {
        //TODO score boards in aiMap.root.childList
        Random rand = new Random();
        int k = rand.nextInt(aiMap.root.childList.size());
        while(k<=0)
            k++;
        return aiMap.root.childList.get(k);
    }
    public void aiMove(TreeNode moveOpt)
    {
        if(!player) {
            move = new Text("X");
            move.setFill(Color.BLACK);
            move.setFont(Font.font("Arial", FontWeight.BOLD, 80));
            GridPane.setHalignment(move, HPos.CENTER);
            gridPane.add(move, moveOpt.colVal, moveOpt.rowVal);
            move.toBack();
            player = true;
            board[moveOpt.colVal][moveOpt.rowVal] = 2;
        }
        move = new Text("O");

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
        if(player)
            move = new Text("O");
        mouseEvent(e);
        move.setFill(Color.GRAY);
        System.out.printf("Mouse entered cell [%d, %d]%n", colIndex, rowIndex);
        if(board[colIndex][rowIndex] != 0 || !player) {
            move.setFill(Color.rgb(0,0,0,0));
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
        if(board[colIndex][rowIndex] != 0 || !player) {
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
        if(board[colIndex][rowIndex] == 0 && player) {
            move.setFill(Color.BLACK);
            System.out.printf("Clicked [%d, %d]%n", colIndex, rowIndex);
            if (player)
                board[colIndex][rowIndex] = 1;
            else
                board[colIndex][rowIndex] = 2;
            player = false;
            if (player)
                move = new Text("O");
            else
                move = new Text("X");
            aiMap = new Tree(new TreeNode(board, colIndex, rowIndex));
            buildTree(aiMap);
            //System.out.println(aiMap);
            //System.out.println(board);
            gameOver();
            player = false;
            aiMove(scoreTree());
        }
    }

    @FXML
    private void handleClose(ActionEvent event) {
        System.exit(0);
        Platform.exit();
    }

}
