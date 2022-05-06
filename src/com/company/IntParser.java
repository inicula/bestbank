package com.company;
import java.lang.Integer;

public class IntParser implements Parser<Integer> {
    public Integer parse(String str){
        return Integer.parseInt(str);
    }
}
