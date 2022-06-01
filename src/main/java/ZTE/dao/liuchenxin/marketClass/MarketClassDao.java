package ZTE.dao.liuchenxin.marketClass;


import ZTE.entity.liuchenxin.MarketClass;

import java.util.List;

/**
 * 数据访问层
 * 市场类型班级接口
 */
public interface MarketClassDao {
    List<MarketClass> showMarketClass(String type, int currenPageNo, int pageSize);//获取市场类型班级列表
    void addMarketClass(String name, String type);//添加市场类行班级
    void updateMarketClass(int id, String name, String type);//修改市场类型班级
    void deleteMarketClass(int id);//删除市场类型班级
    int getInfoCount(String type);//获取总记录数
    MarketClass getMarketClass(int id);//根据id来获取到班级对象
    List<MarketClass> getAllMarketClass();//得到所有的市场类型班级
    List<String> getAllMarketType();//获取所有的市场类型

}
