package ZTE.dao.jiaowuqing.student;

import ZTE.dao.BaseDao;
import ZTE.entity.jiaowuqing.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl extends BaseDao implements StudentDao {
    private Connection conn = null;
    private PreparedStatement pstmd = null;
    private ResultSet rs = null;

    @Override
    public boolean addStudentRegister(Student student) {
        String sql="INSERT  INTO `ztestudent`(`studentName`,`classId`,`fromSchool`,`education`,`loginCode`,`password`) \n" +
                "VALUES (?,?,?,?,?,?);";
        Object[] param = {
          student.getStudentName(),
          student.getClassId(),
          student.getFromSchool(),
          student.getEducation(),
          student.getLogincode(),
          student.getPassword()
        };
        return executeUpdateInfo(sql,param)>0;
    }

    @Override
    public int getStudentTotalCount(Integer zhuanye, Integer banji, String name) {
        int count = -1;
        StringBuffer sb = new StringBuffer("SELECT COUNT(*) FROM ztestudent s\n" +
                "JOIN zteclassinfo c ON s.`classId`=c.`classId`\n" +
                "JOIN ztemajor m ON c.`studyType`=m.`id` WHERE 1=1 ");
        List<Object> params = new ArrayList<>();
        if (zhuanye!=null){
            sb.append("and m.id=? ");
            params.add(zhuanye);
        }
        if (banji!=null){
            sb.append("AND c.`classId`=? ");
            params.add(banji);
        }
        if (name!=null&&!"".equals(name)){
            sb.append("AND s.`studentName` like ? ");
            params.add("%"+name+"%");
        }
        conn=getConnection();
        try {
            pstmd = conn.prepareStatement(sb.toString());
            for (int i = 0; i < params.size(); i++) {
                pstmd.setObject(i+1,params.get(i));
            }
            rs=pstmd.executeQuery();
            while (rs.next()){
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn,pstmd,rs);
        }
        return count;
    }

    @Override
    public List<Student> getStudentListByPage(Integer zhuanye, Integer banji, String name, int currentpage, int pageSize) {
        List<Student> studentList = new ArrayList<>();
        StringBuffer sb = new StringBuffer("SELECT studentid,studentName,m.`major`,c.`className`,fromschool,education FROM ztestudent s\n" +
                " JOIN zteclassinfo c ON s.`classId`=c.`classId`\n" +
                " JOIN ztemajor m ON c.`studyType`=m.`id` where 1=1 ");
        List<Object> params = new ArrayList<>();
        if (zhuanye!=null){
            sb.append("and m.id=? ");
            params.add(zhuanye);
        }
        if (banji!=null){
            sb.append("AND c.`classId`=? ");
            params.add(banji);
        }
        if (name!=null&&!"".equals(name)){
            sb.append("AND s.`studentName`like ? ");
            params.add("%"+name+"%");
        }
        sb.append("LIMIT ?,?");
        params.add((currentpage-1)*pageSize);
        params.add(pageSize);
        conn=getConnection();
        try {
            pstmd = conn.prepareStatement(sb.toString());
            for (int i = 0; i < params.size(); i++) {
                pstmd.setObject(i+1,params.get(i));
            }
            rs=pstmd.executeQuery();
            while (rs.next()){
//                studentid,studentName,m.`major`,c.`className`,fromschool,education
                int studentid = rs.getInt("studentid");
                String studentName = rs.getString("studentName");
                String major = rs.getString("major");
                String className = rs.getString("className");
                String fromschool = rs.getString("fromschool");
                String education = rs.getString("education");
                studentList.add(new Student(studentid,studentName,major,className,fromschool,education));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn,pstmd,rs);
        }
        return studentList;
    }

    @Override
    public boolean deleteStudentById(String id) {
        String sql = "delete from ztestudent where studentid=?";
        Object[] params = {id};
        return executeUpdateInfo(sql,params)>0;
    }

    @Override
    public Student getStudentById(String id) {
        Student student = null;
        String sql = "SELECT studentid,`studentName`,`classId`,`fromSchool`,`education`,`loginCode`,`password`\n" +
                "FROM ztestudent WHERE studentid=?";
        conn=getConnection();
        try {
            pstmd=conn.prepareStatement(sql);
            pstmd.setObject(1,id);
            rs= pstmd.executeQuery();
            while (rs.next()){
                int studentid = rs.getInt("studentid");
                String studentName = rs.getString("studentName");
                int classId = rs.getInt("classId");
                String fromSchool = rs.getString("fromSchool");
                String education = rs.getString("education");
                String loginCode = rs.getString("loginCode");
                String password = rs.getString("password");
                student=new Student(studentid,studentName,classId,fromSchool,education,loginCode,password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn,pstmd,rs);
        }
        return student;
    }

    @Override
    public boolean updateStudentById(Student student) {
        String sql = "UPDATE ztestudent SET studentName=?,classid=?,\n" +
                "fromschool=?,education=?,logincode=?,PASSWORD=?\n" +
                "WHERE studentid=?;";
        Object[] params = {
                student.getStudentName(),
                student.getClassId(),
                student.getFromSchool(),
                student.getEducation(),
                student.getLogincode(),
                student.getPassword(),
                student.getStudentid()
        };
        return executeUpdateInfo(sql,params)>0;
    }

    @Override
    public boolean addStudent(Student student) {
        String sql = "INSERT  INTO `ztestudent`(`studentName`,`classId`,`fromSchool`,`education`,`loginCode`,`password`) \n" +
                "VALUES (?,?,?,?,?,?);\n";
        Object[] params = {
          student.getStudentName(),
          student.getClassId(),
          student.getFromSchool(),
          student.getEducation(),
          student.getLogincode(),
          student.getPassword()
        };
        return executeUpdateInfo(sql,params)>0;
    }

    @Override
    public List<String> getStudentEducationList() {
        List<String> edus = new ArrayList<>();
        String sql = "select distinct education from ztestudent;";
        conn=getConnection();
        try {
            pstmd=conn.prepareStatement(sql);
            rs= pstmd.executeQuery();
            while (rs.next()){
                String education = rs.getString("education");
                edus.add(education);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return edus;
    }

    @Override
    public List<String> getLoginCodeList() {
        List<String> loginCodeList = new ArrayList<>();
        String sql = "select logincode from ztestudent";
        conn=getConnection();
        try {
            pstmd=conn.prepareStatement(sql);
            rs= pstmd.executeQuery();
            while (rs.next()){
                String logincode = rs.getString("logincode");
                loginCodeList.add(logincode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn,pstmd,rs);
        }
        return loginCodeList;
    }

    @Override
    public List<Student> getStudentList(Integer major, Integer classId, String name) {
        List<Student> studentList = new ArrayList<>();
        StringBuffer sql = new StringBuffer("SELECT s.`studentid`,s.`studentName`,m.`major`,c.`className`,\n" +
                "s.`fromSchool`,s.`education` FROM ztestudent s \n" +
                "JOIN zteclassinfo c ON c.`classId`=s.classid\n" +
                "JOIN ztemajor m ON c.`studyType`=m.`id` WHERE 1=1 ");
        List<Object> params = new ArrayList<>();
        if (major!=null){
            sql.append("AND m.`id`=? ");
            params.add(major);
        }
        if (classId!=null){
            sql.append("AND c.`classId`=? ");
            params.add(classId);
        }
        if (name!=null&&!"".equals(name)){
            sql.append("AND s.`studentName` LIKE ? ");
            params.add("%"+major+"%");
        }
        conn=getConnection();
        try {
            pstmd = conn.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); i++) {
                pstmd.setObject(i+1,params.get(i));
            }
            rs= pstmd.executeQuery();
            while (rs.next()){
                int studentid = rs.getInt("studentid");
                String studentName = rs.getString("studentName");
                String majorName = rs.getString("major");
                String className = rs.getString("className");
                String fromschool = rs.getString("fromschool");
                String education = rs.getString("education");
                studentList.add(new Student(studentid,studentName,majorName,className,fromschool,education));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn,pstmd,rs);
        }

        return studentList;
    }
}
