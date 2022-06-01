package ZTE.service.lidelin.student.impl;

import ZTE.dao.lidelin.student.WorkRecordDao;
import ZTE.dao.lidelin.student.impl.WorkRecordDaoImpl;
import ZTE.entity.lidelin.ClassInfo;
import ZTE.entity.lidelin.Major;
import ZTE.entity.lidelin.Student;
import ZTE.entity.lidelin.WorkInfo;
import ZTE.service.lidelin.student.WorkRecordService;

import java.util.List;

/**
 * @ClassName WorkRecordServiceImpl
 * @Description 作业完成情况录入Service实现
 * @Version 1.0
 */
public class WorkRecordServiceImpl implements WorkRecordService {

    WorkRecordDao workRecordDao = new WorkRecordDaoImpl();

    @Override
    public List<Major> getMajorList() {
        return workRecordDao.getMajorList();
    }

    @Override
    public List<ClassInfo> getClassInfoList(String major) {
        return workRecordDao.getClassInfoList(major);
    }

    @Override
    public int getTotalCount(String major, String className) {
        return workRecordDao.getTotalCount(major, className);
    }

    @Override
    public List<Student> getStudentList(String major, String className, int currentPageCount, int pageSize) {
        return workRecordDao.getStudentList(major, className, currentPageCount, pageSize);
    }

    @Override
    public boolean getJExam(String[] studentNoData, String results) {
        return workRecordDao.getJExam(studentNoData, results);
    }

    @Override
    public void importWorkInfo(WorkInfo workInfo) {
        // 根据学生姓名查询学生id
        int studentId = workRecordDao.getDeptIdByDepName(workInfo.getStudentName());
        // 把workInfo和学生id传入进行插入
        boolean flag = workRecordDao.importWorkInfo(workInfo, studentId);
    }

}
