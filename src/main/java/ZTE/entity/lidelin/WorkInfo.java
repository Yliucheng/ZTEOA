package ZTE.entity.lidelin;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;

/**
 * @ClassName WorkInfo
 * @Description 作业完成情况学生信息展示、导出用
 * @Version 1.0
 */
@ContentRowHeight(30) // 表体行高
@HeadRowHeight(40) // 表头行高
@ColumnWidth(20) // 列宽
public class WorkInfo {

    @ExcelProperty(value = "ID", index = 0)
    private int id; // ID
    @ExcelProperty(value = "学生姓名", index = 1)
    private String studentName; // 学生姓名
    @ExcelProperty(value = "考试日期", index = 2)
    private String cktime; // 考试日期
    @ExcelProperty(value = "作业完成表现", index = 3)
    private String ckstatu; // 作业完成表现

    public WorkInfo() {
    }

    public WorkInfo(int id, String studentName, String cktime, String ckstatu) {
        this.id = id;
        this.studentName = studentName;
        this.cktime = cktime;
        this.ckstatu = ckstatu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCktime() {
        return cktime;
    }

    public void setCktime(String cktime) {
        this.cktime = cktime;
    }

    public String getCkstatu() {
        return ckstatu;
    }

    public void setCkstatu(String ckstatu) {
        this.ckstatu = ckstatu;
    }

    @Override
    public String toString() {
        return "WorkInfo{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                ", cktime='" + cktime + '\'' +
                ", ckstatu='" + ckstatu + '\'' +
                '}';
    }

}
