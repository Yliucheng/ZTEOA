package ZTE.entity.yichen;

/**
 * 班级实体类 包含班级表和专业表
 */
public class Classes {
    private Integer classId;
    private String className;
    private Integer studyType;
    private Integer majorId;
    private String majorName;

    public Classes() {
    }

    public Classes(String className, Integer studyType) {
        this.className = className;
        this.studyType = studyType;
    }

    public Classes(Integer classId, String className, Integer studyType) {
        this.classId = classId;
        this.className = className;
        this.studyType = studyType;
    }

    public Classes(Integer classId, String className, String majorName) {
        this.classId = classId;
        this.className = className;
        this.majorName = majorName;
    }

    public Classes(Integer classId, String className, Integer studyType, String majorName) {
        this.classId = classId;
        this.className = className;
        this.studyType = studyType;
        this.majorName = majorName;
    }

    public Classes(Integer majorId, String majorName) {
        this.majorId = majorId;
        this.majorName = majorName;
    }

    public Classes(Integer classId, String className, Integer studyType, Integer majorId, String majorName) {
        this.classId = classId;
        this.className = className;
        this.studyType = studyType;
        this.majorId = majorId;
        this.majorName = majorName;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getStudyType() {
        return studyType;
    }

    public void setStudyType(Integer studyType) {
        this.studyType = studyType;
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }
}
