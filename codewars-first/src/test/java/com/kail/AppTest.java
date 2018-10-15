package com.kail;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        IntStream.range(1, 500).boxed().map(i -> split2(i)).forEach(System.out::println);
        System.out.println(split2(50));
    }

    public static class TempResult {
        List<Long> footprint;
        long area;
        long pivot;

        public TempResult(List<Long> footprint, long area, long pivot) {
            this.footprint = footprint;
            this.area = area;
            this.pivot = pivot;
        }

        public static TempResult dig(TempResult result, long next) {
            List<Long> list = new ArrayList<>();
            list.addAll(result.footprint);
            list.add(next);
            return new TempResult(list, result.area + next * next, next);
        }
    }

    public static class DeepSearch {

        public static List<Long> split(long input) {
            long totalArea = input * input;
            LinkedList<TempResult> stack = new LinkedList<>();
            for (long next = input - 1; next >= Math.ceil(Math.sqrt(input)); next--) {
                stack.add(new TempResult(Collections.singletonList(next), next * next, next));

                while (!stack.isEmpty()) {
                    TempResult first = stack.pollLast();
                    long ceil = Math.min(first.pivot - 1, (long) Math.floor(Math.sqrt(totalArea - first.area)));
                    for (long _next = ceil; _next >= Math.ceil(Math.sqrt(ceil)); _next--) {
                        TempResult dig = TempResult.dig(first, _next);
                        TempResult digNext = TempResult.dig(first, _next - 1);
                        if (dig.area == totalArea) {
                            System.out.println(dig.footprint.stream().map(String::valueOf).collect(Collectors.joining("-", "[", "]")));
                            return dig.footprint;
                        } else if (dig.area < totalArea) {
                            stack.add(dig);
                        } else {
                            stack.addFirst(digNext);
                        }
                    }
                }
            }
            return Collections.emptyList();
        }
    }


    public static String split2(long n) {
        long totalArea = n * n;
        LinkedList<TempResult> stack = new LinkedList<>();
        for (long next = n - 1; next >= Math.ceil(Math.sqrt(n)); next--) {
//        long next = n - 1;
            stack.add(new TempResult(Collections.singletonList(next), next * next, next));
            while (!stack.isEmpty()) {
                LinkedList<TempResult> _stack = new LinkedList<>();
                TempResult first = stack.pollLast();
                long ceil = Math.min(first.pivot - 1, (long) Math.floor(Math.sqrt(totalArea - first.area)));
                boolean hasNext = false;
                for (long _next = ceil; _next >= 1; _next--) {
                    hasNext = false;
                    TempResult dig = TempResult.dig(first, _next);
                    TempResult digNext = TempResult.dig(first, _next - 1);
                    if (dig.area == totalArea) {
                        return dig.footprint.stream().sorted().map(String::valueOf).collect(Collectors.joining(" "));
                    } else if (dig.area < totalArea) {
                        _stack.addFirst(dig);
                    } else {
                        hasNext = true;
                        _stack.addLast(digNext);
                    }
                }
                if(hasNext) {
                    Collections.swap(_stack, _stack.size() - 1, 0);
                }
                stack.addAll(_stack);
            }
        }
        return null;
    }
}
