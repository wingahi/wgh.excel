/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wgh.excel.util.reconvertor.api;

import com.wgh.excel.util.model.ImportColParam;
import java.lang.reflect.InvocationTargetException;

/**
 *数据类型转换器
 * @author Administrator
 */
public interface DataTypeReConvertor {
   
   public Object convert(ImportColParam colParam,String o) throws NoSuchMethodException,IllegalAccessException,IllegalAccessException,InvocationTargetException;
}
