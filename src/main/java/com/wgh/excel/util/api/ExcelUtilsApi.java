/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wgh.excel.util.api;

import com.wgh.excel.util.model.ExcelExportParam;
import com.wgh.excel.util.model.ExcelImportParam;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface ExcelUtilsApi {
    /**
     * 导入
     * @param importParam
     * @return
     * @throws IOException
     * @throws Exception 
     * @return 异常则返回null
     */
    public List importExcel(ExcelImportParam importParam) throws IOException, Exception;
    /**
     * 导出excel，外部调用导出excel方法
     *
     * @param excelParam 传入方法
     * @throws Exception
     */
    public void  exportExcel(ExcelExportParam excelParam) throws Exception;
}
