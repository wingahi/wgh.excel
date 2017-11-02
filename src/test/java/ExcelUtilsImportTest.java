/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.wgh.excel.util.ExcelUtils;
import com.wgh.excel.util.model.ExcelImportParam;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Administrator
 */
public class ExcelUtilsImportTest {

    public static void main(String[] args) throws Exception {

        try {
            // 对读取Excel表格标题测试
            InputStream is = new FileInputStream("e://ttt33.xls");
            ExcelImportParam importParam = new ExcelImportParam(is, ExcelImportExcelInfoEnum.class, ExcelTestModel.class);
            List<ExcelTestModel> list = ExcelUtils.Instance.INSTANCE.importExcel(importParam);
            for (Object object : list) {
                System.out.println(object);
            }
            is.close();
        } catch (FileNotFoundException e) {
            System.out.println("未找到指定路径的文件!");
            e.printStackTrace();
        }
    }
}
