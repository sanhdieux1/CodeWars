package com.kail.kyu6.sort_the_odd;

public class SortTheOdd {
    
    public static int[] sort(int[] input) {
        for (int i = 0; i < input.length; i+=2) {
            if(input[i] == 0) {
                continue;
            }
            for (int i1 = input.length - (input.length%2 == 0 ? 2 : 1) ; i1 > i; i1 -= 2) {
                if(input[i1] == 0) {
                    continue;
                }
                if(input[i] > input[i1]) {
                    int temp = input[i];
                    input[i] = input[i1];
                    input[i1] = temp;
                }
            }
        }
        return input;
    }
    public static void main(String[] args) {
        int[] result = sort(new int[]{5, 3, 2, 8, 11, 4, 3, 0, 0, 1, 1});
        for (int i : result) {
            System.out.print(i + ",");
        }
    }
}
