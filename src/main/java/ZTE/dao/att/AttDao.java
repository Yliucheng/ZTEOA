package ZTE.dao.att;

import ZTE.entity.QueryParams;
import ZTE.entity.att.AttInfo;
import ZTE.entity.att.AttUpload;

import java.util.List;

public interface AttDao {
    /**
     * 根据班级名称获取到当日记录了考勤的人数
     * @param attDate 当前日期
     * @param className 查询的班级名称
     * @return 返回查询到的人数
     */
    int getAttCountTodayByClass(String attDate, String className);

    /**
     * 向考勤表插入数据
     * @param result 考勤表现
     * @param studentNoData 要插入的学生学号数组
     * @param attDate 当前日期
     * @return 插入成功返回true，失败返回false
     */
    boolean addAttInfo(String result, String[] studentNoData, String attDate);

    /**
     * 根据参数查询考勤记录总数量
     * @param queryParams 查询的参数
     * @return 返回查询到的人数
     */
    int getTotalAttCount(QueryParams queryParams);

    /**
     * 根据条件获取到所有考勤信息并分页
     * @param queryParams 查询的参数
     * @param pageSize 页面容量
     * @param currentPage 当前页码
     * @return 返回查询到的当前分页的考勤信息集合
     */
    List<AttInfo> getAttInfoList(QueryParams queryParams, int pageSize, int currentPage);

    /**
     * 根据条件获取到所有考勤信息，用于导出表格
     * @param queryParams 查询的参数
     * @return
     */
    List<AttInfo> getAttInfoList(QueryParams queryParams);

    /**
     * 修改出勤表信息
     * @param attDate 修改后的日期
     * @param ckStatus 修改后的考勤状态
     * @param id 考勤ID
     * @return 修改成功返回true，失败返回false
     */
    boolean changeAttInfo(String attDate, String ckStatus, int id);

    /**
     * 删除出勤表信息
     *
     * @param id 需要删除的考勤Id
     * @return 删除成功返回true，失败返回false
     */
    boolean delAttInfo(int id);

    void addAtt(AttUpload upload);
}
