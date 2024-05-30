<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	
<script src="/webshop10/static/js/jquery-3.7.1.min.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<c:set var="path" value="${pageContext.servletContext.contextPath}"/>
<script>
	$(function () {
		// form 찾아서 onsubmit 이벤트와 연결
		$("form").on("submit", call);
		
		// 아이디 중복 체크
		$("#btnDupCheck").on("click", f_dupCheck);
	});
	
	function f_dupCheck() {
		// var empid = $("#employee_id").val(); // employee_id 값
		var empid = $("#employee_id").val();
		
		if (empid == "") {
			alert("직원 번호를 입력하세요.");
			document.querySelector("#employee_id").focus();
			
			return;
		}
		
		$.ajax({ // DB에 가져갈 정보
			url : "${path}/emp/empIdCheck.do",
			data : {"empid" : $("#employee_id").val()}, // 키 : 값
			type : "get",
			success : function (responseData) { // EmpIdCheckServlet에서 보낸 문자열이 담겨 옴
				var message = "";
				
				if (responseData == "0") {
					message = "사용 가능";
					
				} else {
					message = "사용 불가능";
					
					$("#employee_id").val("");
					document.querySelector("#employee_id").focus();
				}
				
				$("#resultMessage").val(message);
			},
			error : function (data) {
				alert(data);
			} 
		});
	}
	
	// 태그에 required 속성을 주면 입력 했는지 확인하는 코드는 작성 할 필요 없음
	function call(event) {
		var salary = $("#salary").val();
		
		if (salary == "" || salary <= 0) {
			alert("salary 값은 0보다 큰 값이여야 합니다");
			
			event.preventDefault(); // preventDefault() : default 이벤트 취소 (서버 전송 취소)
			document.querySelector("#salary").focus(); // 입력하지 않으면 포커스 주기
		}
	}
</script>
</head>
<body>
	<div class="container mt-3">
		<a href="${path}/emp/empAll.do">직원 목록 보기</a>
		<h2>신규 직원 등록</h2>
		<form action="${path}/emp/empInsert.do" method="post">
			<div class="mb-3 mt-3">
				<label for="employee_id">직원 번호 : </label>
				<input type="number" class="form-control" id="employee_id" placeholder="employee_id" name="employee_id" required>
				<input type="button" value="중복체크" id="btnDupCheck">
				<input id="resultMessage" type="text" value="ID 입력 후 중복체크">
			</div>
			<div class="mb-3 mt-3">
				<label for="first_name">이름 : </label>
				<input type="text" class="form-control" id="first_name" placeholder="first_name" name="first_name" required>
			</div>
			<div class="mb-3 mt-3">
				<label for="last_name">last_name : </label>
				<input type="text" class="form-control" id="last_name" placeholder="last_name" name="last_name" required>
			</div>
			<div class="mb-3 mt-3">
				<label for="email">email : </label>
				<input type="text" class="form-control" id="email" placeholder="email" name="email" required>
			</div>
			<div class="mb-3 mt-3">
				<label for="phone_number">전화번호 : </label>
				<input type="text" class="form-control" id="phone_number" placeholder="phone_number" name="phone_number">
			</div>
			<div class="mb-3 mt-3">
				<label for="hire_date">입사일 : </label>
				<input type="date" class="form-control" id="hire_date" placeholder="hire_date" name="hire_date" required>
			</div>
			<%-- 
				<% %> : ScriptLet 문법 (자바 문법)
						-> EL(반복문 없음, ${} 문법) / JSTL(반복문 있음, <c:forEach> 문법) 문법을 사용하는 것이 좋다. 
				for (JobDTO job : joblist) {} ==> <c:forEach items="${ joblist }" var="job"> 같은 의미!!!!
			
			--%>
			<div class="mb-3 mt-3">
				<label for="job_id">job_id : </label>
				<select name="job_id">
					<%-- ${ } : getAttribute --%>
					<c:forEach items="${ joblist }" var="job">
						<option value="${job.job_id}">${ job.job_title }</option>
					</c:forEach>
				</select>
			</div>
			<div class="mb-3 mt-3">
				<label for="salary">급여 : </label>
				<input type="number" class="form-control" id="salary" placeholder="salary" name="salary">
			</div>
			<div class="mb-3 mt-3">
				<label for="commission_pct">커미션 : </label>
				<input type="text" class="form-control" id="commission_pct" placeholder="commission_pct" name="commission_pct">
			</div>
			<div class="mb-3 mt-3">
				<label for="manager_id">메니져 번호 : </label>
				<select name="manager_id">
				<option value="0">매니저 없음</option>
					<c:forEach items="${ managerlist }" var="manager">
						<option value="${ manager.employee_id }">${ manager.fullname}</option>
					</c:forEach>
				</select>
			</div>
			<div class="mb-3 mt-3">
				<label for="department_id">department_id : </label>
				<select name="department_id">
				<option value="0">부서 없음</option>
					<c:forEach items="${ deptlist }" var="dept">
						<option value="${ dept.department_id }">${ dept.department_name }</option>
					</c:forEach>
				</select>
			</div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
</body>
</html>