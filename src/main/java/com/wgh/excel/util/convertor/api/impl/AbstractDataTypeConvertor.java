/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wgh.excel.util.convertor.api.impl;

import com.wgh.excel.util.convertor.api.DataTypeConvertor;
import com.wgh.excel.util.convertor.exception.ConvertException;
import com.wgh.excel.util.model.ColStyle;
import java.lang.reflect.InvocationTargetException;
import org.apache.poi.ss.usermodel.Cell;

/**
 *
 * @author Administrator
 */
public abstract class AbstractDataTypeConvertor implements DataTypeConvertor {

    @Override
    public void convert(Cell cell, ColStyle colStyle, Object o) throws NoSuchMethodException,IllegalAccessException,InvocationTargetException {
        if (o == null && colStyle.getRequired()) {
            throw new ConvertException(colStyle, "数据转换出错，输出值为："+o);
        }
        doConvert(cell, colStyle, o);
    }

    protected abstract void doConvert(Cell cell, ColStyle colStyle, Object o) throws NoSuchMethodException,IllegalAccessException,InvocationTargetException;
}
