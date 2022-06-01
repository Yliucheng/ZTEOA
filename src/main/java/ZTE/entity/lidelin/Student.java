package ZTE.entity.lidelin;

/**
 * @description 学生信息
 */
public class Student {

    private int studentId; // 学号
    private String studentName; // 姓名
    private String major; // 专业
    private int classId; // 班级编号
    private String className; // 班级名称
    private String fromSchool; // 毕业院校
    private String education; // 学历
    private String loginCode; // 登录账号
    private String password; // 密码

    public Student() {
    }

    public Student(int studentId, String studentName, String major, int classId, String className, String fromSchool, String education, String loginCode, String password) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.major = major;
        this.classId = classId;
        this.className = className;
        this.fromSchool = fromSchool;
        this.education = education;
        this.loginCode = loginCode;
        this.password = password;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
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

    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
