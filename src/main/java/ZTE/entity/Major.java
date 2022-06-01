package ZTE.entity;

/**
 * 专业类，主要用于存储数据进行回显
 */
public class Major {
    private Integer majorId;// 专业ID
    private String majorName;// 专业名称

    public Major() {
    }

    public Major(Integer majorId, String majorName) {
        this.majorId = majorId;
        this.majorName = majorName;
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

    public void setMajorName(String major) {
        this.majorName = major;
    }
}
