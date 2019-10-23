package com.kail.range_extraction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RangeExtraction {
    public static String rangeExtraction(int[] input) {
        LinkedList<Integer> queue = IntStream.of(input).mapToObj(Integer::new).collect(Collectors.toCollection(LinkedList::new));
        return extract(queue).stream().map(Extraction::getValue).collect(Collectors.joining(","));
    }
    
    public static List<Extraction> extract(LinkedList<Integer> queue) {
        if (queue.isEmpty()) {
            return Collections.emptyList();
        }
        List<Extraction> result = new ArrayList<>();
        Extraction extraction = new Extraction();
        result.add(extraction);
        while (!queue.isEmpty()) {
            Integer number = queue.pop();
            if (!extraction.add(number)) {
                queue.addFirst(number);
                result.addAll(extract(queue));
                break;
            }
        }
        return result;
    }
    
    public static class Extraction {
        private Integer begin;
        private Integer current;
        
        public boolean add(int value) {
            if (begin == null) {
                current = value;
                begin = value;
                return true;
            }
            if (current + 1 == value) {
                current = value;
                return true;
            }
            return false;
        }
        
        public String getValue() {
            if (begin == current) {
                return String.valueOf(begin);
            }
            if (begin < current - 1) {
                return String.format("%d-%d", begin, current);
            }
            return String.format("%d,%d", begin, current);
        }
    }
}
