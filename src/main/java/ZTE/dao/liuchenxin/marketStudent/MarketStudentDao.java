package ZTE.dao.liuchenxin.marketStudent;


import ZTE.entity.liuchenxin.MarketStudent;

import java.util.List;

/**
 * 数据访问层
 * 市场类型学生接口
 */
public interface MarketStudentDao {
    List<MarketStudent> showMarketStudent(String type, String name, String className, String willTrain, int currenPageNo, int pageSize);//获取市场类型学生列表
    void addMarketStudent(MarketStudent marketStudent);//添加市场类行学生
    void updateMarketStudent(int id, MarketStudent marketStudent);//修改市场类型学生
    void deleteMarketStudent(int id);//删除市场类型学生
    int getInfoCount(String type, String name, String className, String willTrain);//获取总记录数
    List<String> getMarketClassByStuType(String stuType);//根据学生类型获取同类型的所有班级
    List<String> getAllWillTrain();//获取所有的培训意愿
    String getStuTypeByClass(String className);//根据班级获取学生类型
    List<String> getAllEducation();//获取所有的学历类型
    List<String> getAllXingge();//获取所有的性格类型
    int getClassIdByClassName(String className);//通过班级名称得到班级的id
    MarketStudent getStudentById(int id);//根据id获取到学生对象
}
