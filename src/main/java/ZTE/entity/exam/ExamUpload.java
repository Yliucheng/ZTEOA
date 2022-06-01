package ZTE.entity.exam;

/**
 * 考试成绩上传类，存储考试成绩上传相关数据
 */
public class ExamUpload {
    private  Integer studentId;// 学号
    private String studentName;// 姓名
    private  String examDate;// 考试日期
    private String examType;// 考试类型
    private  String studentResult;// 考试表现

    public ExamUpload() {
    }

    public ExamUpload(Integer studentId, String studentName, String examDate, String examType, String studentResult) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.examDate = examDate;
        this.examType = examType;
        this.studentResult = studentResult;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public String getStudentResult() {
        return studentResult;
    }

    public void setStudentResult(String studentResult) {
        this.studentResult = studentResult;
    }
}
