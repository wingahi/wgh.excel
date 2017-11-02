/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wgh.excel.util.model;

import com.wgh.excel.util.reconvertor.api.DataTypeReConvertor;
import com.wgh.excel.util.reconvertor.api.impl.BooleanDataTypeReConvertor;
import com.wgh.excel.util.reconvertor.api.impl.DateDataTypeReConvertor;
import com.wgh.excel.util.reconvertor.api.impl.DoubleDataTypeReConvertor;
import com.wgh.excel.util.reconvertor.api.impl.EnumDataTypeReConvertor;
import com.wgh.excel.util.reconvertor.api.impl.FloatDataTypeReConvertor;
import com.wgh.excel.util.reconvertor.api.impl.IntegerDataTypeReConvertor;
import com.wgh.excel.util.reconvertor.api.impl.LongDataTypeReConvertor;
import com.wgh.excel.util.reconvertor.api.impl.ShortDataTypeReConvertor;
import com.wgh.excel.util.reconvertor.api.impl.StringDataTypeReConvertor;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 导入excel列信息
 *
 * @author Administrator
 */
public class ImportColParam<T> {

    /**
     * 默认：类型与转换器关系
     */
    private final static Map<String, DataTypeReConvertor> TYPE_CONVERTORS = new HashMap<String, DataTypeReConvertor>();

    static {
        TYPE_CONVERTORS.put(Integer.class.getName(), new IntegerDataTypeReConvertor());
        TYPE_CONVERTORS.put(Short.class.getName(), new ShortDataTypeReConvertor());
        TYPE_CONVERTORS.put(Long.class.getName(), new LongDataTypeReConvertor());
        TYPE_CONVERTORS.put(String.class.getName(), new StringDataTypeReConvertor());
        TYPE_CONVERTORS.put(Boolean.class.getName(), new BooleanDataTypeReConvertor());
        TYPE_CONVERTORS.put(Float.class.getName(), new FloatDataTypeReConvertor());
        TYPE_CONVERTORS.put(Double.class.getName(), new DoubleDataTypeReConvertor());
        TYPE_CONVERTORS.put(Date.class.getName(), new DateDataTypeReConvertor());
        TYPE_CONVERTORS.put(Enum.class.getName(), new EnumDataTypeReConvertor());

        TYPE_CONVERTORS.put(int.class.getName(), new IntegerDataTypeReConvertor());
        TYPE_CONVERTORS.put(short.class.getName(), new ShortDataTypeReConvertor());
        TYPE_CONVERTORS.put(long.class.getName(), new LongDataTypeReConvertor());
        TYPE_CONVERTORS.put(boolean.class.getName(), new BooleanDataTypeReConvertor());
        TYPE_CONVERTORS.put(float.class.getName(), new FloatDataTypeReConvertor());
        TYPE_CONVERTORS.put(double.class.getName(), new DoubleDataTypeReConvertor());
    }

    private Class clazz;
    private DataTypeReConvertor dataTypeReConvertor;
    private T defalutValue;
    private String fieldName;
    private boolean required;
    private boolean repeatable;

    public ImportColParam(String fieldName, Class clazz, T defalutValue) {
        this(fieldName, null, clazz, defalutValue,false,true);
        if (clazz == null) {
            throw new RuntimeException("字段type必填");
        }
    }
    
     public ImportColParam(String fieldName, Class clazz, T defalutValue, boolean required) {
        this(fieldName, null, clazz, defalutValue, required, true);
        if (clazz == null) {
            throw new RuntimeException("字段type必填");
        }
    }
    
    public ImportColParam(String fieldName, Class clazz, T defalutValue, boolean required, boolean repeatable) {
        this(fieldName, null, clazz, defalutValue, required, repeatable);
        if (clazz == null) {
            throw new RuntimeException("字段type必填");
        }
    }

    public ImportColParam(String fieldName, DataTypeReConvertor dataTypeReConvertor, T defalutValue, boolean required, boolean repeatable) {
        this(fieldName, dataTypeReConvertor, null, defalutValue, required, repeatable);
        if (dataTypeReConvertor == null) {
            throw new RuntimeException("字段dataTypeReConvertor必填");
        }
    }

    public ImportColParam(String fieldName, DataTypeReConvertor dataTypeReConvertor, Class clazz, T defalutValue, boolean required, boolean repeatable) {
        if (fieldName == null || fieldName.equals("")) {
            throw new RuntimeException("字段fieldName必填");
        }
        this.fieldName = fieldName;
        this.defalutValue = defalutValue;
        this.clazz = clazz;
        this.dataTypeReConvertor = dataTypeReConvertor;
        if (dataTypeReConvertor == null) {
            if (Enum.class.isAssignableFrom(this.clazz)) {
                this.dataTypeReConvertor = TYPE_CONVERTORS.get(Enum.class.getName());
            } else {
                this.dataTypeReConvertor = TYPE_CONVERTORS.get(this.clazz.getName());
            }
        }
        this.required = required;
        this.repeatable = repeatable;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public DataTypeReConvertor getDataTypeReConvertor() {
        return dataTypeReConvertor;
    }

    public void setDataTypeReConvertor(DataTypeReConvertor dataTypeReConvertor) {
        this.dataTypeReConvertor = dataTypeReConvertor;
    }

    public T getDefalutValue() {
        return defalutValue;
    }

    public void setDefalutValue(T defalutValue) {
        this.defalutValue = defalutValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public boolean getRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean getRepeatable() {
        return repeatable;
    }

    public void setRepeatable(boolean repeatable) {
        this.repeatable = repeatable;
    }

    @Override
    public String toString() {
        return "ImportColParam{" + "fieldName=" + fieldName + ", defalutValue=" + defalutValue + ", required=" + required + ", repeatable=" + repeatable + '}';
    }

}
