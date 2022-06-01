package ZTE.dao.lidelin.student;

import ZTE.entity.lidelin.ClassInfo;
import ZTE.entity.lidelin.Major;
import ZTE.entity.lidelin.Student;
import ZTE.entity.lidelin.WorkInfo;

import java.util.List;

/**
 * @ClassName WorkRecordDao
 * @Description 作业完成情况录入Dao接口
 * @Version 1.0
 */
public interface WorkRecordDao {

    /**
     * @param
     * @return java.util.List<ZTE.entity.Major>
     * @description 得到所有专业
     * @author lidelin
     * @date 2022/5/18 17:30
     */
    List<Major> getMajorList();

    /**
     * @param major
     * @return java.util.List<ZTE.entity.ClassInfo>
     * @description 根据专业得到所有班级信息
     * @author lidelin
     * @date 2022/5/18 17:52
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
     * @param studentNoData
     * @param results
     * @return boolean
     * @description 今天作业完成情况录入
     */
    boolean getJExam(String[] studentNoData, String results);

    /**
     * @param
     * @param studentName
     * @return int
     * @description 根据部门名称返回部门id
     */
    int getDeptIdByDepName(String studentName);

    /**
     * @param workInfo
     * @param studentId
     * @return boolean
     * @description 插入学生作业表现
     */
    boolean importWorkInfo(WorkInfo workInfo, int studentId);

}
