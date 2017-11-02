/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wgh.excel.util.utils;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class DataTypeUtils {
    public static Date getDateValue(Object input, Object defaultValue) throws NumberFormatException {
        if (input == null) {
            if (defaultValue != null) {
                return (Date) defaultValue;
            }
            return null;
        }
        return (Date) input;
    }

    public static Float getFloatValue(Object input, Object defaultValue) throws NumberFormatException {
        if (input == null) {
            if (defaultValue != null) {
                return (Float) defaultValue;
            }
            return null;
        }
        BigDecimal bigDecimal = new BigDecimal(input.toString());
        return bigDecimal.floatValue();
    }
    
    public static Double getDoubleValue(Object input, Object defaultValue) throws NumberFormatException {
        if (input == null) {
            if (defaultValue != null) {
                return (Double) defaultValue;
            }
            return null;
        }
        BigDecimal bigDecimal = new BigDecimal(input.toString());
        return bigDecimal.doubleValue();
    }

    public static Long getLongValue(Object input, Object defaultValue) throws NumberFormatException {
        if (input == null) {
            if (defaultValue != null) {
                return (Long) defaultValue;
            }
            return null;
        }
        BigDecimal bigDecimal = new BigDecimal(input.toString());
        return bigDecimal.longValue();
    }

    public static Integer getIntegerValue(Object input, Object defaultValue) throws NumberFormatException {
        if (input == null) {
            if (defaultValue != null) {
                return (Integer) defaultValue;
            }
            return null;
        }
        return Integer.parseInt(input.toString());
    }

    public static Short getShortValue(Object input, Object defaultValue) throws NumberFormatException {
        if (input == null) {
            if (defaultValue != null) {
                return (Short) defaultValue;
            }
            return null;
        }
        return Short.parseShort(input.toString());
    }

    public static Boolean getBooleanValue(Object input, Object defaultValue) throws NumberFormatException {
        if (input == null) {
            if (defaultValue != null) {
                return (Boolean) defaultValue;
            }
            return null;
        }
        return input.toString().equalsIgnoreCase("true");
    }

    public static String getBooleanStringValue(Object input, Object defaultValue) throws NumberFormatException {
        if (input == null) {
            if (defaultValue != null) {
                return (String) defaultValue;
            }
            return "";
        }
        return input.toString().equalsIgnoreCase("true") ? "是" : "否";
    }

    
    public static String getStringValue(Object input, Object defaultValue) throws NumberFormatException {
        if (input == null) {
            if (defaultValue != null) {
                return (String) defaultValue;
            }
            return "";
        }
        return input.toString();
    }
}
