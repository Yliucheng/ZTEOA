package ZTE.dao.yichen.classes;

import ZTE.dao.BaseDao;
import ZTE.entity.yichen.Classes;

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

    //根据查询条件获取所有的班级信息
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
        }finally {
            this.closeAll(con,pstm,rs);
        }
        return classes1;
    }

    //获取班级信息的总数
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
        }finally {
            this.closeAll(con,pstm,rs);
        }
        return count;
    }

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
    //获取查询条件下拉框内容
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
        }finally {
            this.closeAll(con,pstm,rs);
        }
        return StudyTypeList;
    }
    //判断添加班级是否成功
    @Override
    public boolean addClass(String classname, int studytype) {
        con=this.getConnection();
        boolean flag=false;
        ArrayList<Classes> StudyTypeList = new ArrayList<>();
        String sql = "INSERT INTO zteclassinfo(classname,studytype) VALUES(?,?)";
        try {
            pstm = con.prepareStatement(sql);
            pstm.setString(1,classname);
            pstm.setInt(2,studytype);
            int count = pstm.executeUpdate();
            if (count>0){
                flag=true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            this.closeAll(con,pstm,rs);
        }
        return flag;
    }
    //根据id获取班级信息放置到修改页面
    @Override
    public Classes getClassList(int id) {
        con=this.getConnection();
        ArrayList<Classes> StudyTypeList = new ArrayList<>();
        String sql = "SELECT classid,className,studytype FROM zteclassinfo WHERE classid=?";
        Classes classes=null;
        try {
            pstm = con.prepareStatement(sql);
            pstm.setInt(1,id);
            rs = pstm.executeQuery();
            if (rs.next()){
                int classid = rs.getInt("classid");
                String className = rs.getString("className");
                int studytype = rs.getInt("studytype");
                classes = new Classes(classid, className, studytype);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            this.closeAll(con,pstm,rs);
        }
        return classes;
    }
    //修改班级信息
    @Override
    public boolean ModifyClass(Classes classes) {
        con=this.getConnection();
        boolean flag=false;
        String sql = "UPDATE  zteclassinfo SET className=?,studytype=? WHERE classid=?";
        try {
            pstm = con.prepareStatement(sql);
            pstm.setString(1,classes.getClassName());
            pstm.setInt(2,classes.getStudyType());
            pstm.setInt(3,classes.getClassId());
            int count = pstm.executeUpdate();
            if (count>0){
                flag=true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            this.closeAll(con,pstm,rs);
        }
        return flag;
    }
    //判断该班级是否还有学生
    @Override
    public boolean getClassStduentCount(int classid) {
        con=this.getConnection();
        boolean flag=true;
        String sql = "SELECT * FROM ztestudent WHERE classid = ?";
        try {
            pstm = con.prepareStatement(sql);
            pstm.setInt(1,classid);
            rs = pstm.executeQuery();
            if (rs.next()){
                flag=false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            this.closeAll(con,pstm,rs);
        }
        return flag;
    }
    //根据班级ID删除班级信息
    @Override
    public boolean DeleteClass(int classid) {
        con=this.getConnection();
        boolean flag=false;
        String sql = "DELETE FROM zteclassinfo WHERE classid = ?";
        try {
            pstm = con.prepareStatement(sql);
            pstm.setInt(1,classid);
            int count = pstm.executeUpdate();
            if (count>0){
                flag=true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            this.closeAll(con,pstm,rs);
        }
        return flag;
    }
    //班级验证，判断输入内容是否已存在
    @Override
    public boolean PanDuanClassName(String classname) {
        con=this.getConnection();
        String sql="SELECT *\n" +
                "FROM zteclassinfo\n" +
                "WHERE classname=?";
        try {
            pstm=con.prepareStatement(sql);
            pstm.setString(1,classname);
            rs=pstm.executeQuery();
            if (rs.next()){
                return false;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    @Override
    public ArrayList<Classes> getClassList2(Integer majorId) {
        con=this.getConnection();
        ArrayList<Classes> classes1 = new ArrayList<>();
        StringBuffer sb = new StringBuffer("SELECT z.classid,z.classname,z.studytype,m.major\n" +
                "FROM zteclassinfo z\n" +
                "LEFT JOIN ztemajor m ON z.studytype=m.id where 1=1");
        if (majorId!=null&&!"".equals(majorId)){
            sb.append("  and z.studytype=?");
        }
        try {
            pstm = con.prepareStatement(sb.toString());
            if (majorId!=null&&!"".equals(majorId)){
                pstm.setObject(1,majorId);
            }
            rs = pstm.executeQuery();
            while (rs.next()){
                int classid = rs.getInt("classid");
                String classname = rs.getString("classname");
                int studytype = rs.getInt("studytype");
                String major = rs.getString("major");
                Classes classes = new Classes(classid, classname,studytype, major);
                classes1.add(classes);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            this.closeAll(con,pstm,rs);
        }
        return classes1;
    }
}
