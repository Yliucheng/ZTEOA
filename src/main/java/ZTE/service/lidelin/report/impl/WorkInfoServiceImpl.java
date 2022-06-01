package ZTE.service.lidelin.report.impl;

import ZTE.dao.BaseDao;
import ZTE.dao.lidelin.report.WorkInfoDao;
import ZTE.dao.lidelin.report.impl.WorkInfoDaoImpl;
import ZTE.entity.lidelin.StudentResult;
import ZTE.entity.lidelin.WorkExcelByMonth;
import ZTE.entity.lidelin.WorkInfo;
import ZTE.entity.lidelin.WorkResult;
import ZTE.service.lidelin.report.WorkInfoService;

import java.util.List;

/**
 * @ClassName WorkInfoServiceImpl
 * @Description 作业完成情况查询Service实现
 * @Version 1.0
 */
public class WorkInfoServiceImpl extends BaseDao implements WorkInfoService {

    WorkInfoDao workInfoDao = new WorkInfoDaoImpl();

    @Override
    public List<WorkResult> getWorkResultList() {
        return workInfoDao.getWorkResultList();
    }

    @Override
    public int getTotalCount(String major, String classname, String namec, String date, String result) {
        return workInfoDao.getTotalCount(major, classname, namec, date, result);
    }

    @Override
    public List<WorkInfo> getWorkInfoList(String major, String classname, String namec, String date, String result, int currentPageNo, int pageSize) {
        return workInfoDao.getWorkInfoList(major, classname, namec, date, result, currentPageNo, pageSize);
    }

    @Override
    public List<WorkInfo> getWorkInfoListAll(String major, String classname, String namec, String date, String result) {
        return workInfoDao.getWorkInfoListAll(major, classname, namec, date, result);

    }

    @Override
    public List<WorkExcelByMonth> getWorkInfoListByMonth(String major, String classname, String month) {
        return workInfoDao.getWorkInfoListByMonth(major, classname, month);
    }

    @Override
    public boolean deleteWorkInfoById(int id) {
        return workInfoDao.deleteWorkInfoById(id);
    }

    @Override
    public boolean updateWorkInfo(StudentResult studentResult) {
        return workInfoDao.updateWorkInfo(studentResult);
    }

    @Override
    public WorkInfo getWorkInfoById(int id) {
        return workInfoDao.getWorkInfoById(id);
    }

}
