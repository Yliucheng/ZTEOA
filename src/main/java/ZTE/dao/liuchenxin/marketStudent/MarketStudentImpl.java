package ZTE.dao.liuchenxin.marketStudent;

import ZTE.dao.BaseDao;
import ZTE.entity.liuchenxin.MarketStudent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据访问层
 * 市场类型学生实现类
 */
public class MarketStudentImpl implements MarketStudentDao{
    BaseDao baseDao = new BaseDao();
    Connection conn=null;
    PreparedStatement pstmt=null;
    ResultSet rs=null;

    /**
     * 获取所有学生列表
     * @param type
     * @param name
     * @param className
     * @param willTrain
     * @param currenPageNo
     * @param pageSize
     * @return
     */
    @Override
    public List<MarketStudent> showMarketStudent(String type, String name, String className, String willTrain, int currenPageNo, int pageSize) {
        //可变长度字符串
        StringBuffer sb = new StringBuffer("SELECT s.id,s.studentName,s.mkClassId,c.classType,c.className," +
                "fromSchool,education,phone,qq,xinge,beizhu,willTrain,createTime,createAuthorId,modifyAuthor," +
                "modifyAuthorTime FROM marketStudent s " +
                "left join marketClass c on s.mkClassId=c.mkClassId" +
                " where 1=1");
        List<MarketStudent> marketStudentList = new ArrayList<>();
        //获取连接
        conn=baseDao.getConnection();
        List param = new ArrayList();//存放拼接的参数
        //判断查询的条件并且拼接
        if (null !=  type && !"".equals(type)) {//如果查询条件不为空
            sb.append(" and classType=?");//就拼接条件
            param.add(type);
        }
        if (null !=  name && !"".equals(name)) {//如果查询条件不为空
            sb.append(" and StudentName like ?");//就拼接条件
            param.add("%" + name + "%");
        }
        if (null !=  className && !"".equals(className)) {//如果查询条件不为空
            sb.append(" and className=?");//就拼接条件
            param.add(className);
        }
        if (null !=  willTrain && !"".equals(willTrain)) {//如果查询条件不为空
            sb.append(" and willTrain=?");//就拼接条件
            param.add(willTrain);
        }
        if(0!=pageSize){
            sb.append(" limit ?,?");
            param.add((currenPageNo-1)*pageSize);
            param.add(pageSize);
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
                //取到值
                int id = rs.getInt("id");
                int mkClassId = rs.getInt("mkClassId");
                String studentName = rs.getString("studentName");
                String classType = rs.getString("classType");
                String className1 = rs.getString("className");
                String fromSchool = rs.getString("fromSchool");
                String education = rs.getString("education");
                String phone = rs.getString("phone");
                String qq = rs.getString("qq");
                String xinge = rs.getString("xinge");
                String beizhu = rs.getString("beizhu");
                String willtrain = rs.getString("willtrain");
                String createAuthorId = rs.getString("createAuthorId");
                String createTime = rs.getString("createTime");
                String modifyauthor = rs.getString("modifyauthor");
                String modifyAuthorTime = rs.getString("modifyAuthorTime");
                //创建对象
                MarketStudent marketStudent = new MarketStudent(id,studentName,className1,classType,mkClassId,fromSchool,
                        education,phone,qq,xinge,beizhu,willtrain,createTime,createAuthorId);
                if(null!=modifyauthor&&!"".equals(modifyauthor)){//如果修改过，就设置修改人
                    marketStudent.setModifyAuthor(modifyauthor);
                }
                if(null!=modifyAuthorTime&&!"".equals(modifyAuthorTime)){//如果修改过，就设置修改时间
                    marketStudent.setModifyAuthorTime(modifyAuthorTime);
                }
                //加入集合
                marketStudentList.add(marketStudent);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            baseDao.closeAll(conn,pstmt,rs);
        }
        return marketStudentList;
    }

