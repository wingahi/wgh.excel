/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wgh.excel.util.convertor.exception;

import com.wgh.excel.util.model.ColStyle;

/**
 *
 * @author Administrator
 */
public class ConvertException extends RuntimeException {
    
    public ConvertException(ColStyle colStyle) {
    }

    public ConvertException(ColStyle colStyle,String message) {
        super(message+"，列信息："+colStyle.toString());
    }

    public ConvertException(ColStyle colStyle,String message, Throwable cause) {
        super(message+"，列信息："+colStyle.toString(), cause);
    }

    public ConvertException(ColStyle colStyle,String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message+"，列信息："+colStyle.toString(), cause, enableSuppression, writableStackTrace);
    }

}
