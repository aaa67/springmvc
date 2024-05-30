package com.shinhan.myapp.controller;

import java.util.List;

import org.firstzone.myapp.dept.DeptDTO;
import org.firstzone.myapp.dept.DeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // @Controller + @ResponseBody
@RequestMapping("/dept/api/*")
public class DeptRestController {

	@Autowired
	DeptService dService;
	
	Logger logger = LoggerFactory.getLogger(DeptRestController.class);
	
	//삭제
	@DeleteMapping(value = "/delete/{deptid}",
		           produces = "text/plain;charset=utf-8")
	public String delete(@PathVariable("deptid") Integer id) {
		int result = dService.deptDelete(id);
		return result>0?"삭제 성공":"삭제 실패";
	}
	
	//수정
	@PutMapping(value = "/update", 
			    consumes="application/json", 
			    produces = "text/plain;charset=utf-8")
	public String update(@RequestBody DeptDTO dept) {
		int result = dService.deptUpdate(dept);
		return "수정건수: " + result + "건";
	}
	
	//Post는 요청 문서의 body에 data가 들어온다.
	@PostMapping(value = "/insert", 
			     consumes = MediaType.APPLICATION_JSON_VALUE,
			     produces = "text/plain;charset=utf-8")
	public String insert(@RequestBody DeptDTO dept) {
		logger.info("들어온 dept: " + dept);
		int result = dService.deptInsert(dept);
		return "insert 결과:" + result;
	}

	// JSON으로 내보내기
	// jackson-databind를 이용해서 자바의 객체가 JSON으로 convert된다.
	// produces = "application/json"
	@GetMapping(value = "/deptAll", produces = "application/json")
	public List<DeptDTO> selectAll() {
		List<DeptDTO> deptlist = dService.selectAll();
		return deptlist;
	}

	// JSON으로 내보내기
	// jackson-databind를 이용해서 자바의 객체가 JSON으로 convert된다.
	@GetMapping(value = "/detail/{deptid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public DeptDTO selectById(@PathVariable("deptid") Integer deptid) {
		DeptDTO dept = dService.selectById(deptid);
		return dept;
	}

	// 가져오기: consumes 설정
	// 내보내기: produces 설정
	@GetMapping(value = "/test1", produces = "text/plain;charset=utf-8")
	public String test1() {
		return "Restful 방식 연습";
	}
}
