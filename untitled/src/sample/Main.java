package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sudoku.BacktrackingSudokuSolver;
import sudoku.BoardCleaner;
import sudoku.Level;
import sudoku.SudokuBoard;

public class Main extends Application {

    Stage primaryStage;
    Scene menuScene, playScene;
    SudokuBoard board = new SudokuBoard();
    BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();

    @Override
    public void start(Stage primaryStage) throws Exception {
        solver.solve(board);
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Sudoku");
        menuScene = loadMenuScene();

        primaryStage.setScene(menuScene);
        primaryStage.show();
    }

    public Scene loadMenuScene() {
        Button btn = new Button();
        Button btn2 = new Button();
        Button btn3 = new Button();

        btn.setText("PLAY EASY");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    BoardCleaner.clean(board, Level.easy);
                } catch (Exception e) {
                    System.out.println("exception");
                }
                playScene = loadPlayScene();
                primaryStage.setScene(playScene);
            }
        });

        btn2.setText("PLAY MEDIUM");
        btn2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    BoardCleaner.clean(board, Level.medium);
                } catch (Exception e) {
                    System.out.println("exception");
                }
                playScene = loadPlayScene();
                primaryStage.setScene(playScene);
            }
        });

        btn3.setText("PLAY HARD");
        btn3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    BoardCleaner.clean(board, Level.hard);
                } catch (Exception e) {
                    System.out.println("exception");
                }
                playScene = loadPlayScene();

                primaryStage.setScene(playScene);
            }
        });

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        grid.add(btn, 1, 1);
        grid.add(btn2, 1, 2);
        grid.add(btn3, 1, 3);


        return new Scene(grid, 400, 400);
    }

    public Scene loadPlayScene() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        TextField tf;
        try {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board.get(i, j) == 0) {
                        tf = new TextField("");
                    } else {
                        tf = new TextField("" + board.get(i, j));
                    }
                    grid.add(tf, i, j);
                }
            }
        }
        catch (Exception e) {}

        return new Scene(grid, 400, 400);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
