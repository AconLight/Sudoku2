package sudoku;

/**
 * Created by Wojciech on 2018-06-18.
 */
public interface Dao <T> {

    public T read();
    public void write(T obj);
}
