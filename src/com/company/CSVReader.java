package com.company;
import java.util.ArrayList;

public class CSVReader<T> {
    public CSVReader(Parser<T> parser) {
        this.parser = parser;
    }

    Parser<T> parser;

    public ArrayList<T> getValues(String line) {
        ArrayList<T> res = new ArrayList<T>();

        var arr = line.split("[,]");
        for(String str : arr)
        {
            res.add(parser.parse(str));
        }

        return res;
    }
}
