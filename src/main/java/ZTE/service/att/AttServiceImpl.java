package ZTE.service.att;

import ZTE.dao.att.AttDao;
import ZTE.dao.att.AttDaoImpl;
import ZTE.entity.QueryParams;
import ZTE.entity.att.AttInfo;
import ZTE.entity.att.AttUpload;

import java.util.List;

public class AttServiceImpl implements AttService {
    AttDao attDao = new AttDaoImpl();

    @Override
    public int getAttCountTodayByClass(String attDate, String className) {
        return attDao.getAttCountTodayByClass(attDate, className);
    }

    @Override
    public boolean addAttInfo(String result, String[] studentNoData, String attDate) {
        return attDao.addAttInfo(result, studentNoData, attDate);
    }

    @Override
    public int getTotalAttCount(QueryParams queryParams) {
        return attDao.getTotalAttCount(queryParams);
    }

    @Override
    public List<AttInfo> getAttInfoList(QueryParams queryParams, int pageSize, int currentPage) {
        return attDao.getAttInfoList(queryParams, pageSize, currentPage);
    }

    @Override
    public List<AttInfo> getAttInfoList(QueryParams queryParams) {
        return attDao.getAttInfoList(queryParams);
    }

    @Override
    public boolean changeAttInfo(String attDate, String ckStatus, int id) {
        return attDao.changeAttInfo(attDate, ckStatus, id);
    }

    @Override
    public boolean delAttInfo(int id) {
        return attDao.delAttInfo(id);
    }

    @Override
    public void addArr(AttUpload upload) {
        attDao.addAtt(upload);
    }
}
