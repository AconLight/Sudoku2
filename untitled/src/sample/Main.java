package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
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
    TextField tfLoad, tfSave;
    private FileSudokuBoardDao fileSudokuBoardDao;
    private JbdcSudokuBoardDaoFactory db;

    @Override
    public void start(Stage primaryStage) throws Exception {
        db = (JbdcSudokuBoardDaoFactory) SudokuBoardDaoFactory.getDatabaseDao("asd");
        db.connect();
        db.dropTables();
        db.createTables();
        LoggerManager.init();
        solver.solve(board);
        fileSudokuBoardDao = (FileSudokuBoardDao) SudokuBoardDaoFactory.getFileDao("file.ser");
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
        grid.setHgap(11);
        grid.setVgap(11);
        grid.setPadding(new Insets(25, 25, 25, 25));
        TextField tf;
        tfs = new TextField[9][9];
        try {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board.get(i, j) == 0) {
                        tf = new TextField("");
                        board.getSudokuField(i, j).setCanBeModify(true);
                    } else {
                        tf = new TextField("" + board.get(i, j));
                        board.getSudokuField(i, j).setCanBeModify(false);
                        tf.setDisable(true);


                    }
                    tfs[i][j] = tf;
                    grid.add(tf, i, j);
                }
            }
        } catch (Exception e) {
        }

        tfLoad = new TextField("board123");
        tfSave = new TextField("board123");

        grid.add(tfSave, 1, 11);
        grid.add(tfLoad, 8, 11);

        Button btn = new Button();
        btn.setText("save");
        btn.setPrefWidth(125);
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                fileSudokuBoardDao.write(board);
            }
        });
        Button btn2 = new Button();
        btn2.setText("load");
        btn2.setPrefWidth(125);
        btn2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                board = fileSudokuBoardDao.read();
                //playScene = loadPlayScene();
                updatePlayScene();
                primaryStage.setScene(playScene);
            }
        });

        Button btn4 = new Button();
        btn4.setText("DB save");
        btn4.setPrefWidth(125);
        btn4.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    db.saveSudokuBoard(board, tfSave.getText());
                }catch (SudokuRuntimeException e) {
                    logger.info(e.toString());
                }
            }
        });
        Button btn5 = new Button();
        btn5.setText("DB load");
        btn5.setPrefWidth(125);
        btn5.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    board = db.loadSudokuBoard(tfLoad.getText());
                }catch (SudokuRuntimeException e) {
                    logger.info(e.toString());
                }

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
        grid.add(btn3, 5, 12);
        grid.add(btn4, 3, 14);
        grid.add(btn5, 6, 14);
        return new Scene(grid, 600, 600);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
