/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wgh.excel.util.model;

import com.wgh.excel.util.convertor.api.DataTypeConvertor;
import com.wgh.excel.util.convertor.api.impl.BooleanDataTypeConvertor;
import com.wgh.excel.util.convertor.api.impl.DateDataTypeConvertor;
import com.wgh.excel.util.convertor.api.impl.DoubleDataTypeConvertor;
import com.wgh.excel.util.convertor.api.impl.EnumDataTypeConvertor;
import com.wgh.excel.util.convertor.api.impl.FloatDataTypeConvertor;
import com.wgh.excel.util.convertor.api.impl.IntegerDataTypeConvertor;
import com.wgh.excel.util.convertor.api.impl.LongDataTypeConvertor;
import com.wgh.excel.util.convertor.api.impl.ShortDataTypeConvertor;
import com.wgh.excel.util.convertor.api.impl.StringDataTypeConvertor;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

/**
 * 导出列样式
 *
 * @author Administrator
 */
public class ColStyle<T> {

    /**
     * 默认：类型与位置关系
     */
    private final static Map<String, Short> TYPE_ALIGNS = new HashMap<String, Short>();
    /**
     * 默认：类型与转换器关系
     */
    private final static Map<String, DataTypeConvertor> TYPE_CONVERTORS = new HashMap<String, DataTypeConvertor>();

    static {
        TYPE_ALIGNS.put(Integer.class.getName(), CellStyle.ALIGN_RIGHT);
        TYPE_ALIGNS.put(Short.class.getName(), CellStyle.ALIGN_RIGHT);
        TYPE_ALIGNS.put(Long.class.getName(), CellStyle.ALIGN_RIGHT);
        TYPE_ALIGNS.put(String.class.getName(), CellStyle.ALIGN_LEFT);
        TYPE_ALIGNS.put(Boolean.class.getName(), CellStyle.ALIGN_CENTER);
        TYPE_ALIGNS.put(Float.class.getName(), CellStyle.ALIGN_RIGHT);
        TYPE_ALIGNS.put(Double.class.getName(), CellStyle.ALIGN_RIGHT);
        TYPE_ALIGNS.put(Date.class.getName(), CellStyle.ALIGN_CENTER);
        TYPE_ALIGNS.put(Date.class.getName(), CellStyle.ALIGN_CENTER);
        TYPE_ALIGNS.put(Enum.class.getName(), CellStyle.ALIGN_CENTER);

        TYPE_ALIGNS.put(int.class.getName(), CellStyle.ALIGN_RIGHT);
        TYPE_ALIGNS.put(short.class.getName(), CellStyle.ALIGN_RIGHT);
        TYPE_ALIGNS.put(long.class.getName(), CellStyle.ALIGN_RIGHT);
        TYPE_ALIGNS.put(boolean.class.getName(), CellStyle.ALIGN_CENTER);
        TYPE_ALIGNS.put(float.class.getName(), CellStyle.ALIGN_RIGHT);
        TYPE_ALIGNS.put(double.class.getName(), CellStyle.ALIGN_RIGHT);

        TYPE_CONVERTORS.put(Integer.class.getName(), new IntegerDataTypeConvertor());
        TYPE_CONVERTORS.put(Short.class.getName(), new ShortDataTypeConvertor());
        TYPE_CONVERTORS.put(Long.class.getName(), new LongDataTypeConvertor());
        TYPE_CONVERTORS.put(String.class.getName(), new StringDataTypeConvertor());
        TYPE_CONVERTORS.put(Boolean.class.getName(), new BooleanDataTypeConvertor());
        TYPE_CONVERTORS.put(Float.class.getName(), new FloatDataTypeConvertor());
        TYPE_CONVERTORS.put(Double.class.getName(), new DoubleDataTypeConvertor());
        TYPE_CONVERTORS.put(Date.class.getName(), new DateDataTypeConvertor());
        TYPE_CONVERTORS.put(Enum.class.getName(), new EnumDataTypeConvertor());

        TYPE_CONVERTORS.put(int.class.getName(), new IntegerDataTypeConvertor());
        TYPE_CONVERTORS.put(short.class.getName(), new ShortDataTypeConvertor());
        TYPE_CONVERTORS.put(long.class.getName(), new LongDataTypeConvertor());
        TYPE_CONVERTORS.put(boolean.class.getName(), new BooleanDataTypeConvertor());
        TYPE_CONVERTORS.put(float.class.getName(), new FloatDataTypeConvertor());
        TYPE_CONVERTORS.put(double.class.getName(), new DoubleDataTypeConvertor());
    }

