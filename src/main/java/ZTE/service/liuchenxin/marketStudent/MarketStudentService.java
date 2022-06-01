package ZTE.service.liuchenxin.marketStudent;


import ZTE.entity.liuchenxin.MarketStudent;

import java.util.List;

/**
 * 服务层
 * 市场类型学生接口
 */
public interface MarketStudentService {
    List<MarketStudent> showMarketStudent(String type, String name, String className, String willTrain, int currenPageNo, int pageSize);//获取市场类型学生列表
    void addMarketStudent(MarketStudent marketStudent);//添加市场类行学生
    void updateMarketStudent(int id, MarketStudent marketStudent);//修改市场类型学生
    void deleteMarketStudent(int id);//删除市场类型学生
    int getInfoCount(String type, String name, String className, String willTrain);//获取总记录数
    List<String> getAllWillTrain();//获取所有的培训意向
    List<String> getMarketClassByStuType(String stuType);//获取某种类型的所有班级

    String getStuTypeByClass(String className);////通过班级姓名得到班级的类型
}
