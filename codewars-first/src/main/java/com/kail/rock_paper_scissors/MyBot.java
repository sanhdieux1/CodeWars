package com.kail.rock_paper_scissors;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Stream;

public class MyBot implements RockPaperScissorsPlayer {
    
    private Map<String, Strategy> strategies = new HashMap<>();
    private Strategy currentStrategy;
    private LinkedList<Shape> logs = new LinkedList<>();
    @Override
    public void setNewMatch(String opponentName) {
        strategies.putIfAbsent(opponentName, new Strategy());
        currentStrategy = strategies.get(opponentName);
    }
    
    @Override
    public String getShape() {
        if(!currentStrategy.hasNext()) {
            currentStrategy.roll();
            if(!currentStrategy.hasNext()){
                currentStrategy.addShape(Shape.Rock);
            }
        }
        return currentStrategy.popShape().getValue();
    }
    
    @Override
    public void setOpponentShape(String value) {
        Shape shape = Shape.fromValue(value);
        logs.addLast(shape);
        if(currentStrategy.getCurrentShape() != shape) {
            currentStrategy.addShape(shape);
            currentStrategy.next();
        }
    }
    
    public Map<String, Strategy> getStrategies() {
        return strategies;
    }
    
    public LinkedList<Shape> getLogs() {
        return logs;
    }
    
    public static class Strategy {
        private LinkedList<Shape> strategy = new LinkedList();
        private int index = -1;
        public void addShape(Shape shape) {
            strategy.addLast(shape);
        }
    
        public Shape popShape() {
            if (!hasNext()) {
                return null;
            }
            index++;
            Shape shape = strategy.get(index);
            return shape;
        }
        
        public boolean hasNext(){
            return index < strategy.size() - 1;
        }
        
        public Shape getCurrentShape() {
            return strategy.get(index);
        }
        
        public void roll(){
            index = -1;
        }
        public void next(){
            index++;
        }
        public LinkedList<Shape> getStrategy() {
            return strategy;
        }
    }
    
    
    public enum Shape {
        Rock("R", "1"), Paper("P", "2"), Scissors("S", "3"), Random("R", "4");
        private String value;
        private String display;
        Shape(String value, String display) {
            this.value = value;
            this.display = display;
        }
        
        public String getValue(){return value;}
    
        public static Shape fromValue(String value) {
            return Stream.of(values()).filter(s -> s.getValue().equals(value)).findFirst().orElse(Random);
        }
    
        @Override
        public String toString() {
            return display;
        }
    }
    
}