    private String fieldName;
    private String dataFormat;
    private T defaultValue;
    private Class type;
    private Short align;
    private Short bgColor;
    private Short fillPattern;
    private Font font;
    private boolean required;
    /**
     * 数据转换器
     */
    private DataTypeConvertor dataTypeConvertor;

    private CellStyle cellStyle;

    public CellStyle getCellStyle() {
        return cellStyle;
    }

    public void setCellStyle(CellStyle cellStyle) {
        this.cellStyle = cellStyle;
    }
    
    /**
     *
     * @param fieldName 字段英文名
     * @param dataFormat 数据格式 ，如日期：yyyy-MM-dd hh:mm:ss ，浮点数：###0.00
     * @param defaultValue 默认值
     * @param type 字段类型
     * @param align 位置， 如：CellStyle.ALIGN_CENTER ，CellStyle.ALIGN_RIGHT
     * @param dataTypeConvertor 字段值转换器
     * @param required 是否必要参数，true=必要，false=不必要（为null则取默认值）
     */
    @Deprecated
    public ColStyle(String fieldName, String dataFormat, T defaultValue, Class type, Short align, DataTypeConvertor dataTypeConvertor, boolean required) {
        this( dataFormat, defaultValue, type, align, null, null, null, dataTypeConvertor, required);
    }

    /**
     *
     * @param fieldName 字段英文名
     * @param dataFormat 数据格式 ，如日期：yyyy-MM-dd hh:mm:ss ，浮点数：###0.00
     * @param defaultValue 默认值
     * @param type 字段类型
     * @param align 位置， 如：CellStyle.ALIGN_CENTER ，CellStyle.ALIGN_RIGHT
     * @param dataTypeConvertor 字段值转换器
     */
    @Deprecated
    public ColStyle(String fieldName, String dataFormat, T defaultValue, Class type, Short align, DataTypeConvertor dataTypeConvertor) {
        this(dataFormat, defaultValue, type, align, null, null, null, dataTypeConvertor, false);
    }
    /**
     *
     * @param fieldName 字段英文名
     * @param dataFormat 数据格式 ，如日期：yyyy-MM-dd hh:mm:ss ，浮点数：###0.00
     * @param defaultValue 默认值
     * @param type 字段类型
     */
    @Deprecated
    public ColStyle(String fieldName, String dataFormat, T defaultValue, Class type) {
        this(fieldName, dataFormat, defaultValue, type, false);
    }

     /**
     *
     * @param fieldName 字段英文名
     * @param dataFormat 数据格式 ，如日期：yyyy-MM-dd hh:mm:ss ，浮点数：###0.00
     * @param defaultValue 默认值
     * @param type 字段类型
     * @param required 是否必要参数，true=必要，false=不必要（为null则取默认值）
     */
    @Deprecated
    public ColStyle(String fieldName, String dataFormat, T defaultValue, Class type,boolean required) {
        this(dataFormat, defaultValue, type, null, null, null, null, null, required);
    }
    
    /**
     *
     * @param fieldName 字段英文名
     * @param dataFormat 数据格式 ，如日期：yyyy-MM-dd hh:mm:ss ，浮点数：###0.00
     * @param defaultValue 默认值
     * @param dataTypeConvertor 字段值转换器
     *  @param required 是否必要参数，true=必要，false=不必要（为null则取默认值）
     */
    @Deprecated
    public ColStyle(String fieldName, String dataFormat, T defaultValue, DataTypeConvertor dataTypeConvertor, boolean required) {
        this(dataFormat, defaultValue, null, null, null, null, null, dataTypeConvertor, required);
    }

    
    /**
     * @param dataFormat 数据格式 ，如日期：yyyy-MM-dd hh:mm:ss ，浮点数：###0.00
     * @param defaultValue 默认值
     * @param type 字段类型
     * @param align 位置， 如：CellStyle.ALIGN_CENTER ，CellStyle.ALIGN_RIGHT
     * @param dataTypeConvertor 字段值转换器
     * @param required 是否必要参数，true=必要，false=不必要（为null则取默认值）
     */
    public ColStyle(String dataFormat, T defaultValue, Class type, Short align, DataTypeConvertor dataTypeConvertor, boolean required) {
        this( dataFormat, defaultValue, type, align, null, null, null, dataTypeConvertor, required);
    }

