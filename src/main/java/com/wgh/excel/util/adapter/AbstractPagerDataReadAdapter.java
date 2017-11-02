/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wgh.excel.util.adapter;

import com.wgh.excel.util.api.PagerDataReadAdapter;

/**
 *
 * @author Administrator
 */
public abstract class AbstractPagerDataReadAdapter implements PagerDataReadAdapter{
    /**
     * 当前页码
     */
    private int curPage=1;
    /**
     * 参数
     */
    private Object params;

    public AbstractPagerDataReadAdapter(Object params) {
        this.params = params;
    }

    public int getCurPage() {
        return curPage;
    }
    
    public int nextPage() {
        this.curPage++;
        return this.curPage;
    }

    public Object getParams() {
        return params;
    } 
}
