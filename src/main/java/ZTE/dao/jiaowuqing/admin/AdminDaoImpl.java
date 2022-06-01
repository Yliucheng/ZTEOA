package ZTE.dao.jiaowuqing.admin;

import ZTE.dao.BaseDao;
import ZTE.entity.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDaoImpl extends BaseDao implements AdminDao {
    private Connection conn = null;
    private PreparedStatement pstmd = null;
    private ResultSet rs = null;

    @Override
    public Admin login(String username) {
        Admin admin =null;
        String sql ="select logincode,password from zteadmin where logincode=?";
        if (username!=null){}
        conn=getConnection();
        try {
            pstmd=conn.prepareStatement(sql);
            pstmd.setString(1,username);
            rs= pstmd.executeQuery();
            while (rs.next()){
                String logincode = rs.getString("logincode");
                String password1 = rs.getString("password");
                admin=new Admin(null,logincode,null,password1,null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn,pstmd,rs);
        }
        return admin;
    }

    @Override
    public Admin login(String username,String password) {
        Admin admin =null;
        String sql ="select logincode,password from zteadmin where logincode=? and password=?";
        if (username!=null){}
        conn=getConnection();
        try {
            pstmd=conn.prepareStatement(sql);
            pstmd.setString(1,username);
            pstmd.setString(2,password);
            rs= pstmd.executeQuery();
            while (rs.next()){
                String logincode = rs.getString("logincode");
                String password1 = rs.getString("password");
                admin=new Admin(null,logincode,null,password1,null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn,pstmd,rs);
        }
        return admin;
    }
}
