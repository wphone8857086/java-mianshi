package com.study.classloader;

import com.study.entity.Student;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Driver;
import java.util.Arrays;
import java.util.List;
import java.util.ServiceLoader;

public class MyClassLoader {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        /*
        第三方类库使用SPI加载机制
        PREFIX = "META-INF/services/";
        String fullName = PREFIX + service.getName();
        configs = loader.getResources(fullName);
        主动设置当前线程类加载器
        Thread.currentThread().setContextClassLoader(ClassLoader.getSystemClassLoader());
         */

        ServiceLoader<Driver> loadedDrivers = ServiceLoader.load(Driver.class);
        loadedDrivers.forEach((t) -> {
            System.out.println(t);
        });

        /*
        第二个参数进行初始化
        return forName0(className, true, ClassLoader.getClassLoader(caller), caller);
         */
        Class<?> aClass = Class.forName("com.mysql.jdbc.Driver");

        /*
         自定义类加载器
         重写loadClass方法，打破双亲委派
         */
        DefinedClassLoaderTest classLoader = new DefinedClassLoaderTest("F:/study/Project/java-mianshi/target/classes");
        Class<?> clazz = classLoader.loadClass("com.study.entity.Student");
        Object obj = clazz.newInstance();
        Method sout = clazz.getDeclaredMethod("setName",String.class);
        sout.invoke(obj, "小明");
        System.out.println(clazz.getClassLoader());
        System.out.println(obj.toString());


        Class<?> student = Class.forName("com.study.entity.Student");
        Object o = student.newInstance();
        Method setName = student.getDeclaredMethod("setName", String.class);
        setName.invoke(o, "张三");
        System.out.println(o);



//        bootstrapClassLoader();
//        extClassLoader();
//        appClassLoader();

    }



    /**
     * 启动类加载器的职责
     */
    public static void bootstrapClassLoader() {
        String property = System.getProperty("sun.boot.class.path");
        List<String> list = Arrays.asList(property.split(";"));
        list.forEach((t) -> {
            System.out.println("启动类加载器目录:" + t);
        });
    }


    /**
     * 扩展类加载器
     */
    public static void extClassLoader() {
        String property = System.getProperty("java.ext.dirs");
        List<String> list = Arrays.asList(property.split(";"));
        list.forEach((t) -> {
            System.out.println("扩展类加载器" + t);
        });
    }

    /**
     * app 类加载器
     */
    public static void appClassLoader() {
        String property = System.getProperty("java.class.path");
        List<String> list = Arrays.asList(property.split(";"));
        list.forEach((t) -> {
            System.out.println("应用类加载器" + t);
        });
    }
}
