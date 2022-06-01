package ZTE.entity.jiaowuqing;

/**
 * major表 专业类
 */
public class Major {
    private Integer id;
    private String major;

    public Major() {
    }

    public Major(Integer id, String major) {
        this.id = id;
        this.major = major;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
