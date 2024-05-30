package org.firstzone.myapp.dept;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class DeptDAO implements DeptDAOInterface{
	
	//≈∏¿‘¿Ã ∞∞¿∏∏È Injection
	//∞∞¿∫ ≈∏¿‘¿Ã ø©∑Ø∞≥∏È ø°∑Ø
	@Autowired
	//∞∞¿∫ ≈∏¿‘¿Ã ø©∑Ø∞≥ ¿÷¿∏∏È ¿Ã∏ß¿ª ∫Ò±≥«ÿº≠ ∞∞¿∫ ¿Ã∏ß¿« Bean¿ª Injection
	@Qualifier("dataSource")
	DataSource ds;
	
	Connection conn;
	Statement st;
	PreparedStatement pst; // StatementÎ•? ?ÉÅ?ÜçÎ∞õÏùå, Î∞îÏù∏?î© Î≥??àò Ïß??õê
	ResultSet rs;

	// 1. Create: ?Éà Î∂??Ñú ?Éù?Ñ±
	public int deptInsert(DeptDTO dept) {
		int result = 0;
		String sql = "insert into departments values (?,?,?,?)";
		try {
			conn=ds.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, dept.getDepartment_id());
			pst.setString(2, dept.getDepartment_name());
			pst.setInt(3, dept.getManager_id());
			pst.setInt(4, dept.getLocation_id());
			result = pst.executeUpdate(); // DML Î¨∏Ïû•?? executeUpdate, SelectÎ¨∏Ï? execeuteQuery
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DBUtil.dbDisconnect(conn, pst, rs);
		}
		return result;
	}
	

	// 2. Read: Î∂??Ñú Î™®Îëê Ï°∞Ìöå
	public List<DeptDTO> selectAll() {
		List<DeptDTO> deptlist = new ArrayList<DeptDTO>();
		String sql = "select * from departments";
		try {
			conn=ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				DeptDTO dept = makeDept(rs);
				deptlist.add(dept);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		//	DBUtil.dbDisconnect(conn, st, rs);
		}
		return deptlist;

	}
	
	//?äπ?†ï Î∂??Ñú Ï°∞Ìöå
	public DeptDTO selectById(int deptid) {
		DeptDTO dept = null;
		String sql = "select * from departments where DEPARTMENT_ID=" + deptid;
		try {
			conn=ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				dept = makeDept(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dept;
	}
	
	
	// 3. Update: Î∂??Ñú ?†ïÎ≥? ?àò?†ï
	public int deptUpdate(DeptDTO dept) {
		int result = 0;
		String sql = "update departments "
				+ " set DEPARTMENT_NAME=?, "
				+ " MANAGER_ID=?, "
				+ " LOCATION_ID=? "
				+ " where DEPARTMENT_ID=? ";
		try {
			conn=ds.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(4, dept.getDepartment_id());
			pst.setString(1, dept.getDepartment_name());
			pst.setInt(2, dept.getManager_id());
			pst.setInt(3, dept.getLocation_id());
			result = pst.executeUpdate(); // DML Î¨∏Ïû•?? executeUpdate, SelectÎ¨∏Ï? execeuteQuery
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		//	DBUtil.dbDisconnect(conn, pst, rs);
		}
		return result;
	}
	
	// 4. Delete: ?äπ?†ï Î∂??Ñú ?Ç≠?†ú
	public int deptDelete(int deptid) {
		int result = 0;
		String sql = "delete from departments "
				+ " where DEPARTMENT_ID=? ";
		try {
			conn=ds.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, deptid);
			result = pst.executeUpdate(); // DML Î¨∏Ïû•?? executeUpdate, SelectÎ¨∏Ï? execeuteQuery
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		//	DBUtil.dbDisconnect(conn, pst, rs);
		}
		return result;
	}

	private DeptDTO makeDept(ResultSet rs) throws SQLException {
		DeptDTO dept = new DeptDTO();

		dept.setDepartment_id(rs.getInt("department_id"));
		dept.setDepartment_name(rs.getString("department_name"));
		dept.setManager_id(rs.getInt("manager_id"));
		dept.setLocation_id(rs.getInt("location_id"));

		return dept;
	}


	@Override
	public List<DeptDTO> selectByName(String deptName) {
		return null;
	}
}
