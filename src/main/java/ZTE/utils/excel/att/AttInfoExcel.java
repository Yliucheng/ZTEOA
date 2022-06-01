package ZTE.utils.excel.att;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;

/**
 * 出勤表导出模板
 */
@ContentRowHeight(30) //行高
@HeadRowHeight(40) //头部行高
public class AttInfoExcel {
    @ColumnWidth(20)
    @ExcelProperty(value = "编号",index = 0)
    private Integer id;
    @ColumnWidth(20)
    @ExcelProperty(value = "姓名",index = 1)
    private String studentName;
    @ColumnWidth(20)
    @ExcelProperty(value = "专业",index = 2)
    private String major;
    @ColumnWidth(20)
    @ExcelProperty(value = "班级",index = 3)
    private String classes;
    @ColumnWidth(20)
    @ExcelProperty(value = "日期",index = 4)
    private String attDate;
    @ColumnWidth(20)
    @ExcelProperty(value = "考勤状态",index = 5)
    private  String ckStatus;
}
