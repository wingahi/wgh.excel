/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wgh.excel.util.model;

import com.wgh.excel.util.adapter.AbstractPagerDataReadAdapter;
import com.wgh.excel.util.api.PagerDataReadAdapter;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;
import com.wgh.excel.util.info.enums.api.ExcelExportInfoEnumApi;
import java.util.Collections;

/**
 * excel导出参数实体
 *
 * @author Administrator
 * @param <T>
 */
public class ExcelExportParam<T extends ExcelExportInfoEnumApi> implements Serializable {

    /**
     * 表标题
     */
    private String title;
    /**
     * 导出的数据 1、bean实体类型 2、Map类型 3、Object[]类型 三种数据一起也可以使用，Object[]是按照下标来解析
     */
    private List data;
    /**
     * 返回数据输出流
     */
    private OutputStream os;
    /**
     * 转换的实体class 支持类型： 1、bean实体类型 2、Map类型===》Map.class 3、Object[]类型
     * Object[].class
     *
     * 如果三者混合，则bean实体类型【必填】，其余两种类型不必要
     */
    private Class<?> zclass;

    /**
     * 导出excel的基本信息
     *
     * @see TestEnumClass
     */
    private Class<T> excelInfoClazz;
    /**
     * 分页读取数据适配器
     */
    private AbstractPagerDataReadAdapter dataReadAdapter;
    
    public ExcelExportParam(String title, List data, OutputStream os, Class<?> zclass, Class<T> excelInfoClazz) {
        this.title = title;
        this.data = data;
        this.os = os;
        this.zclass = zclass;
        this.excelInfoClazz = excelInfoClazz;
    }

    public ExcelExportParam(String title,AbstractPagerDataReadAdapter dataReadAdapter, OutputStream os, Class<?> zclass, Class<T> excelInfoClazz) {
        this.title = title;
        this.dataReadAdapter = dataReadAdapter;
        this.os = os;
        this.zclass = zclass;
        this.excelInfoClazz = excelInfoClazz;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List getData() {
        if (data == null) {
            data = Collections.EMPTY_LIST;
        }
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public OutputStream getOs() {
        return os;
    }

    public void setOs(OutputStream os) {
        this.os = os;
    }

    public Class<?> getZclass() {
        return zclass;
    }

    public void setZclass(Class<?> zclass) {
        this.zclass = zclass;
    }

    public Class<?> getExcelInfoClazz() {
        return excelInfoClazz;
    }

    public void setExcelInfoClazz(Class<T> excelInfoClazz) {
        this.excelInfoClazz = excelInfoClazz;
    }

    public AbstractPagerDataReadAdapter getDataReadAdapter() {
        return dataReadAdapter;
    }

    public void setDataReadAdapter(AbstractPagerDataReadAdapter dataReadAdapter) {
        this.dataReadAdapter = dataReadAdapter;
    }
    
}
