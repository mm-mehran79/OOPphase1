package log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Log {
    // public finals: LogTypes
    public static int ERROR = 0;
    public static int ALARM = 1;
    public static int INFO = 2;
    public static int EXCEPTION = 3;
    public static int LOG = 3;

    // properties
    private static boolean fileBoolean = false;
    private static FileWriter logWriter;
    private static File logFile;
    private static Date logDate = new Date();

    // static log for String
    public static void log(int logType,String logString) {
        // if FileWriter doesn't exist, make it
        if ( !fileBoolean )
            makeLogWriter();

        // write in log.txt
        try {
            if ( logType == ERROR)
                logWriter.write("[Error], " + logDate.toString() + ", " + logString + "\n");
            else if ( logType == ALARM)
                logWriter.write("[Alarm], " + logDate.toString() + ", " + logString + "\n");
            else if ( logType == INFO)
                logWriter.write("[Info], " + logDate.toString() + ", " + logString + "\n");
            else if ( logType == EXCEPTION)
                logWriter.write("[Exception], " + logDate.toString() + ", " + logString + "\n");
            else if ( logType == LOG)
                logWriter.write("[log], " + logDate.toString() + ", " + logString + "\n");
            logWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // static log for Exception
    public static void log(Exception e) {
        log(EXCEPTION, e.toString());
    }

    // making the new FileWriter
    private static void makeLogWriter() {
        // making log.txt
        logFile = new File("log.txt");
        try {
            if (logFile.createNewFile()) {
                System.out.println("log.txt file created: " + logFile.getName());
            } else {
                System.out.println("log.txt file already exists: " + logFile.getName());
            }
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }

        // making FileWriter && write the string
        try {
            logWriter = new FileWriter(logFile, true);
            logWriter.write("[Log], " + logDate.toString() + ", log.txt created or opened\n");
            logWriter.flush();
            fileBoolean = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // close the FileWriter
    public static void close() {
        try {
            log(LOG, "closing the (FileWriter) logWriter");
            logWriter.close();
            fileBoolean = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
