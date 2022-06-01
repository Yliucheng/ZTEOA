package ZTE.utils.excel.classes;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;

@ContentRowHeight(30) //行高
@HeadRowHeight(40) //头部行高
public class ClassTemplateExport {
    @ColumnWidth(20)
    @ExcelProperty(value = "班级名称",index = 0)
    private String className;
    @ColumnWidth(20)
    @ExcelProperty(value = "专业ID",index = 1)
    private  Integer studyType;
}
