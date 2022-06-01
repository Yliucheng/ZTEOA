package ZTE.service.jiaowuqing.admin;

import ZTE.dao.jiaowuqing.admin.AdminDao;
import ZTE.dao.jiaowuqing.admin.AdminDaoImpl;
import ZTE.entity.Admin;

public class AdminServicesImpl implements AdminService {
    AdminDao dao = new AdminDaoImpl();
    @Override
    public Admin login(String username,String password) {
        return dao.login(username,password);
    }

    @Override
    public Admin login(String username) {
        return dao.login(username);
    }
}
