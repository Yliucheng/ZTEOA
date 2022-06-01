package ZTE.utils.excel.classes;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;

@ContentRowHeight(30) //行高
@HeadRowHeight(40) //头部行高
public class ClassExport {
    @ColumnWidth(20)
    @ExcelProperty(value = "班级编号",index = 0)
    private  Integer classId;
    @ColumnWidth(20)
    @ExcelProperty(value = "班级名称",index = 1)
    private String className;
    @ColumnWidth(20)
    @ExcelProperty(value = "专业ID",index = 2)
    private  Integer studyType;
    @ColumnWidth(20)
    @ExcelProperty(value = "专业类型",index = 3)
    private  String majorName;

}
