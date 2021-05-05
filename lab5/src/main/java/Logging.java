public class Logging {

    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";


    private static LoggingLevel level = LoggingLevel.DEBUG;

    public static void setLevel(LoggingLevel level) {
        Logging.level = level;
    }

    public static LoggingLevel getLevel() {
        return Logging.level;
    }

    public static void debug(String message) {
        if (Logging.level.value.equals(LoggingLevel.DEBUG.value))
            System.out.println(ANSI_GREEN + "DEBUG: " + message + ANSI_RESET);
    }

    public static void info(String message) {
        if (Logging.level.value <= LoggingLevel.INFO.value)
            System.out.println(ANSI_GREEN + "INFO: " + message + ANSI_RESET);
    }

    public static void warning(String message) {
        if (Logging.level.value <= LoggingLevel.WARNING.value)
            System.out.println(ANSI_RED + "WARNING: " + message + ANSI_RESET);
    }

    public static void error(String message) {
        if (Logging.level.value <= LoggingLevel.ERROR.value)
            System.out.println(ANSI_RED + "ERROR: " + message + ANSI_RESET);
    }


}
