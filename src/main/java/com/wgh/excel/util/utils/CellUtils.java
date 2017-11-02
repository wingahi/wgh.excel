/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wgh.excel.util.utils;

import com.wgh.excel.util.model.ColStyle;
import java.lang.reflect.InvocationTargetException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Administrator
 */
public final class CellUtils {

    public static CellStyle getCellStyle(Workbook book, ColStyle colStyle) {
        CellStyle cellStyle = book.createCellStyle();

        if (colStyle.getAlign() != null) {
            cellStyle.setAlignment(colStyle.getAlign());
        }
        if (colStyle.getDataFormat() != null && colStyle.getDataFormat().length() > 0) {
            DataFormat format = book.createDataFormat();
            cellStyle.setDataFormat(format.getFormat(colStyle.getDataFormat()));
        }
        if (colStyle.getBgColor() != null) {
            cellStyle.setFillForegroundColor(colStyle.getBgColor());// 设置背景色  
        }
        if (colStyle.getFillPattern() != null) {
            cellStyle.setFillPattern(colStyle.getFillPattern());// 设置背景填充方式  
        }
        if (colStyle.getFont() != null) {
            cellStyle.setFont(colStyle.getFont());
        }
        return cellStyle;
    }

    public static void setValue(Cell cell, ColStyle colStyle, Object input) throws NoSuchMethodException,IllegalAccessException,IllegalAccessException,InvocationTargetException{
        if(colStyle.getCellStyle()!=null){
            cell.setCellStyle(colStyle.getCellStyle());
        }
        colStyle.getDataTypeConvertor().convert(cell, colStyle, input);
    }
}
