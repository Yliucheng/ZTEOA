package ZTE.entity.lidelin;

/**
 * @description 班级信息
 */
public class ClassInfo {

    private int classId; // 班级ID
    private String classname; // 班级名称
    private int studyType; // 专业ID

    public ClassInfo() {
    }

    public ClassInfo(int classId, String classname, int studyType) {
        this.classId = classId;
        this.classname = classname;
        this.studyType = studyType;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public int getStudyType() {
        return studyType;
    }

    public void setStudyType(int studyType) {
        this.studyType = studyType;
    }

}
