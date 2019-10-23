package com.kail.weight_for_weight;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Weight {
    
    public static String sort(String input) {
        List<SingleWeight> weightList = Stream.of(input.split(" "))
                .filter(s -> !s.isEmpty())
                .map(SingleWeight::new)
                .collect(Collectors.toList());
        weightList.sort(Comparator.comparing(SingleWeight::virtualWeight).thenComparing(SingleWeight::getWeight));
        
        return weightList.stream().map(SingleWeight::getWeight).collect(Collectors.joining(" "));
    }
    
    
    public static class SingleWeight{
        private String weight;
        SingleWeight(String w){
            weight = w;
        }
    
        public String getWeight() {
            return weight;
        }
    
        public long virtualWeight(){
            return weight.chars()
                    .mapToObj(c -> String.valueOf((char)c))
                    .map(Long::valueOf)
                    .reduce(0L, Long::sum);
        }
    }
}
