package ZTE.entity;

/**
 * 查询条件类
 */
public class QueryParams {
    private String className;// 班级名称
    private String namec;// 学生姓名
    private String date;// 查询日期
    private String result;// 成绩表现
    private Integer examType;// 考试类型

    public QueryParams() {
    }

    public QueryParams(String className, String namec, String date, String result) {
        this.className = className;
        this.namec = namec;
        this.date = date;
        this.result = result;
    }

    public QueryParams(String className, String namec, String date, String result, Integer examType) {
        this.className = className;
        this.namec = namec;
        this.date = date;
        this.result = result;
        this.examType = examType;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getNamec() {
        return namec;
    }

    public void setNamec(String namec) {
        this.namec = namec;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getExamType() {
        return examType;
    }

    public void setExamType(Integer examType) {
        this.examType = examType;
    }
}
