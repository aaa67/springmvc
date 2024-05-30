package com.shinhan.myapp.controller;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.firstzone.myapp.dept.DeptService;
import org.firstzone.myapp.emp.EmpDTO;
import org.firstzone.myapp.emp.EmpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shinhan.myapp.util.DateUtil;

@Controller
@RequestMapping("/emp")
public class EmpController {

	// @Autowired�� type�� ������ �ڵ����� Injection ���ش�.
	@Autowired
	EmpService eService;

	@Autowired
	DeptService dService;

	Logger logger = LoggerFactory.getLogger(EmpController.class);

	// deptSelect=30&jobSelect=FI_MGR&hdate=2005-01-01&salary=5000
	// deptSelect=0 ��� �μ��� �ǹ�
	// jobSelect=all ��� ��å�� �ǹ�
	@GetMapping("/empAll2.do")
	public String empCondition(Model model,
			HttpSession session,
			Integer deptSelect,
			String jobSelect,
			@RequestParam(value="hdate", required=false, defaultValue = "1900-01-01") Date hdate,
			Integer salary) {
		
		/* logger.info(hdate.toLocalDate().toString()); */

		//Date startDate = DateUtil.getSQLDate(hdate);
		
		if(salary==null) salary=0;
		
		List<EmpDTO> empList = eService.selectByCondition(deptSelect, jobSelect, hdate, salary);

		logger.info(empList.size() + "�� ������ȸ");

		// ������ ������ session�� �����ϱ�
		session.setAttribute("deptSelect", deptSelect);
		session.setAttribute("jobSelect", jobSelect);
		session.setAttribute("startDate", hdate);
		session.setAttribute("salary", salary);

		model.addAttribute("emplist", empList); // �𵨿� �����͸� �����Ѵ�.
		model.addAttribute("deptlist", dService.selectAll());
		model.addAttribute("joblist", eService.selectAllJob());
		return "emp/emplist";
	}

	@GetMapping("/empAll.do")
	public String empAll(Model model, Integer deptid, String job_id) {

		List<EmpDTO> empList = null;
		
		if(deptid==null) deptid=0;
		empList = eService.selectByCondition(deptid, job_id, null, 0);

		model.addAttribute("emplist", empList); // �𵨿� �����͸� �����Ѵ�.
		model.addAttribute("deptlist", dService.selectAll());
		model.addAttribute("joblist", eService.selectAllJob());

		// view �̸� return
		// ViewResolver => ���λ� + view �̸� + ���̻� �ٿ��� view forward (�ּ� ���� X)
		return "emp/emplist";
	}

	// ���� ��ο� ��û ��θ� ���� => return ��� ��
	@GetMapping("/empDetail.do")
	public void detail(@RequestParam("empid") Integer empid2, Model model) {

		model.addAttribute("empInfo", eService.selectById(empid2));
		model.addAttribute("deptlist", dService.selectAll());
		model.addAttribute("managerlist", eService.selectAllManager());
		model.addAttribute("joblist", eService.selectAllJob());
	}

	@GetMapping("/empInsert.do")
	public String update(Model model) {

		model.addAttribute("deptlist", dService.selectAll());
		model.addAttribute("managerlist", eService.selectAllManager());
		model.addAttribute("joblist", eService.selectAllJob());

		return "emp/empInsert";
	}

	@PostMapping("/empInsert.do")
	public String insert(EmpDTO emp) {

		System.out.println("insert Ȯ�� (JavaBean) : " + emp);
		eService.empInsert(emp);
		return "redirect:empAll.do";
	}

	@GetMapping("/empIdCheck.do")
	@ResponseBody // ���� ������ �ٵ� ���ڸ� ������ => response.getWriter().append("");
	public String test(int empid) {
		EmpDTO emp = eService.selectById(empid);

		if (emp == null)
			return "0"; // ��밡��

		return "1"; // ��� �Ұ���
	}

	@GetMapping("/empDelete.do")
	public String deleteDB(Integer empid) {
		System.out.println("empid Ȯ�� (JavaBean) : " + empid);
		eService.empDelete(empid);
		return "redirect:empAll.do";
	}

	@PostMapping("/empDetail.do")
	public String updateDB(EmpDTO emp) {
		eService.empUpdate(emp);
		return "redirect:empAll.do";
	}

}