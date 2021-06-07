import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Log {
    private static boolean fileBoolean = false;
    private static FileWriter logWriter;
    private static File logFile;
    private static Date logDate = new Date();

    // static log for String
    public static void log(String logString) {
        // if FileWriter doesn't exist, make it
        if ( !fileBoolean )
            makeLogWriter();

        // write in log.txt
        try {
            logWriter.write(logDate.toString() + ", " + logString + "\n");
            logWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // static log for Exception
    public static void log(Exception e) {
        log(e.toString());
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
            logWriter.write(logDate.toString() + ", log.txt created\n");
            logWriter.flush();
            fileBoolean = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // close the FileWriter
    public static void close() {
        try {
            log("closing the (FileWriter) logWriter");
            logWriter.close();
            fileBoolean = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
