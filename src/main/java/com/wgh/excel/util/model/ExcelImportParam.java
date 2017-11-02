/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wgh.excel.util.model;

import com.wgh.excel.util.info.enums.api.ExcelImportInfoEnumApi;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Map;

/**
 * excel导出参数实体
 *
 * @author Administrator
 */
public class ExcelImportParam<T extends ExcelImportInfoEnumApi> implements Serializable {

    /**
     * 导入excel信息枚举
     */
    private Class<T> paramEnumClass;
    /**
     * 返回数据输入流
     */
    private InputStream ins;
    /**
     * 转换为实体class 只支持bean实体类型
     */
    private Class<?> clazz;

    public ExcelImportParam(InputStream ins, Class<T> paramEnumClass, Class<?> clazz) {
        this.paramEnumClass = paramEnumClass;
        this.ins = ins;
        this.clazz = clazz;
    }

    public InputStream getIns() {
        return ins;
    }

    public void setIns(InputStream ins) {
        this.ins = ins;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Class<T> getParamEnumClass() {
        return paramEnumClass;
    }

    public void setParamEnumClass(Class<T> paramEnumClass) {
        this.paramEnumClass = paramEnumClass;
    }

}
