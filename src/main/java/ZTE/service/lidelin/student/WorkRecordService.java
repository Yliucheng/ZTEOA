package ZTE.service.lidelin.student;

import ZTE.entity.lidelin.ClassInfo;
import ZTE.entity.lidelin.Major;
import ZTE.entity.lidelin.Student;
import ZTE.entity.lidelin.WorkInfo;

import java.util.List;

/**
 * @ClassName WorkRecordService
 * @description 作业完成情况录入Service接口
 * @Version 1.0
 */
public interface WorkRecordService {

    /**
     * @param
     * @description 得到所有专业
     */
    List<Major> getMajorList();

    /**
     * @param major
     * @description 根据专业得到所有班级信息
     */
    List<ClassInfo> getClassInfoList(String major);

    /**
     * @param major
     * @param className
     * @return int
     * @description 查询今天作业完成情况未录入的学生总数
     */
    int getTotalCount(String major, String className);

    /**
     * @param major
     * @param className
     * @param currentPageCount
     * @param pageSize
     * @return java.util.List<ZTE.entity.Student>
     * @description 查询今天作业完成情况未录入的学生信息
     */
    List<Student> getStudentList(String major, String className, int currentPageCount, int pageSize);

    /**
     * @description 今天作业完成情况录入
     * @param	studentNoData
     * @param	results
     * @return boolean
     */
    boolean getJExam(String[] studentNoData, String results);

    /**
     * @param
     * @param workInfo
     * @return void
     * @description 上传excel导入学生成绩
     */
    void importWorkInfo(WorkInfo workInfo);

}