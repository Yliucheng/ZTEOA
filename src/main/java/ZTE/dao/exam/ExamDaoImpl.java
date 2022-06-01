package ZTE.dao.exam;

import ZTE.dao.BaseDao;
import ZTE.entity.QueryParams;
import ZTE.entity.exam.ExamResult;
import ZTE.entity.exam.ExamUpload;
import ZTE.entity.exam.ResultInMonth;
import ZTE.utils.ExamUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExamDaoImpl extends BaseDao implements ExamDao {
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    @Override
    public boolean addResult(String result, String[] studentNoData, int examType, String examDate) {
        boolean flag = false;
        String sql = "INSERT INTO zteresult (studentid,examdate,examType,studentresult)\n" +
                "VALUES (?,?,?,?)";
        int count = 0;// 执行sql语句成功的数量
        for (String studentNoDatum : studentNoData) {
            Object[] params = {
                    studentNoDatum,
                    examDate,
                    examType,
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
    public int getResultCountTodayByClass(String examDate, String className) {
        int count = 0;
        conn = this.getConnection();
        String sql = "SELECT COUNT(*) FROM ztestudent s\n" +
                "RIGHT JOIN zteresult r ON s.studentid=r.studentid\n" +
                "INNER JOIN zteclassinfo c ON s.classId=c.classId\n" +
                "WHERE r.examdate=?\n" +
                "AND c.classname=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1, examDate);
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
    public int getTotalResultCount(QueryParams queryParams) {
        int count = 0;
        List<Object> params = new ArrayList<>();
        params.add(queryParams.getClassName());
        params.add(queryParams.getExamType());
        conn = this.getConnection();
        StringBuffer sql = new StringBuffer("SELECT COUNT(*) FROM zteresult r\n" +
                "INNER JOIN ztestudent s ON r.studentid=s.studentid\n" +
                "INNER JOIN zteclassinfo c ON s.classId=c.classId\n" +
                "WHERE c.classname=?\n" +
                "AND r.examType=?\n");
        if (queryParams.getDate() != null && !"".equals(queryParams.getDate())) {
            sql.append("AND r.examdate=?\n");
            params.add(queryParams.getDate());
        }
        if (queryParams.getNamec() != null && !"".equals(queryParams.getNamec())) {
            sql.append("AND s.studentName like ?\n");
            params.add("%" + queryParams.getNamec() + "%");
        }
        if (queryParams.getResult() != null && !"".equals(queryParams.getResult())) {
            sql.append("AND r.studentresult=?\n");
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
    public List<ExamResult> getResultList(QueryParams queryParams, int pageSize, int currentPage) {
        List<ExamResult> resultList = new ArrayList<>();
        List<Object> params = new ArrayList<>();
        params.add(queryParams.getClassName());
        params.add(queryParams.getExamType());
        conn = this.getConnection();
        StringBuffer sql = new StringBuffer("SELECT r.id,s.studentName,r.examdate,r.examType,r.studentresult FROM zteresult r\n" +
                "INNER JOIN ztestudent s ON r.studentid=s.studentid\n" +
                "INNER JOIN zteclassinfo c ON s.classId=c.classId\n" +
                "WHERE c.classname=?\n" +
                "AND r.examType=?\n");
        if (queryParams.getDate() != null && !"".equals(queryParams.getDate())) {
            sql.append("AND r.examdate=?\n");
            params.add(queryParams.getDate());
        }
        if (queryParams.getNamec() != null && !"".equals(queryParams.getNamec())) {
            sql.append("AND s.studentName like ?\n");
            params.add("%" + queryParams.getNamec() + "%");
        }
        if (queryParams.getResult() != null && !"".equals(queryParams.getResult())) {
            sql.append("AND r.studentresult=?\n");
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
                int examId = rs.getInt("id");
                String studentName = rs.getString("studentName");
                String examDate = rs.getString("examdate");
                String examTypeName = ExamUtil.tansExamTypeIdToName(rs.getInt("examType"));
                String result = rs.getString("studentresult");
                int score = ExamUtil.getScore(result);
                ExamResult examResult = new ExamResult(examId, studentName, examDate, examTypeName, result, score);
                resultList.add(examResult);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.closeAll(conn, pstmt, rs);
        }
        return resultList;
    }

    @Override
    public List<ExamResult> getResultList(QueryParams queryParams) {
        List<ExamResult> resultList = new ArrayList<>();
        List<Object> params = new ArrayList<>();
        params.add(queryParams.getClassName());
        params.add(queryParams.getExamType());
        conn = this.getConnection();
        StringBuffer sql = new StringBuffer("SELECT r.id,s.studentName,r.examdate,r.examType,r.studentresult FROM zteresult r\n" +
                "INNER JOIN ztestudent s ON r.studentid=s.studentid\n" +
                "INNER JOIN zteclassinfo c ON s.classId=c.classId\n" +
                "WHERE c.classname=?\n" +
                "AND r.examType=?\n");
        if (queryParams.getDate() != null && !"".equals(queryParams.getDate())) {
            sql.append("AND r.examdate=?\n");
            params.add(queryParams.getDate());
            if (queryParams.getNamec() != null && !"".equals(queryParams.getNamec())) {
                sql.append("AND s.studentName=?\n");
                params.add(queryParams.getNamec());
            }
            if (queryParams.getResult() != null && !"".equals(queryParams.getResult())) {
                sql.append("AND r.studentresult=?\n");
                params.add(queryParams.getResult());
            }
        }
        try {
            pstmt = conn.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i));
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int examId = rs.getInt("id");
                String studentName = rs.getString("studentName");
                String examDate = rs.getString("examdate");
                String examTypeName = ExamUtil.tansExamTypeIdToName(rs.getInt("examType"));
                String result = rs.getString("studentresult");
                int score = ExamUtil.getScore(result);
                ExamResult examResult = new ExamResult(examId, studentName, examDate, examTypeName, score);
                resultList.add(examResult);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.closeAll(conn, pstmt, rs);
        }
        return resultList;
    }

    @Override
    public boolean changeExamResultInfo(String examDate, String studentresult, int id) {
        boolean flag = false;
        String sql = "UPDATE zteresult SET examdate=?,studentresult=?\n" +
                "WHERE id=?";
        Object[] params = {examDate, studentresult, id};
        int count = this.executeUpdateInfo(sql, params);
        if (count > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean delExamResultInfoById(int id) {
        boolean flag = false;
        String sql = "DELETE FROM zteresult WHERE id=?";
        Object[] params = {id};
        int count = this.executeUpdateInfo(sql, params);
        if (count > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public List<String> getDayHavingResult(int examType, String className, String date) {
        List<String> dateList = new ArrayList<>();
        conn = this.getConnection();
        // 从成绩表中根据月份查询出所有当月有成绩的日期
        String sql = "SELECT DISTINCT r.examdate FROM zteresult r\n" +
                "INNER JOIN ztestudent s ON r.studentid=s.studentid\n" +
                "INNER JOIN zteclassinfo c ON s.classId=c.classId\n" +
                "WHERE r.examdate LIKE ?\n" +
                "AND r.examType=?\n" +
                "AND c.className=?\n" +
                "ORDER BY r.examdate ASC";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1, date + "%");
            pstmt.setObject(2, examType);
            pstmt.setObject(3, className);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String qDate = rs.getString(1);
                dateList.add(qDate);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.closeAll(conn, pstmt, rs);
        }
        return dateList;
    }

    @Override
    public List<ResultInMonth> getExportData(List<String> dateList, int examType, String className) {
        // 用于存储最终的成绩集合
        List<ResultInMonth> dataList = new ArrayList<>();
        // 存储需要传递给sql语句的参数
        List<Object> params = new ArrayList<>();
        conn = this.getConnection();
        StringBuffer sql = new StringBuffer("SELECT r.studentid,s.studentName,\n");
        // 根据获取到的有成绩的日期集合进行行转列
        for (int i = 0; i < dateList.size(); i++) {
            sql.append("MAX(CASE r.examdate WHEN ? THEN r.studentresult ELSE 0 END)");
            if (i < dateList.size() - 1) {
                sql.append(",\n");
            } else {
                sql.append("\n");
            }
            params.add(dateList.get(i));
        }
        // 拼接查询条件
        sql.append("FROM zteresult r\n" +
                "INNER JOIN ztestudent s ON r.studentid=s.studentid\n" +
                "INNER JOIN zteclassinfo c ON s.classId=c.classId\n" +
                "WHERE c.classname=?\n" +
                "AND r.examType=?\n" +
                "GROUP BY r.studentid");
        params.add(className);
        params.add(examType);
        try {
            pstmt = conn.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i));
            }
            rs = pstmt.executeQuery();
            int id = 0;// 成绩表编号
            while (rs.next()) {
                int studentId = rs.getInt("studentid");
                String studentName = rs.getString("studentName");
                List<String> resultList = new ArrayList<>();
                // 从第三列开始是成绩信息，数量为日期集合的大小
                for (int i = 0; i < dateList.size(); i++) {
                    String result = rs.getString(i + 3);
                    resultList.add(result);
                }
                id++;
                ResultInMonth resultInMonth = new ResultInMonth(id, studentId, studentName, resultList);
                dataList.add(resultInMonth);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.closeAll(conn, pstmt, rs);
        }
        return dataList;
    }

    @Override
    public void addExamResult(ExamUpload upload) {
        String sql = "INSERT INTO zteresult (studentid,examdate,examType,studentresult)\n" +
                "VALUES (?,?,?,?)";
        int examType = ExamUtil.getKey(ExamUtil.majorMap,upload.getExamType());
        Object[] params = {
                upload.getStudentId(),
                upload.getExamDate(),
                examType,
                upload.getStudentResult()
        };
        this.executeUpdateInfo(sql,params);
    }
}
