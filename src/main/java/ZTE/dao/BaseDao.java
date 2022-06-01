package ZTE.dao;


import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class BaseDao {
	
	private static String driver;//驱动
	private static String url;//URL地址
	private static String user;//用户名
	private static String password;//密码
	
	Connection conn=null;//负责连接数据库的 
	PreparedStatement pstmt=null;//负责发送SQL命令的
	ResultSet rs=null;//负责返回结果集的
	
	static {
		//利用静态代码块，在类加载的时候就完成初始化
		init();
	}
	
	
	private static void init() {
	Properties pro=new Properties();
	InputStream is= BaseDao.class.getClassLoader().getResourceAsStream("database.properties");
	try {
		//加载database.properties配置文件
		pro.load(is);
		driver= pro.getProperty("mysql.driver");
		url= pro.getProperty("mysql.url");
		user= pro.getProperty("mysql.user");
		password= pro.getProperty("mysql.password");
	} catch (IOException e) {
		e.printStackTrace();
	}
		
	}

	
	/**
	 * 获取连接对象
	 * @return
	 */
	public Connection getConnection() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	


	/**
	 * 关闭资源对象
	 * @param conn
	 * @param pstmt
	 * @param rs
	 */
	public void closeAll(Connection conn,PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs!=null) {
				rs.close();
			}
			if(pstmt!=null){
				pstmt.close();
			}
			if (conn!=null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 增删改通用方法
	 * @param sql
	 * @param params
	 * @return
	 */
	public int executeUpdateInfo(String sql,Object ... params) {
		int count=0;
		//获取连接对象
		conn=this.getConnection();
		try {
			pstmt=conn.prepareStatement(sql);
			for(int i=0;i<params.length;i++) {
				pstmt.setObject((i+1), params[i]);
			}
			count=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			this.closeAll(conn, pstmt, rs);
		}
		return count;//返回受影响行数
	}

}
