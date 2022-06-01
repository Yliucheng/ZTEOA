package ZTE.entity;

/**
 * 管理员用户实体类
 */
public class Admin {
    private Integer id;         //用户id
    private String logincode;   //账号
    private Integer roleId;     //角色id
    private String password;    //密码
    private String createTime;  //创建时间

    public Admin() {
    }

    public Admin(Integer id, String logincode, Integer roleId, String password, String createTime) {
        this.id = id;
        this.logincode = logincode;
        this.roleId = roleId;
        this.password = password;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogincode() {
        return logincode;
    }

    public void setLogincode(String logincode) {
        this.logincode = logincode;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
