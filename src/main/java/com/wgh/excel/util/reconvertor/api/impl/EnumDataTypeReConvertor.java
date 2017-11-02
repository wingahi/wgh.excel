/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wgh.excel.util.reconvertor.api.impl;

import com.wgh.excel.util.model.ImportColParam;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class EnumDataTypeReConvertor extends AbstractDataReConvertor {

    /**
     * 字段用到相应的枚举类:description-name
     */
    protected final static ConcurrentMap<String, ConcurrentMap<String, Object>> ENUM_NAME_MAP = new ConcurrentHashMap<String, ConcurrentMap<String, Object>>();
    //枚举描述方法名称
    protected final static String DES_METHOD_NAME = "getDescription";

    /**
     *
     * @param colParam
     * @param o
     * @return
     * @throws java.lang.NoSuchMethodException
     * @throws java.lang.IllegalAccessException
     * @throws java.lang.reflect.InvocationTargetException
     */
    @Override
    protected Object reConverter(ImportColParam colParam, String o) throws NoSuchMethodException,IllegalAccessException,InvocationTargetException{

        Class clazz = colParam.getClazz();
        ConcurrentMap<String, Object> enumNameMaps = ENUM_NAME_MAP.get(clazz.getName());
        if (enumNameMaps == null) {
            enumNameMaps = new ConcurrentHashMap<String, Object>();
            for (Object constant : clazz.getEnumConstants()) {
                enumNameMaps.put(constant.getClass().getMethod(DES_METHOD_NAME).invoke(constant, new Object[0]).toString(), constant);
            }
            ENUM_NAME_MAP.put(clazz.getName(), enumNameMaps);
        }
        return enumNameMaps.get(o);
    }
}
