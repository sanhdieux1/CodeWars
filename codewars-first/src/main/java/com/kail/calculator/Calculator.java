package com.kail.calculator;

import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Calculator {
    // 2 + 2 * 4 + 5
    public static Double evaluate(String expression) {
        LinkedList<String> expressionList = Stream.of(expression.split(" ")).collect(Collectors.toCollection(LinkedList::new));
        SingleMatch singleMatch = getMatches(expressionList);
        return singleMatch.caculate();
    }
    public static SingleMatch getMatches(LinkedList<String> expressionList){
        SingleMatch singleMatch = new SingleMatch();
        while (!expressionList.isEmpty()) {
            String singleExpression = expressionList.pop();
            if(isOperator(singleExpression)){
                if(isHighestPriority(singleExpression)){
                    String right = expressionList.pop();
                    singleMatch.setLeftSide(calculate(singleMatch.getLeftSide(), singleExpression, Double.valueOf(right)));
                    if(expressionList.isEmpty()){
                        singleMatch.setValue(singleMatch.getLeftSide());
                    }
                    continue;
                }
                singleMatch.setOperator(singleExpression);
                
                if(expressionList.size() > 1) {
                    singleMatch.setRighSide(getMatches("-".equals(singleExpression) ? reversedOperators(expressionList) : expressionList));
                } else {
                    singleMatch.setRighSide(new SingleMatch(Double.valueOf(expressionList.pop())));
                }
                
                break;
            }
            singleMatch.setLeftSide(Double.valueOf(singleExpression));
            if(expressionList.isEmpty()){
                singleMatch.setValue(singleMatch.getLeftSide());
            }
        }
        return singleMatch;
    }
    
    public static LinkedList<String> reversedOperators(LinkedList<String> input) {
        return input.stream().map(o -> {
            if (isOperator(o)) {
                if ("+".equals(o)) {
                    return "-";
                }
                if ("-".equals(o)) {
                    return "+";
                }
            }
            return o;
        }).collect(Collectors.toCollection(LinkedList::new));
    }
    
    public static boolean isOperator(String expression) {
        return "*".equals(expression) || "/".equals(expression) || "+".equals(expression) || "-".equals(expression);
    }
    
    public static boolean isHighestPriority(String expression){
        return "*".equals(expression) || "/".equals(expression);
    }
    public static double calculate(Double left, String operator, Double right){
        switch (operator) {
            case "*" : return left * right;
            case "/" : return left / right;
            case "+" : return left + right;
            case "-" : return left - right;
            default:
                return 0;
        }
    }
    public static class SingleMatch {
        private String operator;
        private Double leftSide;
        private SingleMatch righSide;
        private Double value;
    
        public SingleMatch() {
        }
    
        public SingleMatch(Double value) {
            this.value = value;
        }
    
        public String getOperator() {
            return operator;
        }
    
        public void setOperator(String operator) {
            this.operator = operator;
        }
    
        public Double getValue() {
            return value;
        }
    
        public void setValue(Double value) {
            this.value = value;
        }
    
        public Double getLeftSide() {
            return leftSide;
        }
    
        public void setLeftSide(Double leftSide) {
            this.leftSide = leftSide;
        }
    
        public SingleMatch getRighSide() {
            return righSide;
        }
    
        public void setRighSide(SingleMatch righSide) {
            this.righSide = righSide;
        }
    
        public double caculate(){
            if(value != null) {
                return value;
            }
            return calculate(leftSide, operator, righSide.caculate());
        }
    }
}
