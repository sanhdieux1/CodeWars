package com.kail.meeting;


import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Meeting {

    public static String meeting(String input){
        return Stream.of(input.toUpperCase().split(";"))
                .map(str -> str.split(":"))
                .sorted(getComparator(1).thenComparing(getComparator(0)))
                .map(format())
                .collect(Collectors.joining());
    }
    
    public static Comparator<String[]> getComparator(int index){
        return Comparator.comparing(str -> str[index]);
    }
    
    public static Function<String[], String> format(){
        return str -> String.format("(%s, %s)", str[1], str[0]);
    }
    
    
    public static void main(String[] a){
        System.out.println(meeting("Megan:Arno;Anna:Dorny;Robert:Wahl;Michael:Russell;Paul:Russell;Alex:Russell;Robert:Meta;Haley:Bell;Emily:Rudd;Michael:Wahl;Grace:Met"));
//         (ARNO, MEGAN)(BELL, HALEY)(DORNY, ANNA)(MET, GRACE)(META, ROBERT)(RUDD, EMILY)(RUSSELL, ALEX)(RUSSELL, MICHAEL)(RUSSELL, PAUL)(WAHL, MICHAEL)(WAHL, ROBERT)
//"Expect: (ARNO, MEGAN)(BELL, HALEY)(DORNY, ANNA)(MET, GRACE)(META, ROBERT)(RUDD, EMILY)(RUSSELL, ALEX)(RUSSELL, MICHAEL)(RUSSELL, PAUL)(WAHL, MICHAEL)(WAHL, ROBERT)"
    }
}
