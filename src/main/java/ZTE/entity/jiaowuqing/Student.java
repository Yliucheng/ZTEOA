package ZTE.entity.jiaowuqing;

/**
 * ztestudent表  学生类
 */
public class Student {
    private Integer studentid;//学号
    private String studentName;//姓名
    private Integer classId;//班级id
    private String fromSchool;//来自院校
    private String education;//学历
    private String logincode;//登录名
    private String password;//密码
    private String major; //专业
    private String className;//班级

    public Student() {
    }

    public Student(Integer studentid, String studentName, Integer classId, String fromSchool, String education, String logincode, String password) {
        this.studentid = studentid;
        this.studentName = studentName;
        this.classId = classId;
        this.fromSchool = fromSchool;
        this.education = education;
        this.logincode = logincode;
        this.password = password;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Student(Integer studentid, String studentName, String major, String className, String fromSchool, String education) {
        this.studentid = studentid;
        this.studentName = studentName;
        this.fromSchool = fromSchool;
        this.education = education;
        this.major = major;
        this.className = className;
    }

    public Student(String studentName, Integer classId, String fromSchool, String education, String logincode, String password) {
        this.studentName = studentName;
        this.classId = classId;
        this.fromSchool = fromSchool;
        this.education = education;
        this.logincode = logincode;
        this.password = password;
    }

    public Integer getStudentid() {
        return studentid;
    }

    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getFromSchool() {
        return fromSchool;
    }

    public void setFromSchool(String fromSchool) {
        this.fromSchool = fromSchool;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getLogincode() {
        return logincode;
    }

    public void setLogincode(String logincode) {
        this.logincode = logincode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
