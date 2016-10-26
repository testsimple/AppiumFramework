package libraries.utility;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Created by User on 7/11/2016.
 */
public class Log {

    static {
        PropertyConfigurator.configure(System.getProperty("user.dir") + "/src/main/config/log4j.properties");
    }
    private static Logger Log = Logger.getLogger("toLog");

    public static void startTestCase(String testCaseName) {
        Log.info("****************************************************************************************");
        Log.info("+++                              " + testCaseName + "                             +++");
        Log.info("****************************************************************************************");
    }

    public static void endTestCase(String sTestCaseName) {
        Log.info("---------------            " + "-E---N---D-" + "            ---------------");
    }

    public static void info(String message) {
        Log.info(message);
    }

    public static void warn(String message) {
        Log.warn(message);
    }

    public static void error(String message) {
        Log.error(message);
    }

    public static void fatal(String message) {
        Log.fatal(message);
    }

    public static void debug(String message) {
        Log.debug(message);
    }
}
