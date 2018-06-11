package sudoku;

import java.io.*;

public class SudokuSerializer {
    public static void serialize(SudokuBoard e) {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("sudoku2.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(e);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static SudokuBoard deserialize() {
        SudokuBoard e;
        try {
            FileInputStream fileIn = new FileInputStream("sudoku2.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
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
}
