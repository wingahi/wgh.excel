/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wgh.excel.util.info.enums.api;

import com.wgh.excel.util.model.ColStyle;

/**
 * excel的基本信息枚举类需要实现这个接口
 * @author Administrator
 */
public interface ExcelExportInfoEnumApi {

    /**
     * 字段中文名，必填
     *
     * @return
     */
    public String getTitle();

    /**
     * 列号，可选
     *
     * @return
     */
    default Integer getIndex(){
        return null;
    }

    /**
     * 列宽，单位宽度为50像素，width=2,即为100像素，必填
     *
     * @return
     */
    public Integer getWidth();

    /**
     * 数据列的样式，必填
     *
     * @return
     */
    public ColStyle getColStyle();
}