    /**
     * 添加学生
     * @param marketStudent
     */
    @Override
    public void addMarketStudent(MarketStudent marketStudent) {
        List<Object> param = new ArrayList();//存放拼接的参数
        String sql  = "insert into marketStudent (studentName,mkClassId,fromSchool,education,phone,QQ," +
                "xinge,beizhu,willTrain,createTime,createAuthorId,modifyAuthor,modifyAuthorTime) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        conn = baseDao.getConnection();
        try {
            pstmt = conn.prepareStatement(sql);
            param.add(marketStudent.getName());
            param.add(marketStudent.getClassId());
            param.add(marketStudent.getFromSchool());
            param.add(marketStudent.getEducation());
            param.add(marketStudent.getPhone());
            param.add(marketStudent.getQq());
            param.add(marketStudent.getXingge());
            param.add(marketStudent.getBeizhu());
            param.add(marketStudent.getWillTrain());
            param.add(marketStudent.getCreateTime());
            param.add(marketStudent.getCreateAuthorId());
            param.add(null);
            param.add(null);
            //填入参数
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
     * 修改学生信息
     * @param id
     * @param marketStudent
     */
    @Override
    public void updateMarketStudent(int id,MarketStudent marketStudent) {
        List<Object> param = new ArrayList();//存放拼接的参数
        String sql = "update marketStudent set studentName=?,mkClassId=?,fromSchool=?,education=?,phone=?,qq=?,xinge=?," +
                "beizhu=?,willTrain=?,modifyAuthor=?,modifyAuthorTime=? where id=?";
        conn = baseDao.getConnection();
        try {
            pstmt = conn.prepareStatement(sql);
            param.add(marketStudent.getName());
            param.add(marketStudent.getClassId());
            param.add(marketStudent.getFromSchool());
            param.add(marketStudent.getEducation());
            param.add(marketStudent.getPhone());
            param.add(marketStudent.getQq());
            param.add(marketStudent.getXingge());
            param.add(marketStudent.getBeizhu());
            param.add(marketStudent.getWillTrain());
            param.add(marketStudent.getModifyAuthor());
            param.add(marketStudent.getModifyAuthorTime());
            param.add(id);
            //填入参数
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
     * 删除学生信息
     * @param id
     */
    @Override
    public void deleteMarketStudent(int id) {
        String sql = "delete from marketStudent where id=?";
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
     * @param name
     * @param className
     * @param willTrain
     * @return
     */
    @Override
    public int getInfoCount(String type,String name,String className,String willTrain) {
        int conut=-1;//总记录数
        //可变长度字符串
        StringBuffer sb = new StringBuffer("SELECT count(*) FROM marketStudent s " +
                "left join marketClass c on s.mkClassId=c.mkClassId" +
                " where 1=1");
        //获取连接
        conn=baseDao.getConnection();
        List param = new ArrayList();//存放拼接的参数
        //判断查询的条件并且拼接
        if (null !=  type && !"".equals(type)) {//如果查询条件不为空
            sb.append(" and classType=?");//就拼接条件
            param.add(type);
        }
        if (null !=  name && !"".equals(name)) {//如果查询条件不为空
            sb.append(" and StudentName like ?");//就拼接条件
            param.add("%" + name + "%");
        }
        if (null !=  className && !"".equals(className)) {//如果查询条件不为空
            sb.append(" and className=?");//就拼接条件
            param.add(className);
        }
        if (null !=  willTrain && !"".equals(willTrain)) {//如果查询条件不为空
            sb.append(" and willTrain=?");//就拼接条件
            param.add(willTrain);
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
     * 获取所有的市场类型班级类型
     * @param stuType
     * @return
     */
    @Override
    public List<String> getMarketClassByStuType(String stuType) {
        List<String> classList = new ArrayList<>();//存放班级列表
        conn = baseDao.getConnection();
        StringBuffer sb = new StringBuffer("select className from marketClass where 1=1");
        if(null!=stuType&&!"".equals(stuType)){//如果下拉框有值就拼接判断条件
            sb.append(" and classType=?" );
        }
        try {
            pstmt = conn.prepareStatement(sb.toString());
            if(null!=stuType&&!"".equals(stuType)){
                pstmt.setObject(1,stuType);
            }
            rs = pstmt.executeQuery();
            while (rs.next()){
                String stuClass = rs.getString("className");
                classList.add(stuClass);
                System.out.println(stuClass);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            baseDao.closeAll(conn,pstmt,rs);
        }
        return classList;
    }

    /**
     * 获取所有的培训意向
     * @return
     */
    @Override
    public List<String> getAllWillTrain() {
        List<String> willTrainList = new ArrayList<>();
        String sql = "select willTrain from marketStudent group by willTrain";
        conn = baseDao.getConnection();
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()){
                willTrainList.add(rs.getString("willTrain"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            baseDao.closeAll(conn,pstmt,rs);
        }
        return willTrainList;
    }

    /**
     * 根据班级，得到班级类型
     * @param className
     * @return
     */
    @Override
    public String getStuTypeByClass(String className) {
        String cs = null;
        String sql = "select classType from marketClass where className=?";
        conn = baseDao.getConnection();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1,className);
            rs = pstmt.executeQuery();
            while (rs.next()){
                cs = rs.getString("classType");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            baseDao.closeAll(conn,pstmt,rs);
        }
        return cs;
    }

    /**
     * 获取所有的学历类型
     * @return
     */
    @Override
    public List<String> getAllEducation() {
        String sql = "select education from marketStudent group by education";
        List<String> list = new ArrayList<>();
        conn = baseDao.getConnection();
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()){
                String education = rs.getString("education");
                list.add(education);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            baseDao.closeAll(conn,pstmt,rs);
        }
        return list;
    }

    @Override
    public List<String> getAllXingge() {
        String sql = "select xinge from marketStudent group by xinge";
        List<String> list = new ArrayList<>();
        conn = baseDao.getConnection();
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()){
                String xinge = rs.getString("xinge");
                list.add(xinge);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            baseDao.closeAll(conn,pstmt,rs);
        }
        return list;
    }

    /**
     * 根据班级姓名得到班级的id
     * @param className
     * @return
     */
    @Override
    public int getClassIdByClassName(String className) {
        String sql = "select mkClassId from marketClass where className=?";
        conn = baseDao.getConnection();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1,className);
            rs = pstmt.executeQuery();
            while (rs.next()){
                return rs.getInt("mkClassId");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            baseDao.closeAll(conn,pstmt,rs);
        }
        return 0;
    }

    /**
     * 通过id得到学生对象
     * @param id
     * @return
     */
    @Override
    public MarketStudent getStudentById(int id) {
        MarketStudent marketStudent = null;
        String sql = "select id,studentName,c.classType,c.className,fromSchool,education,phone,qq," +
                "xinge,beizhu,willTrain,createTime,createAuthorId,modifyAuthor,modifyAuthorTime" +
                " from marketStudent m left join marketClass c on c.mkClassId=m.mkClassId where id=?";
        conn = baseDao.getConnection();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1,id);
            rs = pstmt.executeQuery();
            while (rs.next()){
                int id1 = rs.getInt("id");//id
                String name = rs.getString("studentName");//姓名
                String type = rs.getString("classType");//班级类型
                String className = rs.getString("className");//班级名称
                String fromSchool = rs.getString("fromSchool");//来自学校
                String education = rs.getString("education");//学历
                String phone = rs.getString("phone");//电话
                String QQ = rs.getString("qq");//qq
                String xinge = rs.getString("xinge");//性格
                String beizhu = rs.getString("beizhu");//备注
                String willTrain = rs.getString("willTrain");//培训意愿
                String createTime = rs.getString("createTime");//创建时间
                String createAuthorId = rs.getString("createAuthorId");//创建人
                String modifyAuthor = rs.getString("modifyAuthor");//修改人
                String modifyAuthorTime = rs.getString("modifyAuthorTime");//修改时间
                //创建对象
                marketStudent = new MarketStudent(id1,name,className,type,fromSchool,education,phone,QQ,xinge,beizhu,willTrain
                        ,createTime,createAuthorId,modifyAuthor,modifyAuthorTime);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return marketStudent;
    }
}
