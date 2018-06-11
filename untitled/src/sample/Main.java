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
import sudoku.*;

import java.util.logging.Logger;

public class Main extends Application {

    Stage primaryStage;
    Scene menuScene, playScene;
    SudokuBoard board = new SudokuBoard();
    BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
    TextField[][] tfs;
    private final static Logger logger = Logger.getLogger(LoggerManager.class.getName());

    @Override
    public void start(Stage primaryStage) throws Exception {
        LoggerManager.init();
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
                    logger.info(e.toString());
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
                    logger.info(e.toString());
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
                    logger.info(e.toString());
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


        return new Scene(grid, 600, 600);
    }

    public void updatePlayScene() {
        try {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board.get(i, j) == 0) {
                        tfs[i][j].setText("");
                    } else {
                        tfs[i][j].setText("" + board.get(i, j));
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public Scene loadPlayScene() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        TextField tf;
        tfs = new TextField[9][9];
        try {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board.get(i, j) == 0) {
                        tf = new TextField("");
                    } else {
                        tf = new TextField("" + board.get(i, j));
                        tf.setDisable(true);
                    }
                    tfs[i][j] = tf;
                    grid.add(tf, i, j);
                }
            }
        } catch (Exception e) {
        }
        Button btn = new Button();
        btn.setText("save game");
        btn.setPrefWidth(125);
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                SudokuSerializer.serialize(board);
            }
        });
        Button btn2 = new Button();
        btn2.setText("load game");
        btn2.setPrefWidth(125);
        btn2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                board = SudokuSerializer.deserialize();
                //playScene = loadPlayScene();
                updatePlayScene();
                primaryStage.setScene(playScene);
            }
        });

        Button btn3 = new Button();
        btn3.setText("apply");
        btn3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        try {
                            if (tfs[i][j].getCharacters().toString().equals("")) {
                                board.set(i, j, 0);
                            }
                            else {
                                board.set(i, j, Integer.parseInt(tfs[i][j].getCharacters().toString()));
                            }
                        } catch (Exception e) {
                            logger.info(e.toString());
                        }


                    }

                }
                board = new SudokuBoard(board);
                if (board.checkBoard()) {
                    updatePlayScene();

                    primaryStage.setScene(playScene);
                }
                else {
                    logger.info("Sudoku is wrongly solved!");
                }
            }
        });

        grid.add(btn, 3, 10);
        grid.add(btn2, 6, 10);
        grid.add(btn3, 4, 12);
        return new Scene(grid, 600, 600);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
