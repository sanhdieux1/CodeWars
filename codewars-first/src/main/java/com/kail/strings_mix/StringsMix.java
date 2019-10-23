package com.kail.strings_mix;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class StringsMix {
    
    public static String mix(String s1, String s2) {
        Map<Integer, SingleCharacter> s1Map = new HashMap<>();
        s1.chars().forEach(c -> s1Map.compute(c, (k, v) -> v != null ? v.increase() : new SingleCharacter(k, "1")));
        
        Map<Integer, SingleCharacter> s2Map = new HashMap<>();
        s2.chars().forEach(c -> s2Map.compute(c, (k, v) -> v != null ? v.increase() : new SingleCharacter(k, "2")));
        
        s1Map.forEach((k, v) -> {
            s2Map.merge(k, v, (o1, o2) -> {
                if (o1.getCount() == o2.getCount()) {
                    o2.setGroup("=");
                    return o2;
                } else if (o1.getCount() < o2.getCount()) {
                    o2.setGroup("1");
                    return o2;
                } else {
                    o1.setGroup("2");
                    return o1;
                }
            });
        });
        
        return s2Map.entrySet().stream().filter(entry -> entry.getKey() >= 97 && entry.getKey() <= 122 && entry.getValue().getCount() > 1)
                .map(Map.Entry::getValue)
                .map(SingleCharacter::toString)
                .sorted(Comparator.comparing(String::length).reversed().thenComparing(String::compareTo))
                .collect(Collectors.joining("/"));
    }
    
    public static class SingleCharacter {
        private int count;
        private int c;
        private String group;
        
        SingleCharacter(int c, String g) {
            count = 1;
            this.c = c;
            this.group = g;
        }
        
        public SingleCharacter increase() {
            count++;
            return this;
        }
        
        public String getGroup() {
            return group;
        }
        
        public void setGroup(String group) {
            this.group = group;
        }
        
        public int getCount() {
            return count;
        }
        
        public int getC() {
            return c;
        }
        
        @Override
        public String toString() {
            return group + ":" + String.join("", Collections.nCopies(count, Character.toString((char) c)));
        }
    }
}
