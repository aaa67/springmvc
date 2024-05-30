package org.firstzone.myapp.emp;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Controller -> Service -> DAO
//           <-         <-
//Service: ë¹„ì¦ˆ?‹ˆ?Š¤ ë¡œì§?„ ?ˆ˜?–‰?•œ?‹¤.

@Service
public class EmpService {

	//typeÀÌ °°À¸¸é ÀÚµ¿À¸·Î InjectionµÈ´Ù
	@Autowired
	EmpDAOMybatis empDAO;

	// ë¡œê·¸?¸?•˜ê¸?
	public EmpDTO loginCheck(String email, String phone) {
		return empDAO.loginCheck(email, phone);
	}

	// ?´ë©”ì¼ ì¤‘ë³µì²´í¬
	public int selectByEmail(String email) {
		return empDAO.selectByEmail(email);
	}

	// 1. ì§ì› ëª¨ë‘ ì¡°íšŒ
	public List<EmpDTO> selectAll() {
		return empDAO.selectAll();
	}

	// 2. ?Š¹? • ì§ì› ?ƒ?„¸ ë³´ê¸°
	public EmpDTO selectById(int empid) {
		return empDAO.selectById(empid);
	}

	// 3. ?Š¹? • ë¶??„œ?— ê·¼ë¬´?•˜?Š” ì§ì›?“¤
	public List<EmpDTO> selectByDept(int deptId) {
		return empDAO.selectByDept(deptId);
	}

	// 4. ?Š¹? • JOB?¸ ì§ì›?“¤
	public List<EmpDTO> selectByJob(String jobId) {
		return empDAO.selectByJob(jobId);
	}

	// 5. ?‹¤?–‘?•œ ì¡°ê±´?œ¼ë¡? ì¡°íšŒ?•˜ê¸?
	// ë¶??„œë³?(=), ì§ì±…ë³?(=), ?…?‚¬?¼ë³?(>=), ê¸‰ì—¬((>=)
	public List<EmpDTO> selectByCondition(int deptid, String jobid, Date hdate, int salary) {
		return empDAO.selectByCondition(deptid, jobid, hdate, salary);
	}

	// 6. ?…? ¥(Insert)
	public int empInsert(EmpDTO emp) {
		return empDAO.empInsert(emp);
	}

	// 7. ?ˆ˜? •(Update)
	public int empUpdate(EmpDTO emp) {
		return empDAO.empUpdate(emp);
	}

	// 8. ?‚­? œ(Delete)
	public int empDelete(int empid) {
		return empDAO.empDelete(empid);
	}

	// ì§ì›ë²ˆí˜¸ë¥? ?´?š©?•´?„œ ì§ì›?˜ ?´ë¦„ê³¼ ì§ì±…ê³? ê¸‰ì—¬ë¥? ì¡°íšŒ?•œ?‹¤.
	public Map<String, Object> empInfo(int empid) {
		return empDAO.empInfo(empid);
	}

	// ì§ì› ë²ˆí˜¸ê°? ?“¤?–´?˜¤ë©? ì§ì› ë³´ë„ˆ?Š¤ë¥? return?•˜?Š” ?•¨?ˆ˜ë¥? ?˜¸ì¶œí•œ?‹¤.
	public double callFunction(int empid) {
		return empDAO.callFunction(empid);
	}

	// ë§¤ë‹ˆ?? ëª¨ë‘ ì¡°íšŒ
	public List<HashMap<String, Object>> selectAllManager() {
		return empDAO.selectAllManager();
	}

	// ì§ì—… ëª¨ë‘ ì¡°íšŒ
	public List<JobDTO> selectAllJob() {
		return empDAO.selectAllJob();
	}
}
