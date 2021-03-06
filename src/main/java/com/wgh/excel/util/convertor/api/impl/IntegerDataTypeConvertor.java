/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wgh.excel.util.convertor.api.impl;

import com.wgh.excel.util.model.ColStyle;
import com.wgh.excel.util.utils.DataTypeUtils;
import org.apache.poi.ss.usermodel.Cell;

/**
 *
 * @author Administrator
 */
public class IntegerDataTypeConvertor extends AbstractDataTypeConvertor{
    @Override
    protected void doConvert(Cell cell, ColStyle colStyle, Object o) {
        Integer value = DataTypeUtils.getIntegerValue(o, colStyle.getDefaultValue());
        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
       if(value==null){
            cell.setCellValue("");
        }else{
            cell.setCellValue(value);
        }
        
    }
    
    
    
}
