package ZTE.utils.excel.exam;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;

/**
 * 成绩导入模板
 */
@ContentRowHeight(30) //行高
@HeadRowHeight(40) //头部行高
public class ExamTemplate {
    @ColumnWidth(20)
    @ExcelProperty(value = "学号",index = 0)
    private  Integer studentId;
    @ColumnWidth(20)
    @ExcelProperty(value = "姓名",index = 1)
    private String studentName;
    @ColumnWidth(20)
    @ExcelProperty(value = "考试日期",index = 2)
    private  String examDate;
    @ColumnWidth(20)
    @ExcelProperty(value = "考试类型",index = 3)
    private String examType;
    @ColumnWidth(20)
    @ExcelProperty(value = "考试表现",index = 4)
    private  String studentResult;
}
