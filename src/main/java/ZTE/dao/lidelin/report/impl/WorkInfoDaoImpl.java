package ZTE.dao.lidelin.report.impl;

import ZTE.dao.BaseDao;
import ZTE.dao.lidelin.report.WorkInfoDao;
import ZTE.entity.lidelin.StudentResult;
import ZTE.entity.lidelin.WorkExcelByMonth;
import ZTE.entity.lidelin.WorkInfo;
import ZTE.entity.lidelin.WorkResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName WorkInfoDaoImpl
 * @Description 作业完成情况查询Dao实现
 * @Version 1.0
 */
public class WorkInfoDaoImpl extends BaseDao implements WorkInfoDao {

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    @Override
    public List<WorkResult> getWorkResultList() {
        List<WorkResult> workResultList = new ArrayList<>();
        String sql = "select distinct zcj.ckstatu from ztecheckjob zcj;";
        conn = super.getConnection();
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String result = rs.getString("ckstatu");
                WorkResult sResult = new WorkResult(result);
                workResultList.add(sResult);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeAll(conn, pstmt, rs);
        }
        return workResultList;
    }

    @Override
    public int getTotalCount(String major, String classname, String namec, String date, String result) {
        int count = 0;
        StringBuffer sql = new StringBuffer("select count(*)\n" +
                "from ztecheckjob zcj\n" +
                "         inner join ztestudent zs on zcj.studentNo = zs.studentid\n" +
                "         inner join zteclassinfo zci on zs.classId = zci.classId\n" +
                "         inner join ztemajor zm on zci.studyType = zm.id\n" +
                "where 1 = 1\n");
        conn = super.getConnection();
        List<Object> params = new ArrayList<>();
        if (major != null && !"".equals(major)) {
            sql.append("  and zm.major = ?\n");
            params.add(major);
        }
        if (classname != null && !"".equals(classname)) {
            sql.append("  and zci.className = ?\n");
            params.add(classname);
        }
        if (namec != null && !"".equals(namec)) {
            sql.append("  and zs.studentName like ?\n");
            params.add("%" + namec + "%");
        }
        if (date != null && date != "") {
            sql.append("  and zcj.ckTime = ?\n");
            params.add(date);
        }
        if (result != null && result != "") {
            sql.append("  and zcj.ckstatu = ?;\n");
            params.add(result);
        }
        try {
            pstmt = conn.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject((i + 1), params.get(i));
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeAll(conn, pstmt, rs);
        }
        return count;
    }

    @Override
    public List<WorkInfo> getWorkInfoList(String major, String classname, String namec, String date, String result, int currentPageNo, int pageSize) {
        List<WorkInfo> workInfoList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        StringBuffer sql = new StringBuffer("select zcj.id, zs.studentName, zcj.ckTime, zcj.ckstatu\n" +
                "from ztecheckjob zcj\n" +
                "         inner join ztestudent zs on zcj.studentNo = zs.studentid\n" +
                "         inner join zteclassinfo zci on zs.classId = zci.classId\n" +
                "         inner join ztemajor zm on zci.studyType = zm.id\n" +
                "where 1 = 1\n");
        conn = this.getConnection();
        List<Object> params = new ArrayList<>();
        if (major != null && !"".equals(major)) {
            sql.append("  and zm.major = ?\n");
            params.add(major);
        }
        if (classname != null && !"".equals(classname)) {
            sql.append("  and zci.className = ?\n");
            params.add(classname);
        }
        if (namec != null && !"".equals(namec)) {
            sql.append("  and zs.studentName like ?\n");
            params.add("%" + namec + "%");
        }
        if (date != null && !"".equals(date)) {
            sql.append("  and zcj.ckTime = ?\n");
            params.add(date);
        }
        if (result != null && !"".equals(result)) {
            sql.append("  and zcj.ckstatu = ?\n");
            params.add(result);
        }
        sql.append("limit ?,?;");
        params.add((currentPageNo - 1) * pageSize);
        params.add(pageSize);
        try {
            pstmt = conn.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject((i + 1), params.get(i));
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String studentName = rs.getString("studentName");
                String cktime = sdf.format(rs.getTimestamp("ckTime"));
                String ckstatu = rs.getString("ckstatu");
                WorkInfo workInfo = new WorkInfo(id, studentName, cktime, ckstatu);
                workInfoList.add(workInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeAll(conn, pstmt, rs);
        }
        return workInfoList;
    }

    @Override
    public List<WorkInfo> getWorkInfoListAll(String major, String classname, String namec, String date, String result) {
        List<WorkInfo> workInfoList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        StringBuffer sql = new StringBuffer("select zcj.id, zs.studentName, zcj.ckTime, zcj.ckstatu\n" +
                "from ztecheckjob zcj\n" +
                "         inner join ztestudent zs on zcj.studentNo = zs.studentid\n" +
                "         inner join zteclassinfo zci on zs.classId = zci.classId\n" +
                "         inner join ztemajor zm on zci.studyType = zm.id\n" +
                "where 1 = 1\n");
        conn = this.getConnection();
        List<Object> params = new ArrayList<>();
        if (major != null && !"".equals(major)) {
            sql.append("  and zm.major = ?\n");
            params.add(major);
        }
        if (classname != null && !"".equals(classname)) {
            sql.append("  and zci.className = ?\n");
            params.add(classname);
        }
        if (namec != null && !"".equals(namec)) {
            sql.append("  and zs.studentName like ?\n");
            params.add("%" + namec + "%");
        }
        if (date != null && !"".equals(date)) {
            sql.append("  and zcj.ckTime = ?\n");
            params.add(date);
        }
        if (result != null && !"".equals(result)) {
            sql.append("  and zcj.ckstatu = ?;");
            params.add(result);
        }
        try {
            pstmt = conn.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject((i + 1), params.get(i));
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String studentName = rs.getString("studentName");
                String cktime = sdf.format(rs.getTimestamp("ckTime"));
                String ckstatu = rs.getString("ckstatu");
                WorkInfo workInfo = new WorkInfo(id, studentName, cktime, ckstatu);
                workInfoList.add(workInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeAll(conn, pstmt, rs);
        }
        return workInfoList;
    }

    @Override
    public List<WorkExcelByMonth> getWorkInfoListByMonth(String major, String classname, String month) {
        // 返回的学生信息列表
        List<WorkExcelByMonth> list = new ArrayList<>();
        StringBuffer sql = new StringBuffer("select zs.studentid,\n" +
                "       zs.studentName,\n");
        // 根据月份判断天数
        // 大月31天
        int dat = 31;
        if (month != null && !"".equals(month)) {
            String nian = month.substring(0, 4);
            int year = Integer.parseInt(nian);
            String mon = month.substring(5, 7);
            if ("04".equals(mon) || "06".equals(mon) || "09".equals(mon) || "11".equals(mon)) {
                // 小月30天
                dat = 30;
            } else if ("02".equals(mon)) {
                if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
                    // 闰年2月29天
                    dat = 29;
                } else {
                    // 平年2月28天
                    dat = 28;
                }
            }
            // 取到sql查询的天数, 并拼接到这个月的查询语句去
            for (int i = 1; i <= dat; i++) {
                String date;
                if (i < 10) {
                    date = "0" + i;
                } else {
                    date = "" + i;
                }
                if (i != dat) {
                    sql.append("       max(case zcj.ckTime when '" + nian + "-" + mon + "-" + date + "' then zcj.ckstatu else " +
                            "'/' " +
                            "end) " +
                            "'" + nian + "-" + mon + "-" + date + "',\n");
                } else {
                    sql.append("       max(case zcj.ckTime when '" + nian + "-" + mon + "-" + date + "' then zcj.ckstatu else " +
                            "'/' " +
                            "end) " +
                            "'" + nian + "-" + mon + "-" + date + "'\n");
                }
            }
        }
        sql.append("from ztecheckjob zcj\n" +
                "         inner join ztestudent zs on zcj.studentNo = zs.studentid\n" +
                "         inner join zteclassinfo zci on zs.classId = zci.classId\n" +
                "         inner join ztemajor zm on zci.studyType = zm.id\n" +
                "where 1 = 1\n");
        conn = this.getConnection();
        // 参数列表
        List<Object> params = new ArrayList<>();
        // 专业
        if (major != null && !"".equals(major)) {
            sql.append("  and zm.major = ?\n");
            params.add(major);
        }
        // 班级名称
        if (classname != null && !"".equals(classname)) {
            sql.append("  and zci.className = ?\n");
            params.add(classname);
        }
        // 月份
        if (month != null && !"".equals(month)) {
            sql.append("  and month(zcj.ckTime) = ?\n");
            params.add(month.substring(5, 7));
        }
        // 拼接分组、id排序
        sql.append("group by zs.studentid, zs.studentName\n" +
                "order by zs.studentid;");
        try {
            pstmt = conn.prepareStatement(sql.toString());
            // 传递参数
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject((i + 1), params.get(i));
            }
            rs = pstmt.executeQuery();
            int id = 1;
            while (rs.next()) {
                // 信息对象
                WorkExcelByMonth workExcelByMonth = null;
                // 每天的成绩信息列表
                List<String> lis = new ArrayList<>();
                // 学生id
                lis.add(rs.getString(1));
                // 学生姓名
                lis.add(rs.getString(2));
                // 取到每天的成绩
                for (int i = 3; i < dat + 3; i++) {
                    lis.add(rs.getString(i));
                }
                if (dat == 31) {
                    workExcelByMonth = new WorkExcelByMonth(id, lis.get(0), lis.get(1), lis.get(2), lis.get(3), lis.get(4), lis.get(5), lis.get(6), lis.get(7), lis.get(8), lis.get(9), lis.get(10), lis.get(11), lis.get(12), lis.get(13), lis.get(14), lis.get(15), lis.get(16), lis.get(17), lis.get(18), lis.get(19), lis.get(20), lis.get(21), lis.get(22), lis.get(23), lis.get(24), lis.get(25), lis.get(26), lis.get(27), lis.get(28), lis.get(29), lis.get(30), lis.get(31), lis.get(32));
                } else if (dat == 30) {
                    workExcelByMonth = new WorkExcelByMonth(id, lis.get(0), lis.get(1), lis.get(2), lis.get(3), lis.get(4), lis.get(5), lis.get(6), lis.get(7), lis.get(8), lis.get(9), lis.get(10), lis.get(11), lis.get(12), lis.get(13), lis.get(14), lis.get(15), lis.get(16), lis.get(17), lis.get(18), lis.get(19), lis.get(20), lis.get(21), lis.get(22), lis.get(23), lis.get(24), lis.get(25), lis.get(26), lis.get(27), lis.get(28), lis.get(29), lis.get(30), lis.get(31));
                } else if (dat == 29) {
                    workExcelByMonth = new WorkExcelByMonth(id, lis.get(0), lis.get(1), lis.get(2), lis.get(3), lis.get(4), lis.get(5), lis.get(6), lis.get(7), lis.get(8), lis.get(9), lis.get(10), lis.get(11), lis.get(12), lis.get(13), lis.get(14), lis.get(15), lis.get(16), lis.get(17), lis.get(18), lis.get(19), lis.get(20), lis.get(21), lis.get(22), lis.get(23), lis.get(24), lis.get(25), lis.get(26), lis.get(27), lis.get(28), lis.get(29), lis.get(30));
                } else if (dat == 28) {
                    workExcelByMonth = new WorkExcelByMonth(id, lis.get(0), lis.get(1), lis.get(2), lis.get(3), lis.get(4), lis.get(5), lis.get(6), lis.get(7), lis.get(8), lis.get(9), lis.get(10), lis.get(11), lis.get(12), lis.get(13), lis.get(14), lis.get(15), lis.get(16), lis.get(17), lis.get(18), lis.get(19), lis.get(20), lis.get(21), lis.get(22), lis.get(23), lis.get(24), lis.get(25), lis.get(26), lis.get(27), lis.get(28), lis.get(29));
                }
                list.add(workExcelByMonth);
                id++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeAll(conn, pstmt, rs);
        }
        return list;
    }

    @Override
    public boolean deleteWorkInfoById(int id) {
        String sql = "delete\n" +
                "from ztecheckjob zcj\n" +
                "where zcj.id = ?;";
        // 传参
        Object[] params = {id};
        if (super.executeUpdateInfo(sql, params) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateWorkInfo(StudentResult studentResult) {
        String sql = "update ztecheckjob zcj\n" +
                "set zcj.ckTime=?,\n" +
                "    zcj.ckstatu=?\n" +
                "where id = ?;";
        Object[] params = {
                studentResult.getDate(),
                studentResult.getStudentResult(),
                studentResult.getId()
        };
        int count = super.executeUpdateInfo(sql, params);
        if (count > 0) {
            return true;
        }
        return false;
    }

    @Override
    public WorkInfo getWorkInfoById(int id) {
        WorkInfo workInfo = null;
        conn = super.getConnection();
        String sql = "select zcj.id, zs.studentName, zcj.ckTime, zcj.ckstatu\n" +
                "from ztecheckjob zcj\n" +
                "         inner join ztestudent zs on zcj.studentNo = zs.studentid\n" +
                "where zcj.id = ?;\n";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int id1 = rs.getInt("id");
                String studentName = rs.getString("studentName");
                String ckTime = rs.getString("ckTime");
                String ckstatu = rs.getString("ckstatu");
                workInfo = new WorkInfo(id1, studentName, ckTime, ckstatu);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            super.closeAll(conn, pstmt, rs);
        }
        return workInfo;
    }

}
