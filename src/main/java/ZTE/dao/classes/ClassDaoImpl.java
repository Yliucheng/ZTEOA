package ZTE.dao.classes;

import ZTE.dao.BaseDao;
import ZTE.entity.Classes;
import ZTE.entity.Major;
import ZTE.utils.ExamUtil;

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
    public ArrayList<Classes> getClassList(Integer majorIds, Integer currentPageNo, Integer pageSize) {
        con=this.getConnection();
        ArrayList<Classes> classes1 = new ArrayList<>();
        ArrayList<Object> classes2 = new ArrayList<>();
        StringBuffer sb = new StringBuffer("SELECT z.classid,z.classname,z.studytype,m.major\n" +
                "FROM zteclassinfo z\n" +
                "LEFT JOIN ztemajor m ON z.studytype=m.id where 1=1");
        if (majorIds!=null&&!"".equals(majorIds)){
            sb.append("  and z.studytype=?");
            classes2.add(majorIds);
        }
        int a=(currentPageNo-1)*pageSize;
        sb.append("  limit ?,?");
        classes2.add(a);
        classes2.add(pageSize);
        try {
            pstm = con.prepareStatement(sb.toString());
            for (int i = 0; i < classes2.size(); i++) {
                pstm.setObject(i+1,classes2.get(i));
            }
            rs = pstm.executeQuery();
            while (rs.next()){
                int classid = rs.getInt("classid");
                String classname = rs.getString("classname");
                int studytype = rs.getInt("studytype");
                String major = rs.getString("major");
                Classes classes = new Classes(classid, classname, studytype, major);
                classes1.add(classes);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return classes1;
    }

    @Override
    public int getClassListCount(Integer majorIds) {
        con=this.getConnection();
        int count=0;
        ArrayList<Classes> classes1 = new ArrayList<>();
        StringBuffer sb = new StringBuffer("SELECT count(*)\n" +
                "FROM zteclassinfo z\n" +
                "LEFT JOIN ztemajor m ON z.studytype=m.id where 1=1");
        if (majorIds!=null&&!"".equals(majorIds)){
            sb.append("  and z.studytype=?");
        }
        try {
            pstm = con.prepareStatement(sb.toString());
            if (majorIds!=null&&!"".equals(majorIds)){
                pstm.setObject(1,majorIds);
            }
            rs = pstm.executeQuery();
            while (rs.next()){
                count=rs.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    @Override
    public List<Classes> getClassList(Integer majorType) {
        List<Classes> classesList = new ArrayList<>();
        StringBuffer sql = new StringBuffer("SELECT classid,classname,studyType FROM zteclassinfo\n");
        if(majorType!=null&&majorType!=0){
            sql.append("where studyType = ?");
        }
        con=getConnection();
        try {
            pstm=con.prepareStatement(sql.toString());
            if(majorType!=null&&majorType!=0){
                pstm.setObject(1,majorType);
            }
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
    public ArrayList<Classes> getStudyTypeAll() {
        con=this.getConnection();
        int count=0;
        ArrayList<Classes> StudyTypeList = new ArrayList<>();
        StringBuffer sb = new StringBuffer("SELECT id,major\n" +
                "FROM ztemajor");

        try {
            pstm = con.prepareStatement(sb.toString());
            rs = pstm.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String major = rs.getString("major");
                Classes classes = new Classes(id, major);
                StudyTypeList.add(classes);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return StudyTypeList;
    }

    @Override
    public boolean addClass(String classname, int studytype) {
        con=this.getConnection();
        String sql = "INSERT INTO zteclassinfo(classname,studytype) VALUES(?,?)";
        Object[] params = {
                classname,
                studytype
        };
        return executeUpdateInfo(sql,params)>0;
    }

    @Override
    public List<Major> getMajorList() {
        List<Major> majorList = new ArrayList<>();
        con = this.getConnection();
        String sql = "SELECT DISTINCT studyType FROM zteclassinfo";
        try {
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            while(rs.next()){
                int majorId = rs.getInt(1);
                String majorName = ExamUtil.transMajorIdToName(majorId);
                Major major = new Major(majorId,majorName);
                majorList.add(major);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return majorList;
    }
}
