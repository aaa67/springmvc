package org.firstzone.myapp.emp;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Controller -> Service -> DAO
//           <-         <-
//Service: λΉμ¦??€ λ‘μ§? ????€.

@Service
public class EmpService {

	//typeΐΜ °°ΐΈΈι ΐΪ΅ΏΐΈ·Ξ Injection΅Θ΄Ω
	@Autowired
	EmpDAOMybatis empDAO;

	// λ‘κ·Έ?Έ?κΈ?
	public EmpDTO loginCheck(String email, String phone) {
		return empDAO.loginCheck(email, phone);
	}

	// ?΄λ©μΌ μ€λ³΅μ²΄ν¬
	public int selectByEmail(String email) {
		return empDAO.selectByEmail(email);
	}

	// 1. μ§μ λͺ¨λ μ‘°ν
	public List<EmpDTO> selectAll() {
		return empDAO.selectAll();
	}

	// 2. ?Ή?  μ§μ ??Έ λ³΄κΈ°
	public EmpDTO selectById(int empid) {
		return empDAO.selectById(empid);
	}

	// 3. ?Ή?  λΆ??? κ·Όλ¬΄?? μ§μ?€
	public List<EmpDTO> selectByDept(int deptId) {
		return empDAO.selectByDept(deptId);
	}

	// 4. ?Ή?  JOB?Έ μ§μ?€
	public List<EmpDTO> selectByJob(String jobId) {
		return empDAO.selectByJob(jobId);
	}

	// 5. ?€?? μ‘°κ±΄?Όλ‘? μ‘°ν?κΈ?
	// λΆ??λ³?(=), μ§μ±λ³?(=), ??¬?Όλ³?(>=), κΈμ¬((>=)
	public List<EmpDTO> selectByCondition(int deptid, String jobid, Date hdate, int salary) {
		return empDAO.selectByCondition(deptid, jobid, hdate, salary);
	}

	// 6. ?? ₯(Insert)
	public int empInsert(EmpDTO emp) {
		return empDAO.empInsert(emp);
	}

	// 7. ?? (Update)
	public int empUpdate(EmpDTO emp) {
		return empDAO.empUpdate(emp);
	}

	// 8. ?­? (Delete)
	public int empDelete(int empid) {
		return empDAO.empDelete(empid);
	}

	// μ§μλ²νΈλ₯? ?΄?©?΄? μ§μ? ?΄λ¦κ³Ό μ§μ±κ³? κΈμ¬λ₯? μ‘°ν??€.
	public Map<String, Object> empInfo(int empid) {
		return empDAO.empInfo(empid);
	}

	// μ§μ λ²νΈκ°? ?€?΄?€λ©? μ§μ λ³΄λ?€λ₯? return?? ?¨?λ₯? ?ΈμΆν?€.
	public double callFunction(int empid) {
		return empDAO.callFunction(empid);
	}

	// λ§€λ?? λͺ¨λ μ‘°ν
	public List<HashMap<String, Object>> selectAllManager() {
		return empDAO.selectAllManager();
	}

	// μ§μ λͺ¨λ μ‘°ν
	public List<JobDTO> selectAllJob() {
		return empDAO.selectAllJob();
	}
}
