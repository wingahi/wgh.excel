/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wgh.excel.util.reconvertor.exception;

import com.wgh.excel.util.model.ImportColParam;

/**
 *
 * @author Administrator
 */
public class ReConvertException extends RuntimeException {
    
    public ReConvertException(ImportColParam importColParam) {
    }

    public ReConvertException(ImportColParam importColParam,String message) {
        super(message+"，列信息："+importColParam.toString());
    }

    public ReConvertException(ImportColParam importColParam,String message, Throwable cause) {
        super(message+"，列信息："+importColParam.toString(), cause);
    }

    public ReConvertException(ImportColParam importColParam,String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message+"，列信息："+importColParam.toString(), cause, enableSuppression, writableStackTrace);
    }

}

