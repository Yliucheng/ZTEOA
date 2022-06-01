package ZTE.utils.excel.att;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;

@ContentRowHeight(30) //行高
@HeadRowHeight(40) //头部行高
public class AttTemplate {
    @ColumnWidth(20)
    @ExcelProperty(value = "学号",index = 0)
    private Integer studentNo;
    @ColumnWidth(20)
    @ExcelProperty(value = "姓名",index = 1)
    private String studentName;
    @ColumnWidth(20)
    @ExcelProperty(value = "考勤时间",index = 2)
    private String ckTime;
    @ColumnWidth(20)
    @ExcelProperty(value = "考勤状态",index = 3)
    private String ckstatu;
}
