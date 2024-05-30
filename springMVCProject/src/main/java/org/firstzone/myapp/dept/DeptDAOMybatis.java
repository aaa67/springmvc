package org.firstzone.myapp.dept;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("deptmybatis")
public class DeptDAOMybatis implements DeptDAOInterface{
	
	//타입이 같으면 Injection
	//같은 타입이 여러개면 에러
	@Autowired
	SqlSession sqlSession;
	
	Logger logger = LoggerFactory.getLogger(DeptDAOMybatis.class);
	String namespace = "com.shinhan.dept.";
	

	public int deptInsert(DeptDTO dept) {
		logger.info("DeptDAOMybatis.....deptInsert()");
		return sqlSession.insert(namespace + "deptInsert", dept);
	}

	public List<DeptDTO> selectAll() {
		System.out.println("=====");
		logger.info("DeptDAOMybatis.....selectAll()");
		return sqlSession.selectList(namespace + "selectAll");

	}
	
	public DeptDTO selectById(int deptid) {
		logger.info("DeptDAOMybatis.....selectById()");
		return sqlSession.selectOne(namespace + "selectById", deptid);
	}
	
	public List<DeptDTO> selectByName(String deptName) {
		logger.info("DeptDAOMybatis.....selectByName()");
		return sqlSession.selectList(namespace + "selectByName", deptName);
	}
	
	public int deptUpdate(DeptDTO dept) {
		logger.info("DeptDAOMybatis.....deptUpdate()");
		return sqlSession.insert(namespace + "deptUpdate", dept);
	}
	
	public int deptDelete(int deptid) {
		logger.info("DeptDAOMybatis.....deptDelete()");
		return sqlSession.insert(namespace + "deptDelete", deptid);
	}

	private DeptDTO makeDept(ResultSet rs) throws SQLException {
		DeptDTO dept = new DeptDTO();

		dept.setDepartment_id(rs.getInt("department_id"));
		dept.setDepartment_name(rs.getString("department_name"));
		dept.setManager_id(rs.getInt("manager_id"));
		dept.setLocation_id(rs.getInt("location_id"));

		return dept;
	}
}
