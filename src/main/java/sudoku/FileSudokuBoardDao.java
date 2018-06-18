package sudoku;

import java.io.*;

/**
 * Created by Wojciech on 2018-06-18.
 */
public class FileSudokuBoardDao implements Dao<SudokuBoard> {

    private FileOutputStream myfileOut;
    private FileInputStream myfileIn;
    private ObjectOutputStream myOut;
    private ObjectInputStream myIn;
    private String fileName;

    public FileSudokuBoardDao(String fileName) {
        this.fileName = fileName;
    }

    public void finalize() {
        try {
            myfileIn.close();
            myfileOut.close();
            myIn.close();
            myOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SudokuBoard read() {
        SudokuBoard e;
        try (FileInputStream fileIn = new FileInputStream(fileName)){
            myfileIn = fileIn;
            ObjectInputStream in = new ObjectInputStream(fileIn);
            myIn = in;
            e = (SudokuBoard) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("SudokuBoard class not found");
            c.printStackTrace();
            return null;
        }

        return e;
    }

    public void write(SudokuBoard e) {
        try (FileOutputStream fileOut =
                     new FileOutputStream(fileName)){

            myfileOut = fileOut;
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            myOut = out;
            out.writeObject(e);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
