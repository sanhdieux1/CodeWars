package com.kail.next_smaller_number;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.Comparator;

public class Kata
{
    public static long nextSmaller(long n)
    {
        
        LinkedList<String> input = Stream.of(String.valueOf(n).split("")).collect(Collectors.toCollection(LinkedList::new));
        LinkedList<HoanViTask> tasks = new LinkedList();
        tasks.add(new HoanViTask(new StringBuilder(), input));
        
        long result;
        int index = 2;
        while (index <= input.size()){
            LinkedList<String> sublist = input.subList(input.size() - index, input.size()).stream()
                    .sorted(Comparator.comparing(String::toString).reversed()).collect(Collectors.toCollection(LinkedList::new));
            String prefix = input.subList(0, input.size() - index).stream().collect(Collectors.joining());
            long match = getAllHoanVi(sublist, v -> Long.valueOf(prefix.concat(v)) < n);
            if(match != -1){
                return Long.valueOf(prefix.concat(String.valueOf(match)));
            }
            index++;
        }
        return -1;
    }
    
    public static long getAllHoanVi(LinkedList<String> input, Predicate<String> isMatched){
        LinkedList<HoanViTask> tasks = new LinkedList();
        tasks.add(new HoanViTask(new StringBuilder(), input));
        while (!tasks.isEmpty()) {
            HoanViTask task = tasks.pop();
            if(task.isFinal()){
                String value = task.getHoanVi();
                if(isMatched.test(value)){
                    return Long.valueOf(value);
                }
            } else {
                tasks.addAll(0, task.getSublist());
            }
        }
        return -1;
    }
    
    public static class HoanViTask {
        private StringBuilder parent;
        private LinkedList<String> input;
        
        public HoanViTask(StringBuilder parent, LinkedList<String> input) {
            this.parent = parent;
            this.input = input;
        }
        
        public boolean isFinal(){
            return this.input.size() == 1;
        }
        
        public String getHoanVi() {
            return parent.append(input.get(0)).toString();
        }
        
        public LinkedList<HoanViTask> getSublist(){
            return input.stream().map(root -> {
                if(parent.length() == 0 && root.equals("0")){
                    return null;
                }
                StringBuilder parentClone = new StringBuilder(parent);
                StringBuilder child = parentClone.append(root);
                LinkedList<String> clone = (LinkedList<String>) input.clone();
                clone.remove(input.indexOf(root));
                return new HoanViTask(child, clone);
            }).filter(Objects::nonNull).collect(Collectors.toCollection(LinkedList::new));
        }
    }
    
    
    
    
    static int[] b = new int[12];
    static int[] a = new int[12];
    static int[] input = {1,2,3};
    static int n;
    
    static void init()
    {
        n = 9; //123
        for(int i=0;i<n;i++)
            b[i]=0;
    }
    
    static void output()
    {
//        for(int i=0;i<n;i++) {
//            System.out.print(a[i]);
//        }
//        System.out.println();
    }
    
    static void tim(int i)
    {
        for(int j=0;j<n;j++)
        {
            if(b[j]==0)
            {
                a[i]=j+1;
                b[j]=1; // danh dau da su dung so nay
                if(i==n-1)
                    output();
                else
                    tim(i+1);
                b[j]=0;
            }
        }
    }
    
    public static void main(String[] ar)
    {
        init();
        tim(0);
    }
}