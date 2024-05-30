package com.shinhan.myapp.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.firstzone.myapp.dept.DeptDTO;
import org.firstzone.myapp.dept.DeptService;
import org.firstzone.myapp.emp.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
@RequestMapping("/dept")
public class DeptController {

	@Autowired
	DeptService dService;

	@Autowired
	EmpService eService;

	@GetMapping("/deptList.do")
	public void retrieve(Model model, HttpServletRequest request) {
		
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		String result="";
		if(flashMap!=null) {
			result = (String) flashMap.get("deptResult");
		}
		
		model.addAttribute("deptList", dService.selectAll());
		model.addAttribute("deptResult", result);
		
		// return�� void�� ��� ������ ����. ==> return "dept/deptList";
		// forward�ȴ�. /WEB-INF/views/dept/deptList.jsp
	}

	@GetMapping("/deptInsert.do")
	public ModelAndView insert(Model model) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("mlist", eService.selectAllManager());
		mv.setViewName("dept/deptInsert");
		return mv;
	}

	@PostMapping("deptInsert.do")
	public String insertDB(DeptDTO dept, Integer deptid2, Integer department_id, RedirectAttributes redirectAttr) {
		// System.out.println(dept);
		// System.out.println(deptid2);
		// System.out.println(department_id);

		int result = dService.deptInsert(dept);
		String message;

		if (result > 0) {
			message = "insert success";
		} else {
			message = "insert fail";
		}
		redirectAttr.addFlashAttribute("deptResult", message);

		// redirect:�� ���ٸ� default�� forward�̴�. ��, ��û�� �ּҴ� �״���̰� �������� page�� jsp�̴�.
		// forward�ȴ�. /WEB-INF/views/dept/deptList.jsp
		// request.getRequestDispatcher("�������̸�").forward(request, response)
		// redirect:�� �ִٸ� ���û�� �ǹ��Ѵ�. ���ο� ��û�̹Ƿ� �ּҰ� �ٲ��. �̶�, request�� ���޵��� �ʴ´�.
		// response.redirect("��û�ּ�");
		return "redirect:deptList.do";
	}

	@GetMapping("/deptUpdate.do")
	public void detail(Model model, HttpServletRequest request, HttpSession session,
			@RequestParam("deptid") Integer id) {

		System.out.println("getRemoteAddr:" + request.getRemoteAddr());
		session.setAttribute("deptid", id);
		session.setAttribute("myname", "����");
		model.addAttribute("dept", dService.selectById(id));
	}

	@PostMapping("/deptDetail.do")
	public String deptInfoView(@ModelAttribute("dept") DeptDTO dept, Model model) {
		// System.out.println(dept);

		// ** ���� �ڵ�� @ModelAttribute("dept")�� ��ü��.
		// model.addAttribute("dept", dept);

		model.addAttribute("mlist", eService.selectAllManager());
		return "dept/deptUpdate_DB";
	}

	@PostMapping("/deptDBUpdate.do")
	public String deptDBUpdate(DeptDTO dept, RedirectAttributes redirectAttr) {
		int result = dService.deptUpdate(dept);

		String message;

		if (result > 0) {
			message = "update success";
		} else {
			message = "update fail";
		}
		redirectAttr.addFlashAttribute("deptResult", message);

		return "redirect:deptList.do";
	}

	@GetMapping("/deptDelete.do")
	public String deptDelete(@RequestParam("deptid") Integer deptid, HttpServletRequest request, RedirectAttributes redirectAttr) {
		String deptid2 = request.getParameter("deptid");
		System.out.println("Spring�� ����:" + deptid);
		System.out.println("Servlet API �̿�:" + deptid2);
		int result = dService.deptDelete(deptid);
		
		String message;

		if (result > 0) {
			message = "delete success";
		} else {
			message = "delete fail";
		}
		redirectAttr.addFlashAttribute("deptResult", message);
		
		return "redirect:deptList.do";
	}
}