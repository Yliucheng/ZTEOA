package ZTE.dao.exam;

import ZTE.entity.QueryParams;
import ZTE.entity.exam.ExamResult;
import ZTE.entity.exam.ExamUpload;
import ZTE.entity.exam.ResultInMonth;

import java.util.List;

public interface ExamDao {
    /**
     * 插入考试数据
     *
     * @param result 成绩表现
     * @param studentNoData 需要插入的学生学号数组
     * @param examType 考试类型
     * @param examDate 当前日期
     * @return 插入成功返回true，失败返回false
     */
    boolean addResult(String result, String[] studentNoData, int examType, String examDate);

    /**
     * 根据班级名称获取到当日有成绩的人数
     *
     * @param examDate 当前日期
     * @param className 班级名称
     * @return 返回查询到的人数
     */
    int getResultCountTodayByClass(String examDate, String className);

    /**
     * 获取到符合条件的总记录数
     *
     * @param queryParams 查询的条件
     * @return 返回查询到的人数
     */
    int getTotalResultCount(QueryParams queryParams);

    /**
     * 根据查询条件获取到所有考试数据分页显示
     *
     * @param queryParams 查询的条件
     * @param pageSize 页面容量
     * @param currentPage 当前页数
     * @return 返回符合条件的当页成绩集合
     */
    List<ExamResult> getResultList(QueryParams queryParams, int pageSize, int currentPage);

    /**
     * 获取到所有符合查询条件的结果数据用于导出表格
     *
     * @param queryParams 查询条件
     * @return 返回所有符合条件的成绩集合
     */
    List<ExamResult> getResultList(QueryParams queryParams);

    /**
     * 修改成绩表数据
     *
     * @param examDate 修改后的日期
     * @param studentresult 修改后的成绩表现
     * @param id 需要修改的成绩ID
     * @return 修改成功返回true，失败返回false
     */
    boolean changeExamResultInfo(String examDate, String studentresult, int id);

    /**
     * 删除成绩表数据
     *
     * @param id 需要删除的成绩ID
     * @return 删除成功返回true，失败返回false
     */
    boolean delExamResultInfoById(int id);

    /**
     * 获取到有考试成绩的日期
     *
     * @param examType 考试类型
     * @param className 班级名称
     * @param date 查询的月份
     * @return 返回当月有成绩的日期集合
     */
    List<String> getDayHavingResult(int examType, String className, String date);

    /**
     * 获取到符合条件的学生信息和他们的成绩集合
     *
     * @param dateList  当月有成绩的日期集合
     * @param examType  考试类型
     * @param className 班级名称
     * @return 返回符合条件的成绩集合
     */
    List<ResultInMonth> getExportData(List<String> dateList, int examType, String className);
    /**
     * 导入表格时向成绩表添加数据
     * @param upload
     */
    void addExamResult(ExamUpload upload);
}
