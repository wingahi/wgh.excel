/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wgh.excel.util;

import com.wgh.excel.util.adapter.AbstractPagerDataReadAdapter;
import com.wgh.excel.util.api.ExcelUtilsApi;
import com.wgh.excel.util.utils.ReflectUtils;
import com.wgh.excel.util.utils.CellUtils;
import com.wgh.excel.util.constant.Constant;
import com.wgh.excel.util.model.ColStyle;
import com.wgh.excel.util.model.ExcelExportParam;
import com.wgh.excel.util.model.ExcelImportParam;
import com.wgh.excel.util.model.ImportColParam;
import com.wgh.excel.util.reconvertor.exception.ReConvertException;
import com.wgh.excel.util.task.ExportTask;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * excel导出、导出操作类
 *
 * @author Administrator
 */
public class ExcelUtils implements ExcelUtilsApi {

    private final static Logger LOG = Logger.getLogger(ExcelUtils.class.getName());

    private ExcelUtils() {

    }

    public static class Instance {

        public static final ExcelUtils INSTANCE = new ExcelUtils();
    }

    /**
     * excel头部信息
     */
    private final static ConcurrentMap<String, List<Object[]>> EXCEL_HEADER_MAP = new ConcurrentHashMap<String, List<Object[]>>();
    /**
     * excel基本信息
     */
    private final static ConcurrentMap<String, ColStyle[]> EXCEL_INFO_MAP = new ConcurrentHashMap<String, ColStyle[]>();

