package com.kail;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AppTestJson {
    @Test
    public void doTask() throws IOException {
        InputStream input = AppTestJson.class.getClassLoader().getResourceAsStream("TestsGoogleSheetCase.json");
        InputStream inputJava = AppTestJson.class.getClassLoader().getResourceAsStream("TestsJavaCode.json");
        ObjectMapper mapper = new ObjectMapper();
        Map<String, List<String>> testCases = mapper.readValue(input, Map.class);
        List<JavaTestCase> testCasesGoogle = testCases.entrySet().stream().map(JavaTestCase::new).collect(Collectors.toList());
        
        Map<String, List<String>> treeMap = mapper.readValue(inputJava, Map.class);
        List<JavaTestCase> testCasesJava = treeMap.entrySet().stream().map(JavaTestCase::new).collect(Collectors.toList());
        List<String> alltestCalseJava = testCasesJava.stream().map(JavaTestCase::getTestCases).map(List::stream).flatMap(Function.identity()).distinct().collect(Collectors.toList());
        List<JavaTestCase> conlai = testCasesGoogle.stream().peek(t -> {
            t.getTestCases().retainAll(alltestCalseJava);
        }).collect(Collectors.toList());
        
        System.out.println("size:" + conlai.stream().map(JavaTestCase::getTestCases).map(List::stream).flatMap(Function.identity()).distinct().count());
        System.out.println(mapper.writeValueAsString(conlai));
        
    }
    
    @Getter
    @Setter
    @EqualsAndHashCode
    public static class JavaTestCase{
        String testClass;
        List<String> testCases;
    
        public JavaTestCase(Map.Entry<String, List<String>> entry) {
            this.testClass = entry.getKey();
            this.testCases = entry.getValue().stream().collect(Collectors.toList());
        }
    }
    
    
    @Test
    public void testIgnore() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true)
                .configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true)
                .configure(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS, true)
                .configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, true)
                .configure(DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY, true)
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, true);
                
        MyJSONClass myClass = mapper.readValue("{\n" +
                "  \"name\" : \"aaa\"\n" +
                "}", MyJSONClass.class);
        Assert.assertEquals("aaa", myClass.getName());
    }
    
    @Data
    public static class MyJSONClass{
        private String name;
        private String age;
    }
}
