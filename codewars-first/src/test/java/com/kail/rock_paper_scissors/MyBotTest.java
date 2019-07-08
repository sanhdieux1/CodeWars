package com.kail.rock_paper_scissors;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class MyBotTest {
    
    @Test
    public void test_happycase(){

        LinkedList<MyBot.Shape> player1 = new LinkedList<>();
        player1.addAll(Arrays.asList(MyBot.Shape.Rock, MyBot.Shape.Paper, MyBot.Shape.Rock, MyBot.Shape.Paper, MyBot.Shape.Rock, MyBot.Shape.Paper));
        MyBot m = runTest(player1);
        LinkedList<MyBot.Shape> strategy = m.getStrategies().get("player1").getStrategy();
        assertEquals(2, strategy.size());
        
        
    }
    
    @Test
    public void test_case2(){
        
        LinkedList<MyBot.Shape> player1 = new LinkedList<>();
        player1.addAll(Arrays.asList
                (MyBot.Shape.Rock, MyBot.Shape.Paper, MyBot.Shape.Rock, MyBot.Shape.Paper, MyBot.Shape.Rock, MyBot.Shape.Paper, MyBot.Shape.Scissors,
                        MyBot.Shape.Rock, MyBot.Shape.Paper, MyBot.Shape.Rock, MyBot.Shape.Paper, MyBot.Shape.Rock, MyBot.Shape.Paper, MyBot.Shape.Scissors
                ));
        MyBot m = runTest(player1);
        LinkedList<MyBot.Shape> strategy = m.getStrategies().get("player1").getStrategy();
        assertEquals(7, strategy.size());
        
    }
    
    @Test
    public void test_case3(){
        
        LinkedList<MyBot.Shape> player1 = new LinkedList<>();
        player1.addAll(Arrays.asList
                (MyBot.Shape.Rock, MyBot.Shape.Rock, MyBot.Shape.Rock, MyBot.Shape.Paper,
                        MyBot.Shape.Rock, MyBot.Shape.Rock, MyBot.Shape.Rock, MyBot.Shape.Paper
                ));
        MyBot m = runTest(player1);
        LinkedList<MyBot.Shape> strategy = m.getStrategies().get("player1").getStrategy();
        assertEquals(4, strategy.size());
        
    }
    
    @Test
    public void test_case4(){
        
        LinkedList<MyBot.Shape> player1 = new LinkedList<>();
        player1.addAll(Arrays.asList
                (MyBot.Shape.Rock, MyBot.Shape.Paper, MyBot.Shape.Paper, MyBot.Shape.Rock,
                        MyBot.Shape.Rock, MyBot.Shape.Paper, MyBot.Shape.Paper, MyBot.Shape.Rock,
                        MyBot.Shape.Rock, MyBot.Shape.Paper, MyBot.Shape.Paper, MyBot.Shape.Rock
                ));
        MyBot m = runTest(player1);
        LinkedList<MyBot.Shape> strategy = m.getStrategies().get("player1").getStrategy();
        assertEquals(4, strategy.size());
        
    }
    
    @Test
    public void test_case5(){
        LinkedList<MyBot.Shape> player1 = new LinkedList<>();
        player1.addAll(Arrays.asList
                (MyBot.Shape.Paper, MyBot.Shape.Rock, MyBot.Shape.Paper, MyBot.Shape.Paper, MyBot.Shape.Rock,
                        MyBot.Shape.Paper, MyBot.Shape.Rock, MyBot.Shape.Paper, MyBot.Shape.Paper, MyBot.Shape.Rock,
                        MyBot.Shape.Paper, MyBot.Shape.Rock, MyBot.Shape.Paper, MyBot.Shape.Paper, MyBot.Shape.Rock
                ));
        MyBot m = runTest(player1);
        LinkedList<MyBot.Shape> strategy = m.getStrategies().get("player1").getStrategy();
        assertEquals(5, strategy.size());
    }
    
    @Test
    public void test_case_random() {
        for(int i = 1; i<200; i++){
            int stragegySize = randomNum(5,7);
            
            LinkedList<MyBot.Shape> stragegy = randomStragegy(stragegySize);
            LinkedList<MyBot.Shape> player = new LinkedList<>();
            int repeat = randomNum(5,7);
            for (int j=0 ; j< repeat; j++) {
                player.addAll(stragegy);
            }
            MyBot m = runTest(player);
            LinkedList<MyBot.Shape> strategy = m.getStrategies().get("player1").getStrategy();
            if(stragegySize < strategy.size()) {
                System.out.println((strategy.toString()
                        .concat("\nStragegy:").concat(stragegy.toString())
                .concat("\nLogs:").concat(m.getLogs().toString())));
                System.out.println("");
            }
            
        }
        
        
    }
    
    public MyBot runTest(LinkedList<MyBot.Shape> player){
        MyBot m = new MyBot();
        m.setNewMatch("player1");
        player.stream().map(MyBot.Shape::getValue).forEach(v -> {
            m.getShape();
            m.setOpponentShape(v);
        });
        
        return m;
    }
    
    public String random(){
        String abc = "RPS";
        Random rd = new Random();
        char letter = abc.charAt(rd.nextInt(abc.length()));
        return String.valueOf(letter);
    }
    
    public int randomNum(int min, int max){
        Random rd = new Random();
        return rd.nextInt(max - min) + min;
    }
    public List<String> randomList(int size){
        return IntStream.range(0, size).mapToObj(i -> random()).collect(Collectors.toList());
    }
    
    public LinkedList<MyBot.Shape> randomStragegy(int times){
        return randomList(times).stream().map(MyBot.Shape::fromValue).collect(Collectors.toCollection(() -> new LinkedList<>()));
    }
}
