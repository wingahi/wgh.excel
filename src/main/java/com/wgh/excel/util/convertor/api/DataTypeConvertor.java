/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wgh.excel.util.convertor.api;

import com.wgh.excel.util.model.ColStyle;
import java.lang.reflect.InvocationTargetException;
import org.apache.poi.ss.usermodel.Cell;

/**
 *数据类型转换器
 * @author Administrator
 */
public interface DataTypeConvertor {
   
   public void convert(Cell cell,ColStyle colStyle,Object o) throws NoSuchMethodException,IllegalAccessException,IllegalAccessException,InvocationTargetException;
}
