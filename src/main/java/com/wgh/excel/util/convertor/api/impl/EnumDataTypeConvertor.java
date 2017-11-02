/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wgh.excel.util.convertor.api.impl;

import com.wgh.excel.util.model.ColStyle;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.poi.ss.usermodel.Cell;

/**
 *
 * @author Administrator
 */
public class EnumDataTypeConvertor extends AbstractDataTypeConvertor{

    /**
     * 字段用到相应的枚举类：name-description
     */
    protected final static ConcurrentMap<String, ConcurrentMap<String, String>> ENUM_CLASS_MAP = new ConcurrentHashMap<String, ConcurrentMap<String, String>>();
    //枚举描述方法名称
    protected final static String DES_METHOD_NAME = "getDescription";

    @Override
    public void doConvert(Cell cell, ColStyle colStyle, Object o) throws NoSuchMethodException,IllegalAccessException,IllegalAccessException,InvocationTargetException {
        Class clazz = colStyle.getType();
        ConcurrentMap<String, String> enumMaps = ENUM_CLASS_MAP.get(clazz.getName());
        if (enumMaps == null) {
            enumMaps = new ConcurrentHashMap<String, String>();
            for (Object enumItem : clazz.getEnumConstants()) {
                enumMaps.put(enumItem.toString(), enumItem.getClass().getMethod(DES_METHOD_NAME).invoke(enumItem, new Object[0]).toString());
            }
            ENUM_CLASS_MAP.put(clazz.getName(), enumMaps);
        }
        if (o != null) {
            cell.setCellValue(enumMaps.get(o.toString()));
        } else {
            if (colStyle.getDefaultValue() != null) {
                cell.setCellValue(enumMaps.get(colStyle.getDefaultValue().toString()));
            } else {
                cell.setCellValue("");
            }
        }
    }
}
