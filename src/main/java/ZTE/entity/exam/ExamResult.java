package ZTE.entity.exam;

/**
 * 考试成绩类，存放成绩相关信息
 */
public class ExamResult {
    private Integer examId;// 成绩编号
    private String studentName;// 学生姓名
    private String examDate;// 考试日期
    private String examTypeNama;// 考试类型
    private String result;// 成绩评级
    private Integer score;// 成绩得分

    public ExamResult() {
    }

    public ExamResult(Integer examId, String studentName, String examDate, String examTypeNama, Integer score) {
        this.examId = examId;
        this.studentName = studentName;
        this.examDate = examDate;
        this.examTypeNama = examTypeNama;
        this.score = score;
    }

    public ExamResult(Integer examId, String studentName, String examDate, String examTypeNama, String result, Integer score) {
        this.examId = examId;
        this.studentName = studentName;
        this.examDate = examDate;
        this.examTypeNama = examTypeNama;
        this.result = result;
        this.score = score;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
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

    public String getExamTypeNama() {
        return examTypeNama;
    }

    public void setExamTypeNama(String examTypeNama) {
        this.examTypeNama = examTypeNama;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
