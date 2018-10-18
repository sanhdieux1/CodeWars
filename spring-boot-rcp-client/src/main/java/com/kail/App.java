package com.kail;

import com.googlecode.jsonrpc4j.IJsonRpcClient;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.ProxyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

/**
 * Hello world!
 *
 */
@Configuration
@ComponentScan(value = {"com.kail"})
public class App 
{

    @Bean
    public JsonRpcHttpClient jsonRpcHttpClient() {
        URL url = null;
        //You can add authentication headers etc to this map
        Map<String, String> map = new HashMap<>();
        try {
            url = new URL("http://localhost:7070/test-spring-boot/logger/api.json");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new JsonRpcHttpClient(url, map);
    }

    public static void main( String[] args )
    {

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        MyHandler handler = context.getBean(MyHandler.class);
        AccessActivityLoggingService service = handler.getService(AccessActivityLoggingService.class);
        AccessActivityData data = new AccessActivityData();
        data.setFunctionName("test.rcp");
        data.setBrandName("fairexchange");
        data.setDescription("Test RCP");
        data.setInitGroupName("ABC");
        data.setFunctionParams("abc");
        System.out.println(service.getMessage());
    }


}