    /**
     * @param dataFormat 数据格式 ，如日期：yyyy-MM-dd hh:mm:ss ，浮点数：###0.00
     * @param defaultValue 默认值
     * @param type 字段类型
     * @param align 位置， 如：CellStyle.ALIGN_CENTER ，CellStyle.ALIGN_RIGHT
     * @param dataTypeConvertor 字段值转换器
     */
    public ColStyle(String dataFormat, T defaultValue, Class type, Short align, DataTypeConvertor dataTypeConvertor) {
        this(dataFormat, defaultValue, type, align, null, null, null, dataTypeConvertor, false);
    }
    /**
     * @param dataFormat 数据格式 ，如日期：yyyy-MM-dd hh:mm:ss ，浮点数：###0.00
     * @param defaultValue 默认值
     * @param type 字段类型
     */
    public ColStyle( String dataFormat, T defaultValue, Class type) {
        this(dataFormat, defaultValue, type, false);
    }

     /**
     * @param dataFormat 数据格式 ，如日期：yyyy-MM-dd hh:mm:ss ，浮点数：###0.00
     * @param defaultValue 默认值
     * @param type 字段类型
     * @param required 是否必要参数，true=必要，false=不必要（为null则取默认值）
     */
    public ColStyle( String dataFormat, T defaultValue, Class type,boolean required) {
        this(dataFormat, defaultValue, type, null, null, null, null, null, required);
    }
    
    /**
     * @param dataFormat 数据格式 ，如日期：yyyy-MM-dd hh:mm:ss ，浮点数：###0.00
     * @param defaultValue 默认值
     * @param dataTypeConvertor 字段值转换器
     *  @param required 是否必要参数，true=必要，false=不必要（为null则取默认值）
     */
    public ColStyle( String dataFormat, T defaultValue, DataTypeConvertor dataTypeConvertor, boolean required) {
        this(dataFormat, defaultValue, null, null, null, null, null, dataTypeConvertor, required);
    }
    
    /**
     *
     * @param fieldName 字段英文名
     * @param dataFormat 数据格式 ，如日期：yyyy-MM-dd hh:mm:ss ，浮点数：###0.00
     * @param defaultValue 默认值
     * @param type 字段类型
     * @param align 位置， 如：CellStyle.ALIGN_CENTER ，CellStyle.ALIGN_RIGHT
     * ，CellStyle.ALIGN_LEFT
     * @param bgColor 背景颜色，short类型 ，可使用枚举HSSFColor.GREY_50_PERCENT.index
     * @param fillPattern 背景填充方式
     * @param font 字体
     * @param dataTypeConvertor 字段值转换器
     *  @param required 是否必要参数，true=必要，false=不必要（为null则取默认值）
     */
    public ColStyle( String dataFormat, T defaultValue, Class type, Short align, Short bgColor, Short fillPattern, Font font, DataTypeConvertor dataTypeConvertor, boolean required) {
        this.dataFormat = dataFormat;
        this.defaultValue = defaultValue;
        if (type == null) {
            throw new RuntimeException("字段type必填");
        }
        this.type = type;
        this.align = align;
        if (this.align == null) {
            if (Enum.class.isAssignableFrom(this.type)) {
                this.align = TYPE_ALIGNS.get(Enum.class.getName());
            } else {
                this.align = TYPE_ALIGNS.get(this.type.getName());
            }

        }
        this.bgColor = bgColor;
        this.fillPattern = fillPattern;
        this.font = font;
        this.dataTypeConvertor = dataTypeConvertor;
        if (this.dataTypeConvertor == null) {
            if (Enum.class.isAssignableFrom(this.type)) {
                this.dataTypeConvertor = TYPE_CONVERTORS.get(Enum.class.getName());
            } else {
                this.dataTypeConvertor = TYPE_CONVERTORS.get(this.type.getName());
            }
        }
        this.required = required;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getDataFormat() {
        return dataFormat;
    }

    public void setDataFormat(String dataFormat) {
        this.dataFormat = dataFormat;
    }

    public T getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(T defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public Short getAlign() {
        return align;
    }

    public void setAlign(Short align) {
        this.align = align;
    }

    public Short getBgColor() {
        return bgColor;
    }

    public void setColor(Short bgColor) {
        this.bgColor = bgColor;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Short getFillPattern() {
        return fillPattern;
    }

    public void setFillPattern(Short fillPattern) {
        this.fillPattern = fillPattern;
    }

    public DataTypeConvertor getDataTypeConvertor() {
        return dataTypeConvertor;
    }

    public void setDataTypeConvertor(DataTypeConvertor dataTypeConvertor) {
        this.dataTypeConvertor = dataTypeConvertor;
    }

    public boolean getRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    @Override
    public String toString() {
        return "ColStyle{" + "fieldName=" + fieldName + ", type=" + type + ", required=" + required + '}';
    }

    
}
