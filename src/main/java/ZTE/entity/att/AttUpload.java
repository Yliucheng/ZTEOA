package ZTE.entity.att;

public class AttUpload {
    private Integer studentNo;// 学号
    private String studentName;// 姓名
    private String ckTime;// 考勤时间
    private String ckstatu;// 考勤状态

    public AttUpload() {
    }

    public AttUpload(Integer studentNo, String ckTime, String ckstatu) {
        this.studentNo = studentNo;
        this.ckTime = ckTime;
        this.ckstatu = ckstatu;
    }

    public AttUpload(Integer studentNo, String studentName, String ckTime, String ckstatu) {
        this.studentNo = studentNo;
        this.studentName = studentName;
        this.ckTime = ckTime;
        this.ckstatu = ckstatu;
    }

    public Integer getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(Integer studentNo) {
        this.studentNo = studentNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCkTime() {
        return ckTime;
    }

    public void setCkTime(String ckTime) {
        this.ckTime = ckTime;
    }

    public String getCkstatu() {
        return ckstatu;
    }

    public void setCkstatu(String ckstatu) {
        this.ckstatu = ckstatu;
    }
}
