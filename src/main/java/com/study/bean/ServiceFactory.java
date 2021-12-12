package com.study.bean;
 
import org.springframework.beans.factory.FactoryBean;
 
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
 
/**
 * @description:接口实例工厂，这里主要是用于提供接口的实例对象
 * @author: Lxq
 * @date: 2020/7/10 14:49
 */
public class ServiceFactory<T> implements FactoryBean {
 
    private Class<T> interfaceType;
 
    public ServiceFactory(Class<T> interfaceType) {
        this.interfaceType = interfaceType;
    }
 
 
    @Override
    public Object getObject() throws Exception {
        //这里主要是创建接口对应的实例，便于注入到spring容器中
        InvocationHandler handler = new ServiceProxy<>(interfaceType);
        return Proxy.newProxyInstance(interfaceType.getClassLoader(), new Class[]{interfaceType}, handler);
    }
 
    @Override
    public Class<?> getObjectType() {
        return interfaceType;
    }
}