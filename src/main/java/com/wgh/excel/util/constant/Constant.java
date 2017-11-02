/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wgh.excel.util.constant;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;

/**
 * 常量
 * @author Administrator
 */
public interface Constant {

    /**
     * excel表头位置
     */
    short TABLE_HEADER_ALIGN = HSSFCellStyle.ALIGN_CENTER;
    /**
     * 表头背景颜色
     */
    short FILL_FORE_GROUND_COLOR = HSSFColor.GREY_50_PERCENT.index;
    /**
     * 表头背景颜色填充方式
     */
    short FILL_PATTERN = HSSFCellStyle.SOLID_FOREGROUND;
    /**
     * 表头字体大小
     */
    short FONT_HEIGHT = (short) 13;
    /**
     * 表头字体加粗
     */
    short FONT_WEIGHT = HSSFFont.BOLDWEIGHT_BOLD;
    /**
     * 表头字体类型
     */
    String FONT_NAME = "黑体";
    /**
     * 表头字体颜色
     */
    short FONT_COLOR = HSSFFont.COLOR_RED;

    /**
     * excel基本信息
     */
    /*
    标题
    */
    String TITLE = "title";
    /**
     * 列位置
     */
    String INDEX = "index";
    /**
     * 列宽
     */
    String WIDTH = "width";
    /**
     * 字段名
     */
    String FIELD_NAME = "fieldName";
    /**
     * 字段类型
     */
    String TYPE = "type";
    
    
    /**
     * 字段类型
     */
    String COL_STYLE = "colStyle";
    
    /**
     * 导入参数字段信息
     */
    String IMPORT_COL_PARAM = "importColParam";
    /**
     * 单个sheet最大行数
     */
    int EXPORT_MAX_NUM_FOR_ONE_SHEET = 60000;
    /**
     * 默认分页容量
     */
    int DEFAULT_PAGE_SIZE = 60000;
}
