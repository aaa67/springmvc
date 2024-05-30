package org.firstzone.myapp.emp;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Controller -> Service -> DAO
//           <-         <-
//Service: 비즈?��?�� 로직?�� ?��?��?��?��.

@Service
public class EmpService {

	//type�� ������ �ڵ����� Injection�ȴ�
	@Autowired
	EmpDAOMybatis empDAO;

	// 로그?��?���?
	public EmpDTO loginCheck(String email, String phone) {
		return empDAO.loginCheck(email, phone);
	}

	// ?��메일 중복체크
	public int selectByEmail(String email) {
		return empDAO.selectByEmail(email);
	}

	// 1. 직원 모두 조회
	public List<EmpDTO> selectAll() {
		return empDAO.selectAll();
	}

	// 2. ?��?�� 직원 ?��?�� 보기
	public EmpDTO selectById(int empid) {
		return empDAO.selectById(empid);
	}

	// 3. ?��?�� �??��?�� 근무?��?�� 직원?��
	public List<EmpDTO> selectByDept(int deptId) {
		return empDAO.selectByDept(deptId);
	}

	// 4. ?��?�� JOB?�� 직원?��
	public List<EmpDTO> selectByJob(String jobId) {
		return empDAO.selectByJob(jobId);
	}

	// 5. ?��?��?�� 조건?���? 조회?���?
	// �??���?(=), 직책�?(=), ?��?��?���?(>=), 급여((>=)
	public List<EmpDTO> selectByCondition(int deptid, String jobid, Date hdate, int salary) {
		return empDAO.selectByCondition(deptid, jobid, hdate, salary);
	}

	// 6. ?��?��(Insert)
	public int empInsert(EmpDTO emp) {
		return empDAO.empInsert(emp);
	}

	// 7. ?��?��(Update)
	public int empUpdate(EmpDTO emp) {
		return empDAO.empUpdate(emp);
	}

	// 8. ?��?��(Delete)
	public int empDelete(int empid) {
		return empDAO.empDelete(empid);
	}

	// 직원번호�? ?��?��?��?�� 직원?�� ?��름과 직책�? 급여�? 조회?��?��.
	public Map<String, Object> empInfo(int empid) {
		return empDAO.empInfo(empid);
	}

	// 직원 번호�? ?��?��?���? 직원 보너?���? return?��?�� ?��?���? ?��출한?��.
	public double callFunction(int empid) {
		return empDAO.callFunction(empid);
	}

	// 매니?? 모두 조회
	public List<HashMap<String, Object>> selectAllManager() {
		return empDAO.selectAllManager();
	}

	// 직업 모두 조회
	public List<JobDTO> selectAllJob() {
		return empDAO.selectAllJob();
	}
}
