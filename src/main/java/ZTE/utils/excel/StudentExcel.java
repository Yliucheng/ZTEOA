package ZTE.utils.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;

/**
 * 创建学生管理模版
 */
@ContentRowHeight(30)
@HeadRowHeight(40)
public class StudentExcel {
    @ColumnWidth(20)
    @ExcelProperty(value = "学号",index = 0)
    private Integer studentid;

    @ColumnWidth(20)
    @ExcelProperty(value = "姓名",index = 1)
    private String studentName;

    @ColumnWidth(20)
    @ExcelProperty(value = "专业",index = 2)
    private String major;

    @ColumnWidth(20)
    @ExcelProperty(value = "班级",index = 3)
    private String className;

    @ColumnWidth(20)
    @ExcelProperty(value = "来自院校",index = 4)
    private String fromSchool;

    @ColumnWidth(20)
    @ExcelProperty(value = "学历",index = 5)
    private String education;

}
