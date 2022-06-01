package ZTE.entity.liuchenxin;

/**
 * 实体类
 * 市场类型班级
 */
public class MarketClass {
    private int id;//编号
    private String name;//名字
    private String type;//类型

    public MarketClass() {}

    public MarketClass(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
