package ZTE.service.liuchenxin.marketClass;


import ZTE.dao.liuchenxin.marketClass.MarketClassDao;
import ZTE.dao.liuchenxin.marketClass.MarketClassImpl;
import ZTE.entity.liuchenxin.MarketClass;

import java.util.List;

/**
 * 服务层
 * 市场类型班级实现类
 */
public class MarketClassServiceImpl implements MarketClassService{
    MarketClassDao marketClassDao = new MarketClassImpl();
    @Override
    public List<MarketClass> showMarketClass(String type, int currenPageNo, int pageSize) {
        return marketClassDao.showMarketClass( type,currenPageNo,pageSize);
    }

    @Override
    public void addMarketClass(String name,String type) {
        marketClassDao.addMarketClass(name, type);
    }

    @Override
    public void updateMarketClass(int id,String name,String type) {
        marketClassDao.updateMarketClass(id,name,type);
    }

    @Override
    public void deleteMarketClass(int id) {
        marketClassDao.deleteMarketClass(id);
    }

    @Override
    public MarketClass getMarketClass(int id) {
        return marketClassDao.getMarketClass(id);
    }
}
