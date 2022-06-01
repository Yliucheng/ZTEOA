package ZTE.entity.lidelin;

/**
 * @description 专业信息
 */
public class Major {

    private int id; // 专业Id
    private String major; // 专业名称

    public Major() {
    }

    public Major(int id, String major) {
        this.id = id;
        this.major = major;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

}
