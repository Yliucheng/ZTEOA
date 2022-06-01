package ZTE.dao.liuchenxin.marketClass;

import ZTE.dao.BaseDao;
import ZTE.entity.liuchenxin.MarketClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据访问层
 * 市场类型班级实现类
 */
public class MarketClassImpl implements MarketClassDao{
    BaseDao baseDao = new BaseDao();
    Connection conn=null;
    PreparedStatement pstmt=null;
    ResultSet rs=null;

    /**
     * 获取市场类型班级列表
     * @param type
     * @param currenPageNo
     * @param pageSize
     * @return
     */
    @Override
    public List<MarketClass> showMarketClass(String type, int currenPageNo, int pageSize) {
        List<MarketClass> list=new ArrayList<>();

        //可变长度字符串
        StringBuffer sb = new StringBuffer("SELECT mkClassId,className,classType " +
                "FROM marketClass where 1=1");
        List param = new ArrayList();//存放拼接的参数
        //判断查询的条件并且拼接
        if (null != type && !"".equals(type)) {
            sb.append(" and classType=?");
            param.add(type);
        }
        sb.append(" limit ?,?");
        param.add((currenPageNo-1)*pageSize);
        param.add(pageSize);
        conn = baseDao.getConnection();
        try {
            //预处理
            pstmt = conn.prepareStatement(sb.toString());
            //填入参数
            for(int i=0;i<param.size();i++){
                pstmt.setObject((i+1),param.get(i));
            }
            //执行查询语句，返回结果集
            rs = pstmt.executeQuery();
            //处理结果
            while (rs.next()) {
               int id = rs.getInt("mkClassId");
               String name =rs.getString("className");
               String classType =rs.getString("classType");
               MarketClass marketClass = new MarketClass(id,name,classType);
               list.add(marketClass);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            baseDao.closeAll(conn,pstmt,rs);
        }
        return list;
    }

    /**
     * 添加市场类型班级
     * @param name
     * @param type
     */
    @Override
    public void addMarketClass(String name,String type) {
        String sql = "insert into marketClass (className,classType) values (?,?)";
        baseDao.executeUpdateInfo(sql,name,type);
    }

    /**
     * 修改
     * @param id
     * @param name
     * @param type
     */
    @Override
    public void updateMarketClass(int id,String name,String type) {
        //可变长度字符串
        String sql = "update marketClass set className=?,classType=? where mkClassId=?";
        List param = new ArrayList();//存放拼接的参数
        //判断查询的条件并且拼接
        param.add(name);
        param.add(type);
        param.add(id);
        conn = baseDao.getConnection();
        try {
            pstmt = conn.prepareStatement(sql);
            for(int i=0;i<param.size();i++){
                pstmt.setObject((i+1),param.get(i));
            }
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            baseDao.closeAll(conn,pstmt,rs);
        }
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void deleteMarketClass(int id) {
        String sql = "delete from marketclass where mkClassId=?";
        conn = baseDao.getConnection();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1,id);
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            baseDao.closeAll(conn,pstmt,rs);
        }
    }

    /**
     * 获取总记录数
     * @param type
     * @return
     */
    @Override
    public int getInfoCount(String type) {
        int conut=-1;//总记录数
        //可变长度字符串
        StringBuffer sb = new StringBuffer("SELECT count(*) FROM marketClass where 1=1");
        //获取连接
        conn=baseDao.getConnection();
        List param = new ArrayList();//存放拼接的参数
        //判断查询的条件并且拼接
        if (null !=  type && !"".equals(type)) {//如果查询条件不为空
            sb.append(" and classType=?");//就拼接条件
            param.add(type);
        }
        try {
            //预处理
            pstmt = conn.prepareStatement(sb.toString());
            //填入参数
            for(int i=0;i<param.size();i++){
                pstmt.setObject((i+1),param.get(i));
            }
            //执行查询语句，返回结果集
            rs = pstmt.executeQuery();
            //处理结果
            while (rs.next()) {
                conut=rs.getInt(1);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            baseDao.closeAll(conn,pstmt,rs);
        }
        return conut;
    }

    /**
     * 根据id获取到班级对象
     * @param id
     * @return
     */
    @Override
    public MarketClass getMarketClass(int id) {
        MarketClass marketClass=null;
        String sql = "select mkClassId,className,classType from marketClass where mkClassId=?";
        //获取连接
        conn = baseDao.getConnection();
        try {
            //预处理
            pstmt = conn.prepareStatement(sql);
            //填入参数
            pstmt.setObject(1,id);
            //执行查询，返回结果
            rs = pstmt.executeQuery();
            //获取到参数创建对象
            while (rs.next()){
                String name =rs.getString("className");
                String classType =rs.getString("classType");
                marketClass = new MarketClass(id,name,classType);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            baseDao.closeAll(conn,pstmt,rs);
        }
        return marketClass;
    }

    /**
     * 得到所有的市场类班级
     * @return
     */
    public List<MarketClass> getAllMarketClass(){
        List<MarketClass> list=new ArrayList<>();
        String sql = "select mkClassId,className,classType from marketClass";
        conn = baseDao.getConnection();
        try {
            //预处理
            pstmt = conn.prepareStatement(sql);
            //执行查询语句，返回结果集
            rs = pstmt.executeQuery();
            //处理结果
            while (rs.next()) {
                int id = rs.getInt("mkClassId");
                String name =rs.getString("className");
                String classType =rs.getString("classType");
                MarketClass marketClass = new MarketClass(id,name,classType);
                list.add(marketClass);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            baseDao.closeAll(conn,pstmt,rs);
        }
        return list;
    }

    /**
     * 得到所有的市场类型
     * @return
     */
    @Override
    public List<String> getAllMarketType() {
        List<String> list=new ArrayList<>();
        String sql = "select classType from marketClass group by classType";
        conn = baseDao.getConnection();
        try {
            //预处理
            pstmt = conn.prepareStatement(sql);
            //执行查询语句，返回结果集
            rs = pstmt.executeQuery();
            //处理结果
            while (rs.next()) {
                String classType = rs.getString("classType");
                list.add(classType);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            baseDao.closeAll(conn,pstmt,rs);
        }
        return list;
    }
}
