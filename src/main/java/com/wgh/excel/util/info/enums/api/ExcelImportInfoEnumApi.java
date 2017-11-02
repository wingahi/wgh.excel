/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wgh.excel.util.info.enums.api;

import com.wgh.excel.util.model.ImportColParam;

/**
 * excel的基本信息枚举类需要实现这个接口
 * @author Administrator
 */
public interface ExcelImportInfoEnumApi {


    /**
     * 列号，必填
     *
     * @return
     */
    public Integer getIndex();

    /**
     * 导入excel列信息，必填
     *
     * @return
     */
    public ImportColParam getImportColParam();
}
