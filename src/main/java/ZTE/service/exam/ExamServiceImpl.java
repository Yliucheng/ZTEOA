package ZTE.service.exam;

import ZTE.dao.exam.ExamDao;
import ZTE.dao.exam.ExamDaoImpl;
import ZTE.entity.QueryParams;
import ZTE.entity.exam.ExamResult;
import ZTE.entity.exam.ExamUpload;
import ZTE.entity.exam.ResultInMonth;

import java.util.List;

public class ExamServiceImpl implements ExamService{
    ExamDao examDao = new ExamDaoImpl();
    @Override
    public boolean addResult(String result, String[] studentNoData,int examType, String examDate) {
        return examDao.addResult(result,studentNoData,examType,examDate);
    }

    @Override
    public int getResultCountTodayByClass(String examDate, String className) {
        return examDao.getResultCountTodayByClass(examDate,className);
    }

    @Override
    public int getTotalResultCount(QueryParams queryParams) {
        return examDao.getTotalResultCount(queryParams);
    }

    @Override
    public List<ExamResult> getResultList(QueryParams queryParams, int pageSize, int currentPage) {
        return examDao.getResultList(queryParams,pageSize, currentPage);
    }

    @Override
    public List<ExamResult> getResultList(QueryParams queryParams) {
        return examDao.getResultList(queryParams);
    }

    @Override
    public boolean changeExamResultInfo(String examDate, String studentresult, int id) {
        return examDao.changeExamResultInfo(examDate,studentresult,id);
    }

    @Override
    public boolean delExamResultInfoById(int id) {
        return examDao.delExamResultInfoById(id);
    }

    @Override
    public List<String> getDayHavingResult(int examType, String className, String date) {
        return examDao.getDayHavingResult(examType,className,date);
    }

    @Override
    public List<ResultInMonth> getExportData(List<String> dateList,int examType,String className) {
        return examDao.getExportData(dateList,examType,className);
    }

    @Override
    public void addExamResult(ExamUpload upload) {
        examDao.addExamResult(upload);
    }
}
