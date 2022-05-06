package com.company;
import java.util.ArrayList;

public class CSVReader {
    private Parser parser;
    private static CSVReader instance = null;

    private CSVReader(Parser parser) {
        this.parser = parser;
    }

    public static CSVReader getInstance(Parser parser) {
        if(instance == null) {
            instance = new CSVReader(parser);
        }

        return instance;
    }

    public ArrayList getValues(String line) {
        ArrayList res = new ArrayList();

        var arr = line.split("[,]");
        for(String str : arr)
        {
            res.add(parser.parse(str));
        }

        return res;
    }
}
