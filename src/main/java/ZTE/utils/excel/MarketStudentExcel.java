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
public class MarketStudentExcel {
    @ColumnWidth(20)
    @ExcelProperty(value = "学号",index = 0)
    private Integer id;

    @ColumnWidth(20)
    @ExcelProperty(value = "姓名",index = 1)
    private String name;

    @ColumnWidth(20)
    @ExcelProperty(value = "市场类型",index = 2)
    private String stuType;

    @ColumnWidth(20)
    @ExcelProperty(value = "班级编号",index = 3)
    private Integer classId;

    @ColumnWidth(20)
    @ExcelProperty(value = "班级",index = 4)
    private String className;

    @ColumnWidth(20)
    @ExcelProperty(value = "来自院校",index = 5)
    private String fromSchool;

    @ColumnWidth(20)
    @ExcelProperty(value = "学历",index = 6)
    private String education;

    @ColumnWidth(20)
    @ExcelProperty(value = "电话",index = 7)
    private String phone;

    @ColumnWidth(20)
    @ExcelProperty(value = "QQ",index = 8)
    private String qq;

    @ColumnWidth(20)
    @ExcelProperty(value = "性格特点",index = 9)
    private String xingge;

    @ColumnWidth(20)
    @ExcelProperty(value = "备注",index = 10)
    private String beizhu;

    @ColumnWidth(20)
    @ExcelProperty(value = "培训意向",index = 11)
    private String willTrain;

    @ColumnWidth(20)
    @ExcelProperty(value = "创建人",index = 12)
    private String createAuthorId;

    @ColumnWidth(20)
    @ExcelProperty(value = "创建时间",index = 13)
    private String createTime;

    @ColumnWidth(20)
    @ExcelProperty(value = "修改人",index = 14)
    private String modifyAuthor;

    @ColumnWidth(20)
    @ExcelProperty(value = "修改时间",index = 15)
    private String modifyAuthorTime;



}
