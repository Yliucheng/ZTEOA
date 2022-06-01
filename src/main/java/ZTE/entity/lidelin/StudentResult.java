package ZTE.entity.lidelin;

/**
 * @description 修改学生作业成绩用
 */
public class StudentResult {

    private int id; // id
    private String date; // 日期
    private String studentResult; // 成绩

    public StudentResult() {
    }

    public StudentResult(String studentResult) {
        this.studentResult = studentResult;
    }

    public StudentResult(int id, String date, String studentResult) {
        this.id = id;
        this.date = date;
        this.studentResult = studentResult;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStudentResult() {
        return studentResult;
    }

    public void setStudentResult(String studentResult) {
        this.studentResult = studentResult;
    }

}
