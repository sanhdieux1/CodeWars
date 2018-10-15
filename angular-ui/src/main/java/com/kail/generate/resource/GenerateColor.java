package com.kail.generate.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Configuration
@ComponentScan(value={"com.kail"})

public class GenerateColor {
    public static final Logger logger = Logger.getLogger(GenerateColor.class.getName());
    @Autowired
    JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(GenerateColor.class);;
        System.out.println("hello");
        try {
            File file = new File("colours.ts");
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(buildColor());
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "can't generate file folours.ts");
        }
        context.close();
    }

    public static String buildColor(){
        return "$main-color: #000";
    }


}
