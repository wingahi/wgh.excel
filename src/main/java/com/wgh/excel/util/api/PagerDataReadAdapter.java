/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wgh.excel.util.api;

import java.util.List;

/**
 * 内部数据分页读取适配器
 * @author Administrator
 */
public interface PagerDataReadAdapter<T> {
    /**
     * 分页读取数据
     * @param page
     * @param pageSize
     * @return 
     */
    public List<T> doReadData(int page,int pageSize);
}
