# wgh.excel
一、项目结构：

                                    |---- 字段映射枚举类配置：ExcelExportInfoEnumApi，扩展集成该接口自定义字段信息
                                    |
                                    |                    |--直接传入数据：导出的数据 1、bean实体类型
                        |----export |----导出数据传入方式|   2、Map类型 3、Object[]类型               |-BooleanDataTypeConvertor
                        |           |                    |--实现适配器 AbstractPagerDataReadAdapter  |-DateDataTypeConvertor
                        |           |                       内部获取数据                             |-DoubleDataTypeConvertor
                        |           |----字段转换器转换数据：DataTypeConvertor                        |-EnumDataTypeConvertor
     ExcelUtils         |                                             | 实现                        |-IntegerDataTypeConvertor
    （导入、导出工具类）|                                      AbstractDataTypeConvertor  ---继承-->  |-LongDataTypeConvertor
                        |                                                                           |-StringDataTypeConvertor
                        |                                                                           |-其他（可自定义扩展）
                        |           |----字段映射枚举类配置：ExcelImportInfoEnumApi，
                        |           |                        扩展集成该接口自定义字段信息
                        |           |
                        |----import |                                                     |-BooleanDataTypeReConvertor
                                    |                                                     |-DateDataTypeReConvertor
                                    |                                                     |-DoubleDataTypeReConvertor
                                    |----字段转换器转换数据：DataTypeReConvertor --继承-->  |-EnumDataTypeReConvertor
                                                                   |实现                  |-IntegerDataTypeReConvertor
                                                             AbstractDataReConvertor      |-LongDataTypeReConvertor
                                                                                          |-StringDataTypeReConvertor
                                                                                          |-其他（可自定义扩展）


二、示例

  1、导出功能
    1.1、直接传入数据导出
        ExcelExportParam excelParam = new ExcelExportParam("ssssssss", list, new FileOutputStream(new File("e://ttt33.xls"))
                , ExcelTestModel.class, ExcelExportExcelInfoEnum1.class);
        ExcelUtils.Instance.INSTANCE.exportExcel(excelParam);
    1.2、使用适配器AbstractPagerDataReadAdapter内部获取数据
    
        暂不给示例

  2、导入功能
     InputStream is = new FileInputStream("e://ttt33.xls");
     ExcelImportParam importParam = new ExcelImportParam(is, ExcelImportExcelInfoEnum.class, ExcelTestModel.class);
     List<ExcelTestModel> list = ExcelUtils.Instance.INSTANCE.importExcel(importParam);
     for (Object object : list) {
         System.out.println(object);
     }
    
    
    
    
