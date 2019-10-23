package com.kail.strip_comments;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StripComments implements Cloneable{
    public static String stripComment(String text, String[] commentSymbols) {
        List<String> symbols = Stream.of(commentSymbols).collect(Collectors.toList());
        String symbolsPattern = symbols.stream().collect(Collectors.joining("\\\\"));
        final String pattern = "([^" + symbolsPattern + "]*)" + "[" + symbolsPattern + "].*";
        return Stream.of(text.split("\n")).map(line ->
            line.replaceAll(pattern, "$1")).map(s -> s.replaceAll("\\s+$","")).collect(Collectors.joining("\n"));
    }
    
    @Override
    protected StripComments clone() throws CloneNotSupportedException {
        return (StripComments)super.clone();
    }
}
