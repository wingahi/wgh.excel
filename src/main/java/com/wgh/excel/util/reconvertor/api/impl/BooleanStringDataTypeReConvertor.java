/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wgh.excel.util.reconvertor.api.impl;

import com.wgh.excel.util.model.ImportColParam;
import java.util.Arrays;

/**
 *
 * @author Administrator
 */
public class BooleanStringDataTypeReConvertor extends AbstractDataReConvertor {

    @Override
    protected Object reConverter(ImportColParam colParam, String o) {
        return Arrays.asList("true","是","1","yes","YES","y","Y","Yes").contains(o);
    }
}

