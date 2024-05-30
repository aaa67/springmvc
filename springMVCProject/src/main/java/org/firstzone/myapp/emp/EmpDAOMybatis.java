package org.firstzone.myapp.emp;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.firstzone.myapp.dept.DeptDAOMybatis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//DAO(Data Access)

@Repository
public class EmpDAOMybatis  {

	@Autowired
	SqlSession sqlSession;
	
	String namespace = "com.shinhan.emp.";
	
	Logger logger = LoggerFactory.getLogger(EmpDAOMybatis.class);
	
	public EmpDTO loginCheck(String email, String phone) {
		EmpDTO emp = sqlSession.selectOne(namespace + "loginCheck", email);
		logger.info(emp==null?"�������� �ʴ� ����":emp.toString());
		return emp;
	}

	public List<EmpDTO> selectAll() {
		List<EmpDTO> emplist = sqlSession.selectList(namespace + "selectAll");
		logger.info(emplist.toString());
		logger.info(emplist.size() + "�� ��ȸ��");
		return emplist;
	}

	public EmpDTO selectById(int empid) {
		EmpDTO emp = sqlSession.selectOne(namespace + "selectById", empid);
		logger.info(emp!=null?emp.toString():"data����");
		logger.info(String.valueOf(emp));
		return emp;
	}

	public int selectByEmail(String email) {
		Integer result = sqlSession.selectOne(namespace + "selectByEmail", email);
		logger.info(result.toString());
		return result;
	}

	public List<EmpDTO> selectByDept(int deptId) {
		List<EmpDTO> emplist = sqlSession.selectList(namespace + "selectByDept", deptId);
		logger.info(emplist.toString());
		logger.info(emplist.size() + "�� dptId���� ��ȸ��");
		return emplist;
	}

	public List<EmpDTO> selectByJob(String jobId) {
		List<EmpDTO> emplist = sqlSession.selectList(namespace + "selectByJob", "%" + jobId + "%");
		logger.info(emplist.toString());
		logger.info(emplist.size() + "�� jobId���� ��ȸ��");
		return emplist;
	}

	public List<EmpDTO> selectByCondition(int deptid, String jobid, Date hdate, int salary) {
		Map<String, Object> conditionMap = new HashMap<>();
		conditionMap.put("deptid", deptid);
		conditionMap.put("jobid", jobid);
		conditionMap.put("hdate", hdate);
		conditionMap.put("salary", salary);
		
		List<EmpDTO> emplist = sqlSession.selectList(namespace + "selectByCondition", conditionMap);
		logger.info(emplist.toString());
		logger.info(emplist.size() + "�� ���� ��ȸ��");
		
		return emplist;
	}

	public int empInsert(EmpDTO emp) {
		int result = sqlSession.insert(namespace + "empInsert", emp);
		logger.info(result + "�� insert �� ��");
		return result;
	}

	public int empUpdate(EmpDTO emp) {
		int result = sqlSession.delete(namespace + "empUpdate", emp);
		logger.info(result + "�� update��");
		return result;
	}

	public int empDelete(int empid) {
		int result = sqlSession.delete(namespace + "empDelete", empid);
		logger.info(result + "�� ������");
		return result;
	}

	public Map<String, Object> empInfo(int empid) {

		return null;
	}

	public double callFunction(int empid) {
		return 0;
	}

	// 매니?? 모두 조회
	public List<HashMap<String, Object>> selectAllManager() {
		List<HashMap<String, Object>> mlist = sqlSession.selectList(namespace + "selectAllManager");
		logger.info(mlist.toString());
		logger.info(mlist.size() + "�� AllManager ��ȸ��");
		return mlist;
	}

	// 직업 모두 조회
	public List<JobDTO> selectAllJob() {
		List<JobDTO> jlist = sqlSession.selectList(namespace + "selectAllJob");
		logger.info(jlist.toString());
		logger.info(jlist.size() + "�� ��ȸ��");
		return jlist;
	}
	
	private EmpDTO makeEmp(ResultSet rs) throws SQLException {
		EmpDTO emp = new EmpDTO();

		emp.setCommission_pct(rs.getDouble("commission_pct"));
		emp.setDepartment_id(rs.getInt("department_id"));
		emp.setEmail(rs.getString("email"));
		emp.setEmployee_id(rs.getInt("employee_id"));
		emp.setFirst_name(rs.getString("first_name"));
		emp.setHire_date(rs.getDate("hire_date"));
		emp.setJob_id(rs.getString("job_id"));
		emp.setLast_name(rs.getString("last_name"));
		emp.setManager_id(rs.getInt("manager_id"));
		emp.setPhone_number(rs.getString("phone_number"));
		emp.setSalary(rs.getInt("salary"));

		return emp;
	}
	
	private JobDTO makeJob(ResultSet rs) throws SQLException {
		JobDTO job = new JobDTO();
		job.setJob_id(rs.getString("job_id"));
		job.setJob_title(rs.getString("job_title"));
		job.setMin_salary(rs.getInt("min_salary"));
		job.setMax_salary(rs.getInt("max_salary"));
		return job;
	}

}