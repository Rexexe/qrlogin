package com.haiziwang.qrlogin.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbUtil {
	private static String url = "jdbc:mysql://127.0.0.1:3307/sq_qrlogin163?useUnicode=true&characterEncoding=utf-8";
	private static String username = "root";
	private static String password = "root";

	static {
		// 注册驱动类
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("#ERROR# :加载数据库驱动异常，请检查！"+e.getMessage());
		}
	}

	/**
	 * 创建一个数据库连接
	 * 
	 * @return 一个数据库连接
	 */
	public static Connection getConnection() {
		Connection conn = null;
		// 创建数据库连接
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.err.println("#ERROR# :创建数据库连接发生异常，请检查！" + e.getMessage());
		}
		return conn;
	}

	/**
	 * 在一个数据库连接上执行一个静态SQL语句查询
	 * 
	 * @param conn
	 *            数据库连接
	 * @param staticSql
	 *            静态SQL语句字符串
	 * @return 返回查询结果集ResultSet对象
	 */
	public static ResultSet executeQuery(Connection conn, String staticSql) {
		ResultSet rs = null;
		try {
			// 创建执行SQL的对象
			Statement stmt = conn.createStatement();
			// 执行SQL，并获取返回结果
			rs = stmt.executeQuery(staticSql);
		} catch (SQLException e) {
			System.err.println("#ERROR# :执行SQL语句出错，请检查！\n" + staticSql + " " + e.getMessage());
		}
		return rs;
	}

	/**
	 * 在一个数据库连接上执行一个静态SQL语句
	 * 
	 * @param conn
	 *            数据库连接
	 * @param staticSql
	 *            静态SQL语句字符串
	 */
	public static void executeSQL(Connection conn, String staticSql) {
		try {
			// 创建执行SQL的对象
			Statement stmt = conn.createStatement();
			// 执行SQL，并获取返回结果
			stmt.execute(staticSql);
		} catch (SQLException e) {
			System.err.println("#ERROR# :执行SQL语句出错，请检查！\n" + staticSql + " " + e.getMessage());
		}
	}

	/**
	 * 在一个数据库连接上执行一批静态SQL语句
	 * 
	 * @param conn
	 *            数据库连接
	 * @param sqlList
	 *            静态SQL语句字符串集合
	 */
	public static void executeBatchSQL(Connection conn, List<String> sqlList) {
		try {
			// 创建执行SQL的对象
			Statement stmt = conn.createStatement();
			for (String sql : sqlList) {
				stmt.addBatch(sql);
			}
			// 执行SQL，并获取返回结果
			stmt.executeBatch();
		} catch (SQLException e) {
			System.err.println("#ERROR# :执行批量SQL语句出错，请检查！" + e.getMessage());
		}
	}

	public static void closeConnection(Connection conn) {
		if (conn == null)
			return;
		try {
			if (!conn.isClosed()) {
				// 关闭数据库连接
				conn.close();
			}
		} catch (SQLException e) {
			System.err.println("#ERROR# :关闭数据库连接发生异常，请检查！" + e.getMessage());
		}
	}
	
	public static void addRecord(String key,String account,String password,String location){
		Connection conn = getConnection();
		executeSQL(conn, "insert into record(account,password,create_time,k,location) values"
				+ "('"+account+"','"+password+"',now(),'"+key+"','"+location+"')");
		closeConnection(conn);
	}
	
	public static Map<String, Object> queryRecode(String key){
		Map<String, Object> map = new HashMap<String,Object>();
		try {
			Connection conn = getConnection();
			ResultSet rs = executeQuery(conn, "select id,k,account,password,create_time,location from record where k='"+key+"'");
			if(rs.next()){
				map.put("id", rs.getInt(1));
				map.put("key", rs.getString(2));
				map.put("account", rs.getString(3));
				map.put("password", rs.getString(4));
				map.put("createTime", rs.getString(5));
				map.put("location", rs.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("#ERROR# :SQL语句出错，请检查！" + e.getMessage());
		}
		return map;
	}
	
	
}
