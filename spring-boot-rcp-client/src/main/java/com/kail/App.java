package com.kail;

import com.googlecode.jsonrpc4j.IJsonRpcClient;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.ProxyUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        AccessActivityLoggingService service = getService(AccessActivityLoggingService.class);
    }

    public static <T> T getService(Class<T> serviceClass) {
        IJsonRpcClient proxiedClient = (IJsonRpcClient) Proxy.newProxyInstance(App.class.getClassLoader(), new Class[]{IJsonRpcClient.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                JsonRpcHttpClient client = new JsonRpcHttpClient(new URL("localhost:7070/logger/api.json"));
                try {
                    return method.invoke(client, args);
                } catch (InvocationTargetException ite) {
                    throw ite.getCause();
                }
            }
        });

        return ProxyUtil.createClientProxy(App.class.getClassLoader(), serviceClass, proxiedClient);
    }
}
