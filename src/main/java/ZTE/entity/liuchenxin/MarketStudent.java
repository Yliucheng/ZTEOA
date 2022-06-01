package ZTE.entity.liuchenxin;
/**
 * 实体类
 * 市场类型学生
 */
public class MarketStudent {
    private int id;//编号
    private String name;//姓名
    private String className;//班级名称
    private String stuType;//学生类型（班级类型）
    private int classId;//所属班级编号
    private String fromSchool;//学校
    private String education;//学历
    private String phone;//电话
    private String qq;//QQ号
    private String xingge;//性格
    private String beizhu;//备注
    private String willTrain;//培训意向
    private String createTime;//创建时间
    private String createAuthorId;//创建人
    private String modifyAuthor;//修改人
    private String modifyAuthorTime;//修改时间

    public MarketStudent() {}

    public MarketStudent(int id, String name, String className, String stuType, String fromSchool, String education, String phone, String qq, String xingge, String beizhu, String willTrain, String createTime, String createAuthorId, String modifyAuthor, String modifyAuthorTime) {
        this.id = id;
        this.name = name;
        this.className = className;
        this.stuType = stuType;
        this.fromSchool = fromSchool;
        this.education = education;
        this.phone = phone;
        this.qq = qq;
        this.xingge = xingge;
        this.beizhu = beizhu;
        this.willTrain = willTrain;
        this.createTime = createTime;
        this.createAuthorId = createAuthorId;
        this.modifyAuthor = modifyAuthor;
        this.modifyAuthorTime = modifyAuthorTime;
    }

    public MarketStudent(int id, String name, int classId, String fromSchool, String education, String phone, String qq, String xingge, String beizhu, String willTrain, String createTime, String createAuthorId, String modifyAuthor, String modifyAuthorTime) {
        this.id = id;
        this.name = name;
        this.classId = classId;
        this.fromSchool = fromSchool;
        this.education = education;
        this.phone = phone;
        this.qq = qq;
        this.xingge = xingge;
        this.beizhu = beizhu;
        this.willTrain = willTrain;
        this.createTime = createTime;
        this.createAuthorId = createAuthorId;
        this.modifyAuthor = modifyAuthor;
        this.modifyAuthorTime = modifyAuthorTime;
    }

    public MarketStudent(String name, String stuType, int classId, String fromSchool, String education, String phone, String qq, String xingge, String beizhu, String willTrain, String createAuthorId, String createTime) {
        this.name = name;
        this.stuType = stuType;
        this.classId = classId;
        this.fromSchool = fromSchool;
        this.education = education;
        this.phone = phone;
        this.qq = qq;
        this.xingge = xingge;
        this.beizhu = beizhu;
        this.willTrain = willTrain;
        this.createTime = createTime;
        this.createAuthorId = createAuthorId;
    }

    public MarketStudent(String name, String className, String stuType, String fromSchool, String education, String phone, String qq, String xingge, String beizhu, String willTrain, String createAuthorId, String createTime) {
        this.name = name;
        this.className = className;
        this.stuType = stuType;
        this.fromSchool = fromSchool;
        this.education = education;
        this.phone = phone;
        this.qq = qq;
        this.xingge = xingge;
        this.beizhu = beizhu;
        this.willTrain = willTrain;
        this.createTime = createTime;
        this.createAuthorId = createAuthorId;
    }

    public MarketStudent(int id, String name, String className, String stuType, int classId, String fromSchool, String education, String phone, String qq, String xingge, String beizhu, String willTrain, String createTime, String createAuthorId) {
        this.id = id;
        this.name = name;
        this.className = className;
        this.stuType = stuType;
        this.classId = classId;
        this.fromSchool = fromSchool;
        this.education = education;
        this.phone = phone;
        this.qq = qq;
        this.xingge = xingge;
        this.beizhu = beizhu;
        this.willTrain = willTrain;
        this.createTime = createTime;
        this.createAuthorId = createAuthorId;
    }

    public MarketStudent(String name, int classId, String fromSchool, String education, String phone, String qq, String xingge, String beizhu, String willTrain, String modifyTime, String modifyAdmin) {
        this.name = name;
        this.classId = classId;
        this.fromSchool = fromSchool;
        this.education = education;
        this.phone = phone;
        this.qq = qq;
        this.xingge = xingge;
        this.beizhu = beizhu;
        this.willTrain = willTrain;
        this.modifyAuthor=modifyAdmin;
        this.modifyAuthorTime=modifyTime;
    }

    public int getId() {
        return id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStuType() {
        return stuType;
    }

    public void setStuType(String stuType) {
        this.stuType = stuType;
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

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getXingge() {
        return xingge;
    }

    public void setXingge(String xingge) {
        this.xingge = xingge;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    public String getWillTrain() {
        return willTrain;
    }

    public void setWillTrain(String willTrain) {
        this.willTrain = willTrain;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateAuthorId() {
        return createAuthorId;
    }

    public void setCreateAuthorId(String createAuthorId) {
        this.createAuthorId = createAuthorId;
    }

    public String getModifyAuthor() {
        return modifyAuthor;
    }

    public void setModifyAuthor(String modifyAuthor) {
        this.modifyAuthor = modifyAuthor;
    }

    public String getModifyAuthorTime() {
        return modifyAuthorTime;
    }

    public void setModifyAuthorTime(String modifyAuthorTime) {
        this.modifyAuthorTime = modifyAuthorTime;
    }
}
