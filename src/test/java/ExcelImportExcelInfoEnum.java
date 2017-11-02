/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.wgh.excel.util.info.enums.api.ExcelImportInfoEnumApi;
import com.wgh.excel.util.model.ImportColParam;
import java.util.Date;

/**
 * excel2utils导出参数例子
 *
 * @author Administrator
 */
public enum ExcelImportExcelInfoEnum implements ExcelImportInfoEnumApi {

    age( 0, new ImportColParam<Integer>("age", Integer.class, null)),
    name( 1,new ImportColParam<Integer>("name", String.class, null)),
    date( 2, new ImportColParam<Integer>("date", Date.class, null)),
    state( 3,new ImportColParam<Integer>("state", EnumTest.class, null)),
    amount(4, new ImportColParam<Integer>("amount", Double.class, null)),
    black( 5, new ImportColParam<Integer>("black", Boolean.class, null)),
    tttt( 6, new ImportColParam<Integer>("tttt", Long.class, null)),
    count( 7,new ImportColParam<Integer>("count", Long.class, 0));

    private ExcelImportExcelInfoEnum( int index, ImportColParam importColParam) {
        this.index = index;
        this.importColParam = importColParam;
    }

    private final Integer index;
    private final ImportColParam importColParam;

  
    @Override
    public Integer getIndex() {
        return index;
    }

    @Override
    public ImportColParam getImportColParam() {
        return importColParam;
    }

}
