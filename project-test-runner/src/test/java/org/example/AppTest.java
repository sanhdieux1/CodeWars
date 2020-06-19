package org.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @After
    public void teardown() throws IOException {
        File dir = new File("");
        File target = new File(dir.getAbsolutePath() + "/target/classes");
        if(target.exists()) {
            FileUtils.forceDelete(target);
        }
    }
    
    @Test
    public void shouldAnswerWithTrue() throws IOException {
        assertEquals("fromSureFire", System.getProperty("propertyValue"));
        
        Properties props = new Properties();
        InputStream stream2 = App.class.getClassLoader().getResourceAsStream("application_new.properties");
        assertNotNull(stream2);
        props.load(stream2);
        assertEquals("fromProperties", props.getProperty("applicationKey"));
        stream2.close();
    }
}
