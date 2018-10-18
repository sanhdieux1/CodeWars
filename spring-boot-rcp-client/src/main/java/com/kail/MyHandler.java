package com.kail;

import com.googlecode.jsonrpc4j.IJsonRpcClient;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.ProxyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URL;

@Service
public class MyHandler {

    @Autowired
    JsonRpcHttpClient jsonRpcHttpClient;

    public <T> T getService(Class<T> serviceClass) {
        IJsonRpcClient proxiedClient = (IJsonRpcClient) Proxy.newProxyInstance(App.class.getClassLoader(), new Class[]{IJsonRpcClient.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                JsonRpcHttpClient client = new JsonRpcHttpClient(new URL("http://localhost:7070/test-spring-boot/logger/api.json"));
                try {
                    return method.invoke(client, args);
                } catch (InvocationTargetException ite) {
                    throw ite.getCause();
                }
            }
        });

        return ProxyUtil.createClientProxy(App.class.getClassLoader(), serviceClass, proxiedClient);
    }

    public <T> T exampleClientAPI(Class<T> serviceClass) {
        return ProxyUtil.createClientProxy(getClass().getClassLoader(), serviceClass, jsonRpcHttpClient);
    }
}
