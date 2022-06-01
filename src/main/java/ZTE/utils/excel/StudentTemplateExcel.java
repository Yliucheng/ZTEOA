package ZTE.utils.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;

/**
 * 创建学生表格上传模版
 */
@ContentRowHeight(30)
@HeadRowHeight(40)
public class StudentTemplateExcel {
    @ColumnWidth(20)
    @ExcelProperty(value = "姓名",index = 0)
    private String studentName;

    @ColumnWidth(20)
    @ExcelProperty(value = "班级id",index = 1)
    private Integer classId;

    @ColumnWidth(20)
    @ExcelProperty(value = "来自院校",index = 2)
    private String fromSchool;

    @ColumnWidth(20)
    @ExcelProperty(value = "学历",index = 3)
    private String education;

    @ColumnWidth(20)
    @ExcelProperty(value = "登录账号",index = 4)
    private String logincode;

    @ColumnWidth(20)
    @ExcelProperty(value = "密码",index = 5)
    private String password;

}
