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
		
		// return이 void인 경우 다음과 같다. ==> return "dept/deptList";
		// forward된다. /WEB-INF/views/dept/deptList.jsp
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

		// redirect:이 없다면 default로 forward이다. 즉, 요청의 주소는 그대로이고 보여지는 page는 jsp이다.
		// forward된다. /WEB-INF/views/dept/deptList.jsp
		// request.getRequestDispatcher("페이지이름").forward(request, response)
		// redirect:이 있다면 재요청을 의미한다. 새로운 요청이므로 주소가 바뀐다. 이때, request는 전달되지 않는다.
		// response.redirect("요청주소");
		return "redirect:deptList.do";
	}

	@GetMapping("/deptUpdate.do")
	public void detail(Model model, HttpServletRequest request, HttpSession session,
			@RequestParam("deptid") Integer id) {

		System.out.println("getRemoteAddr:" + request.getRemoteAddr());
		session.setAttribute("deptid", id);
		session.setAttribute("myname", "정인");
		model.addAttribute("dept", dService.selectById(id));
	}

	@PostMapping("/deptDetail.do")
	public String deptInfoView(@ModelAttribute("dept") DeptDTO dept, Model model) {
		// System.out.println(dept);

		// ** 다음 코드는 @ModelAttribute("dept")로 대체함.
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
		System.out.println("Spring이 전달:" + deptid);
		System.out.println("Servlet API 이용:" + deptid2);
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
