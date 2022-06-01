package ZTE.dao.student;

import ZTE.dao.BaseDao;
import ZTE.entity.Student;
import ZTE.utils.ExamUtil;

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
        String sql = "INSERT  INTO `ztestudent`(`studentName`,`classId`,`fromSchool`,`education`,`loginCode`,`password`) \n" +
                "VALUES (?,?,?,?,?,?);";
        Object[] param = {
                student.getStudentName(),
                student.getClassId(),
                student.getFromSchool(),
                student.getEducation(),
                student.getLogincode(),
                student.getPassword()
        };
        return executeUpdateInfo(sql, param) > 0;
    }

    /**
     * 获取到当日没有成绩的学生集合
     * @param className 班级名称
     * @param examType 考试类型
     * @param examDate 当前日期
     * @param pageSize 页面尺寸
     * @param currentPage 当前页码
     * @return
     */
    @Override
    public List<Student> getStudentListWithoutResult(String className, int examType, String examDate, int pageSize, int currentPage) {
        List<Student> studentList = new ArrayList<>();
        conn = this.getConnection();
        String sql = "SELECT studentid,studentName,className,studyType FROM ztestudent s\n" +
                "INNER JOIN zteclassInfo c ON s.classId=c.classId\n" +
                "WHERE c.className=?\n" +
                "AND s.studentid NOT IN(\n" +
                "SELECT s.studentid FROM ztestudent s\n" +
                "RIGHT JOIN zteresult r ON s.studentid=r.studentid\n" +
                "WHERE r.examType=?\n" +
                "AND r.examdate=?)\n" +
                "LIMIT ?,?";
        try {
            pstmd = conn.prepareStatement(sql);
            pstmd.setObject(1, className);
            pstmd.setObject(2, examType);
            pstmd.setObject(3, examDate);
            pstmd.setObject(4, (currentPage - 1) * pageSize);
            pstmd.setObject(5, pageSize);
            rs = pstmd.executeQuery();
            while (rs.next()) {
                int studentid = rs.getInt("studentid");
                String studentName = rs.getString("studentName");
                int studyType = rs.getInt("studyType");
                String major = ExamUtil.transMajorIdToName(studyType);
                Student student = new Student(studentid, studentName, className, major);
                studentList.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.closeAll(conn, pstmd, rs);
        }
        return studentList;
    }

    /**
     * 根据班级获取到当日没有成绩的学生数量
     *
     * @param className 班级名称
     * @param examType  考试类型
     * @param examDate  考试日期（当前日期）
     * @return
     */
    @Override
    public int getStudentCountByClassName(String className, int examType, String examDate) {
        int totalCount = 0;
        conn = this.getConnection();
        String sql = "SELECT COUNT(*) FROM ztestudent s\n" +
                "INNER JOIN zteclassInfo c ON s.classId=c.classId\n" +
                "WHERE c.className=?\n" +
                "AND s.studentid NOT IN(\n" +
                "SELECT s.studentid FROM ztestudent s\n" +
                "INNER JOIN zteresult r ON s.studentid=r.studentid\n" +
                "WHERE r.examType=?\n" +
                "AND r.examdate=?)";
        try {
            pstmd = conn.prepareStatement(sql);
            pstmd.setObject(1, className);
            pstmd.setObject(2, examType);
            pstmd.setObject(3, examDate);
            rs = pstmd.executeQuery();
            while (rs.next()) {
                totalCount = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.closeAll(conn, pstmd, rs);
        }
        return totalCount;
    }

    /**
     * 根据班级获取到当日没有记录考勤的学生人数
     * @param className 班级名称
     * @param attDate 当前日期
     * @return
     */
    @Override
    public int getStudentCountByClassName(String className, String attDate) {
        int totalCount = 0;
        conn = this.getConnection();
        String sql = "SELECT COUNT(*) FROM ztestudent s\n" +
                "INNER JOIN zteclassInfo c ON s.classId=c.classId\n" +
                "WHERE c.className=?\n" +
                "AND s.studentid NOT IN(\n" +
                "SELECT s.studentid FROM ztestudent s\n" +
                "INNER JOIN ztecheckwork ch ON s.studentid=ch.studentno\n" +
                "AND ch.ckTime=?)";
        try {
            pstmd = conn.prepareStatement(sql);
            pstmd.setObject(1, className);
            pstmd.setObject(2, attDate);
            rs = pstmd.executeQuery();
            while (rs.next()) {
                totalCount = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.closeAll(conn, pstmd, rs);
        }
        return totalCount;
    }

    /**
     * 获取到当日没有记录考勤的学生集合
     * @param className
     * @param attDate
     * @param pageSize
     * @param currentPage
     * @return
     */
    @Override
    public List<Student> getStudentListWithoutAtt(String className, String attDate, int pageSize, int currentPage) {
        List<Student> studentList = new ArrayList<>();
        conn = this.getConnection();
        String sql = "SELECT s.studentid,s.studentName,c.classname,c.studyType FROM ztestudent s\n" +
                "INNER JOIN zteclassInfo c ON s.classId=c.classId\n" +
                "WHERE c.className=?\n" +
                "AND s.studentid NOT IN(\n" +
                "SELECT studentno FROM ztecheckwork\n" +
                "WHERE ckTime=?)\n" +
                "LIMIT ?,?";
        try {
            pstmd = conn.prepareStatement(sql);
            pstmd.setObject(1, className);
            pstmd.setObject(2, attDate);
            pstmd.setObject(3, (currentPage - 1) * pageSize);
            pstmd.setObject(4, pageSize);
            rs = pstmd.executeQuery();
            while (rs.next()) {
                int studentid = rs.getInt("studentid");
                String studentName = rs.getString("studentName");
                int studyType = rs.getInt("studyType");
                String major = ExamUtil.transMajorIdToName(studyType);
                Student student = new Student(studentid, studentName, className, major);
                studentList.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.closeAll(conn, pstmd, rs);
        }
        return studentList;
    }

}
