package ZTE.utils.excel.exam;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;

/**
 * 成绩表导出模板
 */
@ContentRowHeight(30) //行高
@HeadRowHeight(40) //头部行高
public class ExamResultExcel {
    @ColumnWidth(20)
    @ExcelProperty(value = "考试ID",index = 0)
    private  Integer examId;
    @ColumnWidth(20)
    @ExcelProperty(value = "学生姓名",index = 1)
    private String studentName;
    @ColumnWidth(20)
    @ExcelProperty(value = "考试日期",index = 2)
    private  String examDate;
    @ColumnWidth(20)
    @ExcelProperty(value = "考试类型",index = 3)
    private String examTypeNama;
    @ColumnWidth(20)
    @ExcelProperty(value = "成绩",index = 4)
    private  Integer score;
}
