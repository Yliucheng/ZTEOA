package ZTE.dao.lidelin.student.impl;

import ZTE.dao.BaseDao;
import ZTE.dao.lidelin.student.WorkRecordDao;
import ZTE.entity.lidelin.ClassInfo;
import ZTE.entity.lidelin.Major;
import ZTE.entity.lidelin.Student;
import ZTE.entity.lidelin.WorkInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName WorkRecordDaoImpl
 * @Description 作业完成情况录入Dao实现
 * @Version 1.0
 */
public class WorkRecordDaoImpl extends BaseDao implements WorkRecordDao {

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    @Override
    public List<Major> getMajorList() {
        List<Major> majorList = new ArrayList<>();
        conn = super.getConnection();
        String sql = "select zm.id, zm.major\n" +
                "from ztemajor zm;\n";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String major = rs.getString("major");
                majorList.add(new Major(id, major));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            super.closeAll(conn, pstmt, rs);
        }
        return majorList;
    }

    @Override
    public List<ClassInfo> getClassInfoList(String major) {
        List<ClassInfo> classInfoList = new ArrayList<>();
        conn = super.getConnection();
        StringBuffer sql = new StringBuffer("select zci.classId, zci.className, zci.studyType\n" +
                "from zteclassinfo zci\n" +
                "         inner join ztemajor zm on zci.studyType = zm.id\n" +
                "where 1 = 1\n");
        if (major != null && !"".equals(major)) {
            sql.append("  and zm.major = ?;");
        }
        try {
            pstmt = conn.prepareStatement(sql.toString());
            if (major != null && !"".equals(major)) {
                pstmt.setString(1, major);
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int classId = rs.getInt("classId");
                String className = rs.getString("className");
                int studyType = rs.getInt("studyType");
                ClassInfo classInfo = new ClassInfo(classId, className, studyType);
                classInfoList.add(classInfo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            super.closeAll(conn, pstmt, rs);
        }
        return classInfoList;
    }

    @Override
    public int getTotalCount(String major, String className) {
        int count = 0;
        conn = super.getConnection();
        StringBuffer sql = new StringBuffer("select count(*)\n" +
                "from ztestudent zs\n" +
                "         inner join zteclassinfo zci on zs.classId = zci.classId\n" +
                "         inner join ztemajor zm on zci.studyType = zm.id\n" +
                "where zs.studentid not in (select zs2.studentid\n" +
                "                           from ztestudent zs2\n" +
                "                                    inner join ztecheckjob zcj on zs2.studentid = zcj.studentNo\n" +
                "                           where to_days(zcj.ckTime) = to_days(now()))\n");
        List<Object> params = new ArrayList<>();
        if (major != null && !"".equals(major)) {
            sql.append("  and major = ?\n");
            params.add(major);
        }
        if (className != null && !"".equals(className)) {
            sql.append("  and zci.className = ?;");
            params.add(className);
        }
        try {
            pstmt = conn.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i));
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            super.closeAll(conn, pstmt, rs);
        }
        return count;
    }

    @Override
    public List<Student> getStudentList(String major, String className, int currentPageCount, int pageSize) {
        List<Student> studentList = new ArrayList<>();
        conn = super.getConnection();
        StringBuffer sql = new StringBuffer("select zs.studentid,\n" +
                "       zs.studentName,\n" +
                "       zm.major,\n" +
                "       zs.classId,\n" +
                "       zci.className,\n" +
                "       zs.fromSchool,\n" +
                "       zs.education,\n" +
                "       zs.loginCode,\n" +
                "       zs.password\n" +
                "from ztestudent zs\n" +
                "         inner join zteclassinfo zci on zs.classId = zci.classId\n" +
                "         inner join ztemajor zm on zci.studyType = zm.id\n" +
                "where zs.studentid not in (select zs2.studentid\n" +
                "                           from ztestudent zs2\n" +
                "                                    inner join ztecheckjob zcj on zs2.studentid = zcj.studentNo\n" +
                "                           where to_days(zcj.ckTime) = to_days(now()))\n");
        List<Object> params = new ArrayList<>();
        if (major != null && !"".equals(major)) {
            sql.append("  and major = ?\n");
            params.add(major);
        }
        if (className != null && !"".equals(className)) {
            sql.append("  and zci.className = ?\n");
            params.add(className);
        }
        sql.append("limit ?,?;");
        params.add((currentPageCount - 1) * pageSize);
        params.add(pageSize);
        try {
            pstmt = conn.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i));
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int studentId = rs.getInt("studentId");
                String studentName = rs.getString("studentName");
                String major1 = rs.getString("major");
                int classId = rs.getInt("classId");
                String className1 = rs.getString("className");
                String fromSchool = rs.getString("fromSchool");
                String education = rs.getString("education");
                String loginCode = rs.getString("loginCode");
                String password = rs.getString("password");
                Student student = new Student(studentId, studentName, major1, classId, className1, fromSchool, education,
                        loginCode,
                        password);
                studentList.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            super.closeAll(conn, pstmt, rs);
        }
        return studentList;
    }

    @Override
    public boolean getJExam(String[] studentNoData, String results) {
        int count = 0;
        for (int i = 0; i < studentNoData.length; i++) {
            String sql = "insert into ztecheckjob(studentNo, ckTime, ckstatu)\n" +
                    "values (?, now(), ?);";
            Object[] params = {studentNoData[i], results};
            count = super.executeUpdateInfo(sql, params);
        }
        if (count > 0) {
            return true;
        }
        return false;
    }

    @Override
    public int getDeptIdByDepName(String studentName) {
        int studentId = 0;
        conn = super.getConnection();
        String sql = "select zs.studentid from ztestudent zs where zs.studentName = ?;";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, studentName);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                studentId = rs.getInt("studentid");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return studentId;
    }

    @Override
    public boolean importWorkInfo(WorkInfo workInfo, int studentId) {
        String sql = "insert into ztecheckjob (id, studentNo, ckTime, ckstatu) values(?, ?, ?, ?); ";
        Object[] params = {
                workInfo.getId(),
                studentId,
                workInfo.getCktime(),
                workInfo.getCkstatu()
        };
        int count = super.executeUpdateInfo(sql, params);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

}
