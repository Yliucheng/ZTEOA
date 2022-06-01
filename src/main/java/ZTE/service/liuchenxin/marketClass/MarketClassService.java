package ZTE.service.liuchenxin.marketClass;


import ZTE.entity.liuchenxin.MarketClass;

import java.util.List;

/**
 * 服务层
 * 市场类型班级接口
 */
public interface MarketClassService {
    List<MarketClass> showMarketClass(String type, int currenPageNo, int pageSize);//获取市场类型班级列表
    void addMarketClass(String name, String type);//添加市场类行班级
    void updateMarketClass(int id, String name, String type);//修改市场类型班级
    void deleteMarketClass(int id);//删除市场类型班级
    MarketClass getMarketClass(int id);//根据id获取到班级对象
}
