package com.mustafa.ecommercespringboot.utils;

import java.util.List;

public final class CommonUtils {
    private static final String COMMA = ",";
    private CommonUtils(){
        throw new UnsupportedOperationException("Utility class can not be instantiated.");
    }
    public static String arrayToCommaSeparatedString(List<String> arr){
        return String.join(COMMA,arr);
    }
    public static String[] commaSeparatedStringToArray(String anyThing){
        return anyThing.split(COMMA);
    }
}
