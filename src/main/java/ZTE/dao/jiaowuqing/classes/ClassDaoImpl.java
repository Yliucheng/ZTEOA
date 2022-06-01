package ZTE.dao.jiaowuqing.classes;

import ZTE.dao.BaseDao;
import ZTE.entity.jiaowuqing.Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassDaoImpl extends BaseDao implements ClassDao {
    Connection con = null;
    PreparedStatement pstm=null;
    ResultSet rs = null;

    @Override
    public List<Classes> getClassList() {
        List<Classes> classesList = new ArrayList<>();
        String sql = "SELECT classid,classname,studyType FROM zteclassinfo;";
        con=getConnection();
        try {
            pstm=con.prepareStatement(sql);
            rs=pstm.executeQuery();
            while (rs.next()){
                int classid = rs.getInt("classid");
                String classname = rs.getString("classname");
                int studyType = rs.getInt("studyType");
                classesList.add(new Classes(classid,classname,studyType,null,null));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(con,pstm,rs);
        }
        return classesList;
    }

    @Override
    public List<Classes> getClassListByMajorId(Integer majorId) {
        if (majorId==null){
            return null;
        }
        List<Classes> classesList = new ArrayList<>();
        StringBuffer sb= new StringBuffer("SELECT classId,className,studyType FROM zteclassinfo c\n" +
                "JOIN ztemajor m ON c.`studyType`=m.`id` WHERE 1=1 ");
        sb.append("AND m.`id`=?");
        con=getConnection();
        try {
            pstm=con.prepareStatement(sb.toString());
            if (majorId!=null){
                pstm.setInt(1,majorId);
            }
            rs= pstm.executeQuery();
            while (rs.next()){
                int classId = rs.getInt("classId");
                String className = rs.getString("className");
                int studyType = rs.getInt("studyType");
                classesList.add(new Classes(classId,className,studyType,null));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(con,pstm,rs);
        }
        return classesList;
    }
}
