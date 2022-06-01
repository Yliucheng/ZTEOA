package ZTE.entity;

/**
 * zteclass表班级实体类
 */
public class Classes {
    private Integer classId;// 班级编号
    private String className;// 班级名称
    private Integer studyType;// 学习类型
    private Integer majorId;// 专业编号
    private String majorName;// 专业名称

    public Classes() {
    }

    public Classes(Integer classId, String className, Integer majorId, String majorName) {
        this.classId = classId;
        this.className = className;
        this.majorId = majorId;
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

    public Classes(String className, Integer studyType) {
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

    @Override
    public String toString() {
        return "{" +
                "classId:" + classId +
                ", className:'" + className + '\'' +
                ", studyType:" + studyType +
                '}';
    }
}
