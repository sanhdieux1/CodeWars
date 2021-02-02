package com.kail.snail;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class Snail {
    private int step = 0;
    private int n;
    
    public int[] process(int[][] array) {
        this.n = array.length;
        if(n == 2 && array[0].length != 2) {
            return new int[]{};
        }
        int totalStep = this.n / 2;
        int[] result = new int[]{};
        for (step = 0; step <= totalStep; step++) {
            result = concatinate(result, concatinate(concatinate(
                    concatinate(right(array), bottom(array)), left(array)),
                    top(array)));
        }
        return result;
    }
    
    public static int[] concatinate(int[] main, int[] toAdded) {
        return ArrayUtils.addAll(main, toAdded);
    }
    public static int[] rotate(int[] input) {
        int[] output = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            output[input.length - i - 1] = input[i];
        }
        return output;
    }
    
    public int[] right(int[][] array) {
        int startIndex = step;
        int endIndex = n - step;
        int arrayIndex = step;
        int length = endIndex - startIndex;
        if(length <= 0 || array[arrayIndex].length < endIndex) {
            return new int[]{};
        }
        return Arrays.copyOfRange(array[arrayIndex], startIndex, endIndex);
    }
    
    public int[] bottom(int[][] array) {
        int startIndex = step + 1;
        int endIndex = n - step - 1;
        int arrayIndex = n - step - 1;
        int length = endIndex - startIndex + 1;
        if(length <= 0) {
            return new int[]{};
        }
        int[] result = new int[length];
        int j = 0;
        for (int i = startIndex; i <= endIndex; i++) {
            result[j] = array[i][arrayIndex];
            j++;
        }
        return result;
    }
    
    public int[] left(int[][] array) {
        int startIndex = n - step - 1;
        int endIndex = step;
        int arrayIndex = n - step - 1;
        int length = startIndex - endIndex + 1;
        if(length <= 0) {
            return new int[]{};
        }
        return rotate(Arrays.copyOfRange(array[arrayIndex], endIndex, startIndex));
    }
    public int[] top(int[][] array) {
        int startIndex = n - step - 2;
        int endIndex = step + 1;
        int arrayIndex = step;
        int length = startIndex - endIndex + 1;
        if(length <= 0) {
            return new int[]{};
        }
        int[] result = new int[length];
        int j = 0;
        for (int i = startIndex; i >= endIndex; i--) {
            result[j] = array[i][arrayIndex];
            j++;
        }
        return result;
    }
    
    @Test
    public void test() {
        Snail s = new Snail();
//        int[] result = s.snail(new int[][]{{1, 2, 3,4,5},
//                new int[]{6,7,8,9,10},
//                new int[]{11,12,13,14,15},
//                new int[]{16,17,18,19,20},
//                new int[]{21,22,23,24,25}
//        });
//        int[] result = s.process(
//                new int[][]{
//                        new int[]{1,2,3,9},
//                        new int[]{4,5,6,4},
//                        new int[]{7,8,9,1},
//                        new int[]{1,2,3,4}
//        });
//        Assert.assertEquals("[1, 2, 3, 9, 4, 1, 4, 3, 2, 1, 7, 4, 5, 6, 9, 8]", Arrays.toString(result));
        int[][] array
                = {{}};
        Assert.assertEquals("[]", Arrays.toString(s.process(array)));
    }
    
}
