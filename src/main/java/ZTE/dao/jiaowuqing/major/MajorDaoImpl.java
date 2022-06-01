package ZTE.dao.jiaowuqing.major;

import ZTE.dao.BaseDao;
import ZTE.entity.jiaowuqing.Major;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MajorDaoImpl extends BaseDao implements MajorDao{
    private Connection conn = null;
    private PreparedStatement pstmd = null;
    private ResultSet rs = null;
    @Override
    public List<Major> getMajorList() {
        List<Major> majorList = new ArrayList<>();
        String sql = "select id,major from ztemajor";
        conn=getConnection();
        try {
            pstmd=conn.prepareStatement(sql);
            rs= pstmd.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String major = rs.getString("major");
                majorList.add(new Major(id,major));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn,pstmd,rs);
        }
        return majorList;
    }
}
