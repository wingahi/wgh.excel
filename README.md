# wgh.excel
# wgh.excel
一、项目结构：

                                    |---- 字段映射枚举类配置：ExcelExportInfoEnumApi，扩展集成该接口自定义字段信息
                                    |
                                    |                    |--直接传入数据：导出的数据 1、bean实体类型 2、Map类型 3、Object[]类型 三种数据一起也可以使用，Object[]是按照下标来解析
                        |----export |----导出数据传入方式|                                                           |-BooleanDataTypeConvertor
                        |           |                    |--实现适配器AbstractPagerDataReadAdapter内部获取数据       |-DateDataTypeConvertor
                        |           |                                                                                |-DoubleDataTypeConvertor
                        |           |----字段转换器转换数据：DataTypeConvertor --实现->AbstractDataTypeConvertor-继承|-EnumDataTypeConvertor
     ExcelUtils         |                                                                                            |-IntegerDataTypeConvertor
    （导入、导出工具类）|                                                                                            |-LongDataTypeConvertor
                        |                                                                                            |-StringDataTypeConvertor
                        |                                                                                            |-其他（可自定义扩展）
                        |           |----字段映射枚举类配置：ExcelImportInfoEnumApi，扩展集成该接口自定义字段信息
                        |           |
                        |           |
                        |----import |          最终返回实体集合                                                      |-BooleanDataTypeReConvertor
                                    |                                                                                |-DateDataTypeReConvertor
                                    |                                                                                |-DoubleDataTypeReConvertor
                                    |----字段转换器转换数据：DataTypeReConvertor --实现->AbstractDataReConvertor-继承|-EnumDataTypeReConvertor
                                                                                                                     |-IntegerDataTypeReConvertor
                                                                                                                     |-LongDataTypeReConvertor
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
    
    
    
    
