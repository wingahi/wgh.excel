/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.wgh.excel.util.ExcelUtils;
import com.wgh.excel.util.model.ExcelExportParam;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public class ExcelUtilsOutputTest {

    public static void main(String[] args) throws Exception {

        //类型1
        ExcelTestModel excelTestModel = new ExcelTestModel();
        excelTestModel.setAge(102);
        excelTestModel.setName("张三");
        excelTestModel.setState(EnumTest.MALE);
        excelTestModel.setAmount(1000000000.50);
        excelTestModel.setBlack(false);
        excelTestModel.setTttt(10000000000L);
        ExcelTestModel excelTestModel1 = new ExcelTestModel();
        excelTestModel1.setName("李四");
        excelTestModel1.setAge(1);
        excelTestModel1.setBlack(true);
        excelTestModel1.setState(EnumTest.FEMALE);
        excelTestModel1.setDate(new Date());
      //  excelTestModel1.setAmount(100000000.7);
        //类型2
        Map<String, Object> map1 = new HashMap<>();
        map1.put("age", 102);
        map1.put("name", "张三");
        map1.put("state", EnumTest.MALE);
        map1.put("amount", 1000000000.50);
        map1.put("black", false);
        map1.put("tttt", null);
        map1.put("count", null);
        map1.put("date", new Date());

        List list = new ArrayList();
        list.add(map1);
        list.add(excelTestModel);
        list.add(excelTestModel1);
        //类型3
        list.add(new Object[]{102, "1111", new Date(), EnumTest.MALE, 12222.0, false, 10000, 1254544});

        //方法一
        ExcelExportParam excelParam = new ExcelExportParam("ssssssss", list, new FileOutputStream(new File("e://ttt33.xls"))
                , ExcelTestModel.class, ExcelExportExcelInfoEnum1.class);
        ExcelUtils.Instance.INSTANCE.exportExcel(excelParam);

    }

}
