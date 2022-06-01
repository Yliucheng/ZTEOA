package ZTE.entity.att;

/**
 * 出勤类，存储出勤相关信息
 */
public class AttInfo {
    private Integer id;// 考勤编号
    private String studentName;// 学生姓名
    private String major;//专业
    private String classes;//班级
    private String attDate;// 考勤日期
    private String ckStatus;// 考勤状态

    public AttInfo() {
    }

    public AttInfo(Integer id, String studentName, String major, String classes, String attDate, String ckStatus) {
        this.id = id;
        this.studentName = studentName;
        this.major = major;
        this.classes = classes;
        this.attDate = attDate;
        this.ckStatus = ckStatus;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getAttDate() {
        return attDate;
    }

    public void setAttDate(String attDate) {
        this.attDate = attDate;
    }

    public String getCkStatus() {
        return ckStatus;
    }

    public void setCkStatus(String ckStatus) {
        this.ckStatus = ckStatus;
    }
}
