/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wgh.excel.util.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public class ReflectUtils {
    public static Object newInstance(Class<?> cls) {
        try {
            return cls.newInstance();
        } catch (Throwable t) {
            try {
                Constructor<?>[] constructors = cls.getConstructors();
                if (constructors != null && constructors.length == 0) {
                    throw new RuntimeException("Illegal constructor: " + cls.getName());
                }
                Constructor<?> constructor = constructors[0];
                if (constructor.getParameterTypes().length > 0) {
                    for (Constructor<?> c : constructors) {
                        if (c.getParameterTypes().length < 
                                constructor.getParameterTypes().length) {
                            constructor = c;
                            if (constructor.getParameterTypes().length == 0) {
                                break;
                            }
                        }
                    }
                }
                return constructor.newInstance(new Object[constructor.getParameterTypes().length]);
            } catch (InstantiationException e) {
                throw new RuntimeException(e.getMessage(), e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e.getMessage(), e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
    }
    
     public static boolean isBeanPropertyReadMethod(Method method) {
        return method != null
            && Modifier.isPublic(method.getModifiers())
            && ! Modifier.isStatic(method.getModifiers())
            && method.getReturnType() != void.class
            && method.getDeclaringClass() != Object.class
            && method.getParameterTypes().length == 0
            && ((method.getName().startsWith("get") && method.getName().length() > 3)
                    || (method.getName().startsWith("is") && method.getName().length() > 2));
    }
    
    public static boolean isBeanPropertyWriteMethod(Method method) {
        return method != null
            && Modifier.isPublic(method.getModifiers())
            && ! Modifier.isStatic(method.getModifiers())
            && method.getDeclaringClass() != Object.class
            && method.getParameterTypes().length == 1
            && method.getName().startsWith("set")
            && method.getName().length() > 3;
    }
    
    public static String getPropertyNameFromBeanReadMethod(Method method) {
        if (isBeanPropertyReadMethod(method)) {
            if (method.getName().startsWith("get")) {
                return method.getName().substring(3, 4).toLowerCase()
                    + method.getName().substring(4);
            }
            if (method.getName().startsWith("is")) {
                return method.getName().substring(2, 3).toLowerCase()
                    + method.getName().substring(3);
            }
        }
        return null;
    }

    public static String getPropertyNameFromBeanWriteMethod(Method method) {
        if (isBeanPropertyWriteMethod(method)) {
            return method.getName().substring(3, 4).toLowerCase()
                + method.getName().substring(4);
        }
        return null;
    }
    
    public static Map<String, Method> getBeanPropertyReadMethods(Class cl) {
        Map<String, Method> properties = new HashMap<String, Method>();
        for(; cl != null; cl = cl.getSuperclass()) {
            Method[] methods = cl.getDeclaredMethods();
            for(Method method : methods) {
                if (isBeanPropertyReadMethod(method)) {
                    method.setAccessible(true);
                    String property = getPropertyNameFromBeanReadMethod(method);
                    properties.put(property, method);
                }
            }
        }

        return properties;
    }
    
    public static Map<String, Method> getBeanPropertyWriteMethods(Class cl) {
        Map<String, Method> properties = new HashMap<String, Method>();
        for(; cl != null; cl = cl.getSuperclass()) {
            Method[] methods = cl.getDeclaredMethods();
            for(Method method : methods) {
                if (isBeanPropertyWriteMethod(method)) {
                    method.setAccessible(true);
                    String property = getPropertyNameFromBeanWriteMethod(method);
                    properties.put(property, method);
                }
            }
        }

        return properties;
    }
}
