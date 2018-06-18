package sudoku;

import java.sql.*;
import java.util.logging.Logger;

/**
 * Created by Wojciech on 2018-06-11.
 */
public class JbdcSudokuBoardDaoFactory implements Dao<SudokuBoard>{
    private  String framework = "embedded";
    private  String protocol = "jdbc:derby:";
    private  String dbName = "DB";

    private String name;

    private  String tableName = "SudokuBoards";

    private  Connection conn = null;
    private  Statement stmt = null;

    private final static Logger logger = Logger.getLogger(LoggerManager.class.getName());

    public JbdcSudokuBoardDaoFactory(String name) {
        this.name = name;
    }

    public void connect()
    {
        try {
            conn = DriverManager.getConnection(protocol + dbName + ";create=true");
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }

    public void dropTables() {
        try {
            stmt.executeUpdate("drop table fields");
            conn.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void createTables() {
        try {
            DatabaseMetaData dmd = conn.getMetaData();
            ResultSet rs = dmd.getTables(null, "APP", "FIELDS", null);
            if (!rs.next()) {
                stmt.executeUpdate("create table FIELDS (" +
                        "id int," +
                        "value int," +
                        "boardName varchar(50)," +
                        "modify varchar(10)" +
                        ")");
                conn.commit();
                logger.info("Created table fields");
            } else {
                logger.info("Table fields already exists");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void saveSudokuBoard(SudokuBoard board, String name) throws SudokuBoardOutOfBoundGetException {
        String nm = name;
        try {
            String sql = "insert into FIELDS values (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            int numerator = 1;
            for (int i=0;i<9;i++) {
                for (int j = 0; j < 9; j++) {
                    ps.setInt(1, numerator);
                    ps.setInt(2, board.get(i, j));
                    ps.setString(3, nm);
                    ps.setString(4, Boolean.toString(board.getSudokuField(i, j).getCanBeModify()));

                    ps.executeUpdate();
                    conn.commit();
                    numerator++;
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public SudokuBoard loadSudokuBoard(String name) throws SudokuRuntimeException {
        SudokuBoard nsb = new SudokuBoard();

        ResultSet rs2 = null;
        try {
            rs2 = stmt.executeQuery("SELECT * FROM FIELDS");
            int i=0,j=0;

            while (rs2.next()) {
                if (rs2.getString("BOARDNAME").equals(name)) {
                    Boolean bol = false;
                    if (rs2.getString("MODIFY").equals("true")) bol = true;

                    nsb.getSudokuField(i, j).setCanBeModify(bol);
                    nsb.set(i, j, rs2.getInt(2));
                    if (i == 9) break;
                    if (j == 8) {
                        i++;
                        j = 0;
                    } else {
                        j++;
                    }


                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nsb;
    }

    @Override
    public SudokuBoard read() {
        SudokuBoard board;

        try {
            board = loadSudokuBoard(name);
        } catch (SudokuRuntimeException e) {
            e.printStackTrace();
            board = new SudokuBoard();
        }

        return board;
    }

    @Override
    public void write(SudokuBoard obj) {
        try {
            saveSudokuBoard(obj, name);
        } catch (SudokuBoardOutOfBoundGetException e) {
            e.printStackTrace();
        }

    }

    public void finalize() {

    }
}
