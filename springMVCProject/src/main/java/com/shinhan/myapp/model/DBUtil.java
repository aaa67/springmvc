package com.shinhan.myapp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtil {
	//DB ?���?
	public static Connection dbConnection2() {
		
		Context initContext;
		Connection conn = null;
		
		try {
			initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
			conn = ds.getConnection(); //Connection Pooling(?���? ?��?�� ?�� 미리 Connection?�� 만들?��?���? �?�?)?��?�� Connection 1�? ?���?
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static Connection dbConnection() {
		//1. JDBC Driver load
		//2. Connection ?��?��
		//ip=>192.169.0.**, localhost, 127.0.0.1
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String userid="hr";
		String password="hr";
		Connection conn=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println("1. JDBC Driver load ?���?");
			conn=DriverManager.getConnection(url, userid, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
		
	}
	
	//DB ?���? ?��?��
	public static void dbDisconnect(Connection conn, Statement st, ResultSet rs) {
		try {
			if(rs!=null) rs.close();
			if(st!=null) st.close();
			if(conn!=null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	//	System.out.println("3. ?��?�� ?�� db ?��?��");
	}
}
