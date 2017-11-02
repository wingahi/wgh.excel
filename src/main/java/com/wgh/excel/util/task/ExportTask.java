/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wgh.excel.util.task;

import com.wgh.excel.util.ExcelUtils;
import com.wgh.excel.util.constant.Constant;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Administrator
 */
public class ExportTask extends RecursiveAction {

    private static final long serialVersionUID = -3611254198265061729L;
    public static final int THRESHOLD = Constant.EXPORT_MAX_NUM_FOR_ONE_SHEET;
    private int start;
    private int end;
    private final Workbook book;
    private final String title;
    private Sheet sheet;
    private final List dataList;
    private final String excelInfoClazzName;

    public ExportTask(final Workbook book, Sheet sheet, String title, List dataList, String excelInfoClazzName, int start, int end) {
        this.start = start;
        this.end = end;
        this.book = book;
        this.sheet = sheet;
        this.dataList = dataList;
        this.title = title;
        this.excelInfoClazzName = excelInfoClazzName;
    }

    /**
     * 获取总页数
     *
     * @param dataLen
     * @return
     */
    private void createSheet(int startIndex) {
        if (null != this.title && !"".equals(this.title)) {
            startIndex = startIndex-1;
            this.sheet = book.createSheet(this.title + (startIndex == 0 ? "" : startIndex));
        } else {
            this.sheet = book.createSheet();
        }
    }

    /**
     * 获取总页数
     *
     * @param dataLen
     * @return
     */
    private static int getTotalPage(int dataLen) {
        int page = dataLen / THRESHOLD;
        if (page * THRESHOLD < dataLen) {
            page++;
        }
        return page;
    }
    
    @Override
    protected void compute() {

        //如果任务足够小就计算任务
        boolean canCompute = (this.end - this.start) <= THRESHOLD;
        if (canCompute) {
            if (this.sheet == null) {
                createSheet(1);
            }
            try {
                ExcelUtils.Instance.INSTANCE.doExportExcel(this.sheet, this.excelInfoClazzName, this.book, this.dataList);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
                throw new RuntimeException(ex.getMessage());
            }

        } else {
            // 如果任务大于阈值，就分裂成子任务计算
            int page = getTotalPage(this.end);
            int dataSuffIndex;
            for (int index = 1; index <= page; index++) {
                createSheet(index);
                if(index==page){
                    dataSuffIndex = this.end;
                }else{
                    dataSuffIndex = index*THRESHOLD;
                }
                this.start = (index-1)*THRESHOLD;
                List subDataList = this.dataList.subList(this.start, dataSuffIndex);
                ExportTask exportTask = new ExportTask(this.book, sheet, this.title, subDataList, this.excelInfoClazzName, this.start, dataSuffIndex);
                exportTask.invoke();
            }
        }
    }
}
