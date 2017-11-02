/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wgh.excel.util.reconvertor.api.impl;
import com.wgh.excel.util.model.ImportColParam;
import org.apache.poi.ss.usermodel.DateUtil;

/**
 *
 * @author Administrator
 */
public class DateDataTypeReConvertor extends AbstractDataReConvertor {

    @Override
    protected Object reConverter(ImportColParam colParam, String o) {
        return DateUtil.getJavaDate(Double.parseDouble(o));
    }
}