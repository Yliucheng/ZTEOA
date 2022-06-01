package ZTE.service.jiaowuqing.admin;

import ZTE.entity.Admin;

public interface AdminService {
    /**
     * 根据传递过来的参数查询数据库是否有这个用户
     * @param username 用户名
     * @param password 密码
     * @return 有则返回对象，无则返回null
     */
    Admin login(String username, String password);
    /**
     * 根据传递过来的参数查询数据库是否有这个数据库
     * @param username 用户名
     * @return 有则返回对象，无则返回null
     */
    Admin login(String username);
}
