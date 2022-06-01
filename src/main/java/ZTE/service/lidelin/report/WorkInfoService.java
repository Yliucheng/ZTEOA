package ZTE.service.lidelin.report;

import ZTE.entity.lidelin.StudentResult;
import ZTE.entity.lidelin.WorkExcelByMonth;
import ZTE.entity.lidelin.WorkInfo;
import ZTE.entity.lidelin.WorkResult;

import java.util.List;

/**
 * @ClassName WorkInfoService
 * @Description 作业完成情况查询Service接口
 * @Version 1.0
 */
public interface WorkInfoService {

    /**
     * @param
     * @return java.util.List<ZTE.entity.WorkResult>
     * @description 查询到所有的作业表现信息
     */
    List<WorkResult> getWorkResultList();

    /**
     * @param major
     * @param classname
     * @param namec
     * @param date
     * @param result
     * @return int
     * @description 查询需要显示的作业完成情况学生总数
     */
    int getTotalCount(String major, String classname, String namec, String date, String result);

    /**
     * @param major
     * @param classname
     * @param namec
     * @param date
     * @param result
     * @param currentPageNo
     * @param pageSize
     * @return java.util.List<ZTE.entity.WorkInfo>
     * @description 查询需要显示的作业完成情况学生信息(分页)(显示用)
     */
    List<WorkInfo> getWorkInfoList(String major, String classname, String namec, String date, String result, int currentPageNo, int pageSize);

    /**
     * @param major
     * @param classname
     * @param namec
     * @param date
     * @param result
     * @description 查询需要显示的作业完成情况学生信息(所有)(导出用)
     */
    List<WorkInfo> getWorkInfoListAll(String major, String classname, String namec, String date, String result);

    /**
     * @param major
     * @param classname
     * @param month
     * @return java.util.List<ZTE.entity.WorkExcelByMonth>
     * @description 查询需要显示的作业完成情况学生信息(按月份)(导出用)
     */
    List<WorkExcelByMonth> getWorkInfoListByMonth(String major, String classname, String month);

    /**
     * @param id
     * @return boolean
     * @description 根据id删除作业完成情况学生信息
     */
    boolean deleteWorkInfoById(int id);

    /**
     * @param studentResult
     * @return boolean
     * @description 根据id修改作业完成情况学生信息
     */
    boolean updateWorkInfo(StudentResult studentResult);

    /**
     * @param id
     * @return ZTE.entity.lidelin.WorkInfo
     * @description 取到作业情况修改页面的展示数据
     */
    WorkInfo getWorkInfoById(int id);

}