package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    private BufferedWriter file;

    private static Logger instance = null;
    private Logger(String filename) {
        try {
            file = new BufferedWriter(new FileWriter(filename, true));
        }
        catch(IOException e)
        {
        }
    }

    public static Logger getInstance() {
        if(instance == null)
        {
            instance = new Logger("log.csv");
        }

        return instance;
    }

    public void write(String str) {
        try {
            file.write(str + "," + getTimestamp());
            file.flush();
        }
        catch(IOException e)
        {

        }
    }

    private static String getTimestamp() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date) + "\n";
    }
}
