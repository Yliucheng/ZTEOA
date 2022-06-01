package ZTE.entity.exam;

import java.util.List;

/**
 * 考试结果类，按月导出表格模板
 */
public class ResultInMonth {
    private Integer id;// 编号
    private Integer studentid;// 学号
    private String studentname;// 学生姓名
    private List<String> resultlist;// 本月成绩集合

    public ResultInMonth() {
    }

    public ResultInMonth(Integer id, Integer studentid, String studentname, List<String> resultlist) {
        this.id = id;
        this.studentid = studentid;
        this.studentname = studentname;
        this.resultlist = resultlist;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentid() {
        return studentid;
    }

    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public List<String> getResultlist() {
        return resultlist;
    }

    public void setResultlist(List<String> resultlist) {
        this.resultlist = resultlist;
    }
}
