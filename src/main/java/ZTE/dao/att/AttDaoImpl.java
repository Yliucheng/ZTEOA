package ZTE.dao.att;

import ZTE.dao.BaseDao;
import ZTE.entity.QueryParams;
import ZTE.entity.att.AttInfo;
import ZTE.entity.att.AttUpload;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AttDaoImpl extends BaseDao implements AttDao {
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    @Override
    public int getAttCountTodayByClass(String attDate, String className) {
        int count = 0;
        conn = this.getConnection();
        String sql = "SELECT COUNT(*) FROM ztestudent s\n" +
                "RIGHT JOIN ztecheckwork ch ON s.studentid=ch.studentno\n" +
                "INNER JOIN zteclassinfo c ON s.classId=c.classId\n" +
                "WHERE ch.ckTime=?\n" +
                "AND c.classname=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1, attDate);
            pstmt.setObject(2, className);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.closeAll(conn, pstmt, rs);
        }
        return count;
    }

    @Override
    public boolean addAttInfo(String result, String[] studentNoData, String attDate) {
        boolean flag = false;
        String sql = "INSERT INTO ztecheckwork (studentno,ckTime,ckstatu)\n" +
                "VALUES (?,?,?)";
        int count = 0;// 执行sql语句成功的数量
        for (String studentNoDatum : studentNoData) {
            Object[] params = {
                    studentNoDatum,
                    attDate,
                    result
            };
            int i = this.executeUpdateInfo(sql, params);
            if (i > 0) {
                count++;
            }
        }
        // 如果执行成功的sql语句数量等于学号数组的数量，说明全部执行成功，返回true
        if (count == studentNoData.length) {
            flag = true;
        }
        return flag;
    }

    @Override
    public int getTotalAttCount(QueryParams queryParams) {
        int count = 0;
        conn = this.getConnection();
        List<Object> params = new ArrayList<>();
        params.add(queryParams.getClassName());
        StringBuffer sql = new StringBuffer("SELECT COUNT(*) FROM ztecheckwork ch\n" +
                "INNER JOIN ztestudent s ON s.studentid=ch.studentno\n" +
                "INNER JOIN zteclassinfo c ON s.classId=c.classId\n" +
                "WHERE c.classname=?\n");
        if (queryParams.getNamec() != null && !"".equals(queryParams.getNamec())) {
            sql.append("AND s.studentName like ?\n");
            params.add("%" + queryParams.getNamec() + "%");
        }
        if (queryParams.getDate() != null && !"".equals(queryParams.getDate())) {
            sql.append("AND ch.ckTime=?\n");
            params.add(queryParams.getDate());
        }
        if (queryParams.getResult() != null && !"".equals(queryParams.getResult())) {
            sql.append("AND ch.ckstatu=?\n");
            params.add(queryParams.getResult());
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
            this.closeAll(conn, pstmt, rs);
        }
        return count;
    }

    @Override
    public List<AttInfo> getAttInfoList(QueryParams queryParams, int pageSize, int currentPage) {
        List<AttInfo> attInfoList = new ArrayList<>();
        int count = 0;
        conn = this.getConnection();
        List<Object> params = new ArrayList<>();
        params.add(queryParams.getClassName());
        StringBuffer sql = new StringBuffer("SELECT ch.id,s.studentName,m.major,c.className,ch.ckTime,ch.ckstatu FROM ztecheckwork ch\n" +
                "INNER JOIN ztestudent s ON s.studentid=ch.studentno\n" +
                "INNER JOIN zteclassinfo c ON s.classId=c.classId\n" +
                "INNER JOIN ztemajor m ON m.id=c.studyType\n" +
                "WHERE c.classname=?\n");
        if (queryParams.getNamec() != null && !"".equals(queryParams.getNamec())) {
            sql.append("AND s.studentName like ?\n");
            params.add("%" + queryParams.getNamec() + "%");
        }
        if (queryParams.getDate() != null && !"".equals(queryParams.getDate())) {
            sql.append("AND ch.ckTime=?\n");
            params.add(queryParams.getDate());
        }
        if (queryParams.getResult() != null && !"".equals(queryParams.getResult())) {
            sql.append("AND ch.ckstatu=?\n");
            params.add(queryParams.getResult());
        }
        sql.append("LIMIT ?,?");
        params.add((currentPage - 1) * pageSize);
        params.add(pageSize);
        try {
            pstmt = conn.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i));
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String studentName = rs.getString("studentName");
                String ckTime = rs.getString("ckTime");
                String major = rs.getString("major");
                String className = rs.getString("className");
                String ckstatu = rs.getString("ckstatu");
                AttInfo attInfo = new AttInfo(id, studentName, major, className, ckTime, ckstatu);
                attInfoList.add(attInfo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.closeAll(conn, pstmt, rs);
        }
        return attInfoList;
    }

    @Override
    public List<AttInfo> getAttInfoList(QueryParams queryParams) {
        List<AttInfo> attInfoList = new ArrayList<>();
        int count = 0;
        conn = this.getConnection();
        List<Object> params = new ArrayList<>();
        params.add(queryParams.getClassName());
        StringBuffer sql = new StringBuffer("SELECT ch.id,s.studentName,m.major,c.className,ch.ckTime,ch.ckstatu FROM ztecheckwork ch\n" +
                "INNER JOIN ztestudent s ON s.studentid=ch.studentno\n" +
                "INNER JOIN zteclassinfo c ON s.classId=c.classId\n" +
                "INNER JOIN ztemajor m ON m.id=c.studytype\n" +
                "WHERE c.classname=?\n");
        if (queryParams.getNamec() != null && !"".equals(queryParams.getNamec())) {
            sql.append("AND s.studentName=?\n");
            params.add(queryParams.getNamec());
        }
        if (queryParams.getDate() != null && !"".equals(queryParams.getDate())) {
            sql.append("AND ch.ckTime=?\n");
            params.add(queryParams.getDate());
        }
        if (queryParams.getResult() != null && !"".equals(queryParams.getResult())) {
            sql.append("AND ch.ckstatu=?\n");
            params.add(queryParams.getResult());
        }
        try {
            pstmt = conn.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i));
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String studentName = rs.getString("studentName");
                String ckTime = rs.getString("ckTime");
                String major = rs.getString("major");
                String className = rs.getString("className");
                String ckstatu = rs.getString("ckstatu");
                AttInfo attInfo = new AttInfo(id, studentName, major, className, ckTime, ckstatu);
                attInfoList.add(attInfo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.closeAll(conn, pstmt, rs);
        }
        return attInfoList;
    }

    @Override
    public boolean changeAttInfo(String attDate, String ckStatus, int id) {
        boolean flag = false;
        String sql = "UPDATE ztecheckwork SET ckTime=?,ckstatu=?\n" +
                "WHERE Id=?";
        Object[] params = {attDate, ckStatus, id};
        int count = this.executeUpdateInfo(sql, params);
        if (count > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean delAttInfo(int id) {
        boolean flag = false;
        String sql = "DELETE FROM ztecheckwork WHERE Id=?";
        Object[] params = {id};
        int count = this.executeUpdateInfo(sql, params);
        if (count > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public void addAtt(AttUpload upload) {
        String sql = "INSERT INTO ztecheckwork (studentno,ckTime,ckstatu)\n" +
                "VALUES (?,?,?)";
        Object[] params = {
                upload.getStudentNo(),
                upload.getCkTime(),
                upload.getCkstatu()
        };
        this.executeUpdateInfo(sql,params);
    }
}
