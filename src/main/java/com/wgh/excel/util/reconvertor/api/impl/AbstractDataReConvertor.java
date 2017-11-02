/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wgh.excel.util.reconvertor.api.impl;

import com.wgh.excel.util.model.ImportColParam;
import com.wgh.excel.util.reconvertor.api.DataTypeReConvertor;
import com.wgh.excel.util.reconvertor.exception.ReConvertException;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author Administrator
 */
public abstract class AbstractDataReConvertor implements DataTypeReConvertor{
    
     @Override
    public Object convert(ImportColParam colParam, String o) throws NoSuchMethodException,IllegalAccessException,InvocationTargetException{
        if(o==null || o.equals("")){
            if(colParam.getRequired()){
                throw new ReConvertException(colParam, "数据转换出错，输入值为null或empty");
            }
            return colParam.getDefalutValue();
        }
        return reConverter(colParam,  o);
    }
    
    protected abstract Object reConverter(ImportColParam colParam, String o) throws NoSuchMethodException,IllegalAccessException,InvocationTargetException;
}
