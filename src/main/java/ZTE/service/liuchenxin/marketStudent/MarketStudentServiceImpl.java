package ZTE.service.liuchenxin.marketStudent;


import ZTE.dao.liuchenxin.marketStudent.MarketStudentDao;
import ZTE.dao.liuchenxin.marketStudent.MarketStudentImpl;
import ZTE.entity.liuchenxin.MarketStudent;

import java.util.List;

/**
 * 服务层
 * 市场类型学生实现类
 */
public class MarketStudentServiceImpl implements MarketStudentService{
    MarketStudentDao marketStudentDao = new MarketStudentImpl();
    @Override
    public List<MarketStudent> showMarketStudent(String type, String name, String className, String willTrain, int currenPageNo, int pageSize) {
        return marketStudentDao.showMarketStudent(type, name, className, willTrain, currenPageNo, pageSize);
    }

    @Override
    public void addMarketStudent(MarketStudent marketStudent) {
        marketStudentDao.addMarketStudent(marketStudent);
    }

    @Override
    public void updateMarketStudent(int id,MarketStudent marketStudent) {
        marketStudentDao.updateMarketStudent(id,marketStudent);
    }


    @Override
    public void deleteMarketStudent(int id) {
        marketStudentDao.deleteMarketStudent(id);
    }

    /**
     * 获取总记录数
     * @param type
     * @param name
     * @param className
     * @param willTrain
     * @return
     */
    @Override
    public int getInfoCount(String type, String name, String className, String willTrain) {
        return marketStudentDao.getInfoCount(type, name, className, willTrain);
    }

    @Override
    public List<String> getAllWillTrain() {
        return marketStudentDao.getAllWillTrain();
    }

    @Override
    public List<String> getMarketClassByStuType(String stuType) {
       return marketStudentDao.getMarketClassByStuType(stuType);
    }

    @Override
    public String getStuTypeByClass(String className) {
        return marketStudentDao.getStuTypeByClass(className);
    }
}
