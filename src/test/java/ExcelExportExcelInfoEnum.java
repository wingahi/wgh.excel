/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.wgh.excel.util.convertor.api.impl.BooleanStringDataTypeConvertor;
import com.wgh.excel.util.convertor.api.impl.DateDataTypeConvertor;
import com.wgh.excel.util.convertor.api.impl.DoubleDataTypeConvertor;
import com.wgh.excel.util.convertor.api.impl.EnumDataTypeConvertor;
import com.wgh.excel.util.convertor.api.impl.IntegerDataTypeConvertor;
import com.wgh.excel.util.convertor.api.impl.LongDataTypeConvertor;
import com.wgh.excel.util.convertor.api.impl.StringDataTypeConvertor;
import com.wgh.excel.util.model.ColStyle;
import java.util.Date;
import org.apache.poi.ss.usermodel.CellStyle;
import com.wgh.excel.util.info.enums.api.ExcelExportInfoEnumApi;

/**
 * excel2utils导出参数例子
 * @author Administrator
 */
public enum ExcelExportExcelInfoEnum implements ExcelExportInfoEnumApi{
    age("年龄", 0, 3, new ColStyle<Integer>("age", "", 0,Integer.class, CellStyle.ALIGN_RIGHT,new IntegerDataTypeConvertor())),
    name("姓名", 1, 3, new ColStyle<String>("name", "", "", String.class, CellStyle.ALIGN_LEFT,new StringDataTypeConvertor())),
    date("生日", 2, 3, new ColStyle<Date>("date", "yyyy-MM-dd",null, Date.class, CellStyle.ALIGN_CENTER,new DateDataTypeConvertor())),
    state("性别", 3, 3, new ColStyle<EnumTest>("state", "", null,EnumTest.class, CellStyle.ALIGN_LEFT,new EnumDataTypeConvertor())),
    amount("账户金额", 4, 3, new ColStyle<Double>("amount", "###0.00", 0.00,Double.class, CellStyle.ALIGN_RIGHT,new DoubleDataTypeConvertor())),
    black("是否黑", 5, 3, new ColStyle<String>("black", "", null,  Boolean.class, CellStyle.ALIGN_CENTER,new BooleanStringDataTypeConvertor())),
    tttt("tttt", 6, 3, new ColStyle<Long>("tttt", "", 0L, Long.class, CellStyle.ALIGN_RIGHT,new LongDataTypeConvertor())),
    count("count", 7, 3, new ColStyle<Long>("count", "###0", null, Long.class, CellStyle.ALIGN_RIGHT,new LongDataTypeConvertor()));

    private ExcelExportExcelInfoEnum(String title, Integer index, Integer width, ColStyle colStyle) {
        this.title = title;
        this.index = index;
        this.width = width;
        this.colStyle = colStyle;
    }
    private final String title;
    private final Integer index;
    private final Integer width;
    private final ColStyle colStyle;

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public Integer getIndex() {
       return index;
    }

    @Override
    public Integer getWidth() {
        return width;
    }

    @Override
    public ColStyle getColStyle() {
        return colStyle;
    }

   

}
