package sudoku;

import java.io.IOException;
import java.util.logging.*;
import java.util.logging.Level;

/**
 * Created by Wojciech on 2018-06-11.
 */
public class LoggerManager {
    private final static Logger logger = Logger.getLogger(LoggerManager.class.getName());
    private static FileHandler fh = null;

    public static void init(){
        try {
            fh=new FileHandler("LoggerManager.log", false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        fh.setFormatter(new SimpleFormatter());
        logger.addHandler(fh);
        logger.setLevel(Level.INFO);
        logger.info("Logger init");
    }
}