    //存储不允许重复的字段
    protected final static ThreadLocal<Map<String, Object>> UNREPEATAL_FIELD_THREAD_LOCAL = new ThreadLocal<Map<String, Object>>() {
        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<String, Object>();
        }

    };
    /**
     * 类的方法
     */
    protected final static ConcurrentMap<String, Map<String, Method>> CALSS_METHOD_MAP = new ConcurrentHashMap<String, Map<String, Method>>();

    protected final static String READ_METHOD_PRE = "READ_";

    protected final static String WRITE_METHOD_PRE = "WRITE_";

    /**
     * 导入excel，得到Excel，并解析内容
     *
     * @param importParam
     * @return
     * @throws IOException
     * @throws Exception
     * @return 异常则返回null
     */
    @Override
    public List importExcel(ExcelImportParam importParam) throws IOException, Exception {

        ImportColParam[] colParams = initImportColParams(importParam.getParamEnumClass());

        List list = new ArrayList();
        Sheet sheet = null;
        //创建workbook，兼容xls、xlsx
        Workbook wb = WorkbookFactory.create(importParam.getIns());

        try {
            getBeanPropertyWriteMethods(importParam.getClazz());
            //遍历excel多个sheet
            for (int sheetIndex = 0, sheetSize = wb.getNumberOfSheets(); sheetIndex < sheetSize; sheetIndex++) {
                int rowIndex = 1;
                sheet = wb.getSheetAt(sheetIndex);
                //总行数  
                int rowLength = sheet.getLastRowNum();

                //得到Excel工作表的行  
                Row headerRow = sheet.getRow(0);
                if(headerRow!=null){
                    int colLength = headerRow.getLastCellNum();
                    final Class clazz = importParam.getClazz();
                    for (; rowIndex <= rowLength; rowIndex++) {
                        //得到Excel工作表的行  
                        Row row = sheet.getRow(rowIndex);
                        //解析单个实体
                        Object object = analysisEntity(row, colParams, clazz, colLength);
                        if (object != null) {
                            list.add(object);
                        }
                    }
                }
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
              UNREPEATAL_FIELD_THREAD_LOCAL.remove();
        }
        return null;

    }

    /**
     * 解析excel单行数据为实体
     *
     * @param row excel的一行
     * @param fieldNames excel每列对应的字段名
     * @param clazz 转换的实体类class
     * @param colLen 列数
     * @return
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    private static Object analysisEntity(Row row, ImportColParam[] importColParams, Class clazz, int colLength) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
        Object entity = ReflectUtils.newInstance(clazz);
        if (entity != null) {
            //获取bean的属性写方法
            Map<String, Method> writeMethodMaps = CALSS_METHOD_MAP.get(clazz.getName() + WRITE_METHOD_PRE);
            boolean canJudge = true;
            for (int colIndex = 0; colIndex < colLength; colIndex++) {
                //得到Excel工作表指定行的单元格  
                Cell cell = row.getCell(colIndex);
                //允许空cell
                if (cell == null) {
                    continue;
                }
                canJudge = true;
                Method method = writeMethodMaps.get(importColParams[colIndex].getFieldName());
                cell.setCellType(CellType.STRING);
                Object _value = importColParams[colIndex].getDataTypeReConvertor().convert(importColParams[colIndex], cell.getStringCellValue());
                if (!importColParams[colIndex].getRepeatable() && _value != null) {
                    Map<String, Object> unRepeatableFieldMap = UNREPEATAL_FIELD_THREAD_LOCAL.get();
                    //是字符串
                    if (_value.getClass().equals(String.class) && _value.toString().trim().isEmpty()) {
                        canJudge = false;
                    }
                    if (canJudge && unRepeatableFieldMap.containsKey(importColParams[colIndex].getFieldName() + ":" + _value)) {
                        throw new ReConvertException(importColParams[colIndex], "位置【行：" + row.getRowNum() + "-列：" + colIndex + "】]重复，重复值为：" + _value);
                    }
                    unRepeatableFieldMap.put(importColParams[colIndex].getFieldName() + ":" + _value, _value);
                }
                method.invoke(entity, new Object[]{_value});

            }
        }
        return entity;

    }

    protected static void getBeanPropertyWriteMethods(Class clazz) {
        synchronized (clazz) {
            if (!CALSS_METHOD_MAP.containsKey(clazz.getName() + WRITE_METHOD_PRE)) {
                Map<String, Method> writeMethodMaps = ReflectUtils.getBeanPropertyWriteMethods(clazz);
                CALSS_METHOD_MAP.put(clazz.getName() + WRITE_METHOD_PRE, writeMethodMaps);
            }
        }

    }

    private static ImportColParam[] initImportColParams(Class clazz) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        ImportColParam[] colParams = new ImportColParam[clazz.getEnumConstants().length];
        getBeanPropertyReadMethods(clazz);
        Map<String, Method> readMethodMaps = CALSS_METHOD_MAP.get(clazz.getName() + READ_METHOD_PRE);
        for (Object constant : clazz.getEnumConstants()) {
            colParams[Integer.parseInt(readMethodMaps.get(Constant.INDEX).invoke(constant, new Object[0]).toString())] = (ImportColParam) readMethodMaps.get(Constant.IMPORT_COL_PARAM).invoke(constant, new Object[0]);
        }
        return colParams;
    }

    protected static void getBeanPropertyReadMethods(Class clazz) {
        synchronized (clazz) {
            if (!CALSS_METHOD_MAP.containsKey(clazz.getName() + READ_METHOD_PRE)) {
                Map<String, Method> readMethodMaps = ReflectUtils.getBeanPropertyReadMethods(clazz);
                CALSS_METHOD_MAP.put(clazz.getName() + READ_METHOD_PRE, readMethodMaps);
            }
        }
    }

    protected static CellStyle setHeaderStyle(final Workbook book) {
        //样式
        CellStyle cellStyle = book.createCellStyle();
        //表头样式
        cellStyle.setAlignment(Constant.TABLE_HEADER_ALIGN);
        cellStyle.setFillForegroundColor(Constant.FILL_FORE_GROUND_COLOR);// 设置背景色   
        cellStyle.setFillPattern(Constant.FILL_PATTERN);
        cellStyle.setWrapText(true);
        //表头字体
        Font font = book.createFont();
        font.setFontName(Constant.FONT_NAME);
        font.setBoldweight(Constant.FONT_WEIGHT);//粗体显示  
        font.setFontHeightInPoints(Constant.FONT_HEIGHT);//设置字体大小   
        font.setColor(Constant.FONT_COLOR);
        cellStyle.setFont(font);
        return cellStyle;
    }

    /**
     * 导出excel，外部调用导出excel方法
     *
     * @param excelParam 传入方法
     */
    @Override
    public void exportExcel(ExcelExportParam excelParam){
        long st = System.currentTimeMillis();
        //导出的数据
        List data = excelParam.getData();
        //创建Excel工作薄   
        final Workbook book = new HSSFWorkbook();
        try {
            //初始化枚举excel的头部及基本信息
            getExcelHeaderInfoMap(excelParam.getExcelInfoClazz());
            //初始化读方法
            getBeanPropertyReadMethods(excelParam.getZclass());
            if(excelParam.getDataReadAdapter()==null){
                 exportExcelFromData(book, excelParam, data);
            }else{
                 exportExcelForPagerData(book, excelParam);
            }
            LOG.log(Level.INFO, "com.wgh.excel.util.ExcelUtils.exportExcel()导出解析数据总耗时:{0}ms", System.currentTimeMillis() - st);
            //写入数据  把相应的Excel 工作簿存盘   
            book.write(excelParam.getOs());
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    /**
     * 通过传入数据来进行导出
     * @param book
     * @param excelParam
     * @param data
     * @throws ExecutionException
     * @throws InterruptedException 
     */
    private void exportExcelFromData(Workbook book, ExcelExportParam excelParam, List data) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = null;
        try {
            forkJoinPool = new ForkJoinPool();
            ExportTask exportTask = new ExportTask(book, null, excelParam.getTitle(), data, excelParam.getExcelInfoClazz().getName(), 0, data.size());
            forkJoinPool.submit(exportTask);
            exportTask.get();
        } catch (Exception e) {
             e.printStackTrace();
        } finally {
            if (forkJoinPool != null) {
                forkJoinPool.shutdown();
            }
        }

    }
    
    /**
     * 通过内部分页获取数据来进行导出
     * @param book
     * @param excelParam
     */
    public void exportExcelForPagerData(Workbook book, ExcelExportParam excelParam){
        try {
            int sheetIndex = 1,nextSheetIndex = 1,totalCount = 0,lastTotalCount=0;
            AbstractPagerDataReadAdapter pagerDataReadAdapter = excelParam.getDataReadAdapter();
            List<Object> data = pagerDataReadAdapter.doReadData(pagerDataReadAdapter.getCurPage(),Constant.DEFAULT_PAGE_SIZE);
            Sheet sheet = null;
            while(data!=null && !data.isEmpty()){
                if(Constant.EXPORT_MAX_NUM_FOR_ONE_SHEET<data.size()){
                    throw new Exception("分页的页容量pageSize必须要被数字"+Constant.EXPORT_MAX_NUM_FOR_ONE_SHEET+"整除");
                }
                totalCount+=data.size();
                /**
                 * 计算是否需要产生新的sheet
                 */
                nextSheetIndex = getTotalPage(totalCount);
                /**
                 * 如果是新建sheet，则需要设置表格样式
                 */
                if(nextSheetIndex>sheetIndex || lastTotalCount==0){
                     sheet = createSheet(book,(sheetIndex=nextSheetIndex), excelParam.getTitle());
                     setSheetStyle(book, sheet, excelParam.getExcelInfoClazz().getName());
                }
                /**
                 * 进行导出数据
                 */
                doExportByData(data, sheet, excelParam.getExcelInfoClazz().getName(),lastTotalCount%Constant.EXPORT_MAX_NUM_FOR_ONE_SHEET);
                lastTotalCount = totalCount;
                //清空数据
                data = null;
                data = pagerDataReadAdapter.doReadData(pagerDataReadAdapter.nextPage(),Constant.DEFAULT_PAGE_SIZE);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    /**
     * 获取页码
     * @param dataLen
     * @return 
     */
    private static int getTotalPage(int dataLen) {
        int page = dataLen / Constant.EXPORT_MAX_NUM_FOR_ONE_SHEET;
        if (page * Constant.EXPORT_MAX_NUM_FOR_ONE_SHEET < dataLen) {
            page++;
        }
        return page;
    }
    
    private static Sheet createSheet(Workbook workbook,int startIndex,String title) {
        Sheet sheet = null;
        if (null != title && !"".equals(title)) {
            startIndex = startIndex-1;
            sheet = workbook.createSheet(title + (startIndex == 0 ? "" : startIndex));
        } else {
            sheet = workbook.createSheet();
        }
        return sheet;
    }
    
    
    public void doExportExcel(Sheet sheet, String excelInfoClazzName, final Workbook book, List data) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        setSheetStyle(book, sheet, excelInfoClazzName);
        doExportByData(data, sheet, excelInfoClazzName,0);
    }

    private void doExportByData(List data, Sheet sheet, String excelInfoClazzName,int rowStartIndex) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (data != null && data.size() > 0) {
            Row rowi = null;
            Object object = null;
            //循环导出数据到excel中
            for (int dataIndex = 0, len = data.size(); dataIndex < len; dataIndex++) {
                object = data.get(dataIndex);
                //创建第i行
                rowi = sheet.createRow(rowStartIndex+dataIndex + 1);
                putDateToRowCell(rowi, excelInfoClazzName, object);
            }
        }
    }

    private void setSheetStyle(final Workbook book, Sheet sheet, String excelInfoClazzName) {
        //设置表头样式
        CellStyle cellStyle = setHeaderStyle(book);
        CellStyle dataCellStyle = null; 
        sheet.createFreezePane(0, 1);
        //第一行为标题行
        Row row = sheet.createRow(0);//创建第一行
        ColStyle[] colStyles = EXCEL_INFO_MAP.get(excelInfoClazzName);
        ColStyle colStyle = null;
        List<Object[]> excelHeader = EXCEL_HEADER_MAP.get(excelInfoClazzName);
        for (int zhIndex = 0, len = excelHeader.size(); zhIndex < len; zhIndex++) {
            int index = (int) (excelHeader.get(zhIndex)[1]);
            Cell cell = row.createCell(index);
            cell.setCellStyle(cellStyle);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            cell.setCellValue((String) (excelHeader.get(zhIndex)[0]));
            sheet.setColumnWidth((short) index, (short) 35.7 * 50 * (int) (excelHeader.get(zhIndex)[2]));
            colStyle = colStyles[index];
            dataCellStyle = CellUtils.getCellStyle(book, colStyle);
            if(colStyle.getBgColor()==null){
                sheet.setDefaultColumnStyle(index, dataCellStyle);
            }else{
                colStyle.setCellStyle(dataCellStyle);
            }
        }
    }

    /**
     * 填充数据到excel的行
     *
     * @param rowi 行
     * @param object 行数据实体
     * @param enNames 字段英文名
     * @param enumeClass 枚举类型
     * @param hasDefaultValue 是否设置默认值
     * @param orderMethods 排序的set方法，只有bean实体类才有，其他的Object[]和Map类型的行数据均为null
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private static void putDateToRowCell(Row rowi, String excelInfoClazzName, Object object) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Cell cell = null;
        ColStyle[] colStyles = EXCEL_INFO_MAP.get(excelInfoClazzName);
        if (Map.class.isAssignableFrom(object.getClass())) {//map类型
            Map<String, Object> entityMap = (Map<String, Object>) object;
            for (int index = 0; index < colStyles.length; index++) {
                cell = rowi.createCell(index);
                CellUtils.setValue(cell, colStyles[index], entityMap.get(colStyles[index].getFieldName()));
            }
        } else if (Object[].class.isAssignableFrom(object.getClass())) {//String[]类型数据
            Object[] objectArray = (Object[]) object;
            for (int index = 0; index < colStyles.length && index < objectArray.length; index++) {
                cell = rowi.createCell(index);
                CellUtils.setValue(cell, colStyles[index], objectArray[index]);
            }
        } else {//bean类型数据
            Map<String, Method> readMethodMaps = CALSS_METHOD_MAP.get(object.getClass().getName() + READ_METHOD_PRE);
            for (int index = 0; index < colStyles.length; index++) {
                cell = rowi.createCell(index);
                Object value = readMethodMaps.get(colStyles[index].getFieldName()).invoke(object, new Object[0]);
                CellUtils.setValue(cell, colStyles[index], value);
            }
        }
    }

    private static void getExcelHeaderInfoMap(Class clazz) throws Exception {
        if (EXCEL_INFO_MAP.containsKey(clazz.getName())) {
            return;
        }
        synchronized (clazz) {
            List<Object[]> headerInfos = new ArrayList<Object[]>(clazz.getEnumConstants().length);
            ColStyle[] colStyles = new ColStyle[clazz.getEnumConstants().length];
            Object[] objects;
            Object colStyle = null;
            Map<String, Method> methodMap = ReflectUtils.getBeanPropertyReadMethods(clazz);
            int enumIndex = -1;
            Method indexMethod =  methodMap.get(Constant.INDEX);
            for (Object constant : clazz.getEnumConstants()) {
                objects = new Object[5];
                objects[0] = methodMap.get(Constant.TITLE).invoke(constant, new Object[0]);
                /**默认以第一个属性的index配置为标准
                 * 1、如果配置了idnex，则使用配置使用的index
                 * 2、如果没有配置index，那么就按字段定义顺序排序
                 */
                 if(null!=indexMethod && enumIndex==-1){
                    objects[1] = indexMethod.invoke(constant, new Object[0]);
                    enumIndex = Integer.parseInt(objects[1].toString());
                }else{
                    enumIndex++;
                    objects[1] = enumIndex;
                }
                
                objects[2] = methodMap.get(Constant.WIDTH).invoke(constant, new Object[0]);
                headerInfos.add(objects);
                colStyle = methodMap.get(Constant.COL_STYLE).invoke(constant, new Object[0]);
                
                colStyles[enumIndex] = (ColStyle) colStyle;
                colStyles[enumIndex].setFieldName(constant.toString());
            }
            EXCEL_INFO_MAP.put(clazz.getName(), colStyles);
            EXCEL_HEADER_MAP.put(clazz.getName(), headerInfos);
        }
    }

}
