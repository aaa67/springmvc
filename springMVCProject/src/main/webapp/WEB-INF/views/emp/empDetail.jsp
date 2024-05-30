<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="../common/header.jsp" %>
<h1>직원의 상세보기(수정가능)</h1>
<form action="${path}/emp/empDetail.do" method="post">
    <div class="mb-3 mt-3">
      <label for="employee_id">직원번호:</label>
      <input type="number" class="form-control" id="employee_id" placeholder="Enter employee_id" name="employee_id" value="${empInfo.employee_id}" required>
    </div>
    <div class="mb-3 mt-3">
      <label for="first_name">first_name:</label>
      <input type="text" class="form-control" id="first_name" placeholder="Enter first_name" name="first_name" value="${empInfo.first_name}">
    </div>
    <div class="mb-3 mt-3">
      <label for="last_name">last_name:</label>
      <input type="text" class="form-control" id="last_name" placeholder="Enter last_name" name="last_name" value="${empInfo.last_name}" required>
    </div>
    <div class="mb-3 mt-3">
      <label for="email">email:</label>
      <input type="email" class="form-control" id="email" placeholder="Enter email" name="email" value="${empInfo.email}" required>
    </div>
    <div class="mb-3 mt-3">
      <label for="phone_number">phone_number:</label>
      <input type="text" class="form-control" id="phone_number" placeholder="Enter phone_number" name="phone_number" value="${empInfo.phone_number}">
    </div>
    <%-- ScriptLet 문법보다는 EL(반복문없음):${}, JSTL문법:<c:forEach>을 사용하는 것이 좋다. 
      for(String job:jlist){}
    --%>
    <div class="mb-3 mt-3">
      <label for="job_id">job_id:</label>
      <select name="job_id">
        <c:forEach items="${joblist}" var="job">
          <option value="${job.job_id}" ${empInfo.job_id==job.job_id?"selected":""}>${job.job_title}</option>
        </c:forEach>
      <%-- <%
      List<String> jlist = (List<String>)request.getAttribute("jlist");
      for(String job:jlist){ %>
      <option value="<%=job %>"> <%=job %> </option>
      <%} %> --%>
      </select>
    </div>
    <div class="mb-3 mt-3">
      <label for="manager_id">manager_id:</label>
      <select name="manager_id">
      <option value="0">매니저 없음</option>
       <c:forEach items="${managerlist}" var="manager">
         <option value="${manager.employee_id}" ${empInfo.manager_id==manager.employee_id?"selected":""}>${manager.employee_id} / ${manager.fullname}</option>
       </c:forEach>
      </select>
    </div>
    <div class="mb-3 mt-3">
      <label for="department_id">department_id:</label>
      <select name="department_id">
      <option value="0">부서 없음</option>
      <c:forEach items="${deptlist}" var="dept">
         <option value="${dept.department_id}" ${empInfo.department_id==dept.department_id?"selected":""}>${dept.department_id} / ${dept.department_name}</option>
       </c:forEach>
      </select>
    </div>
    <div class="mb-3 mt-3">
      <label for="salary">salary:</label>
      <input type="number" class="form-control" id="salary" placeholder="Enter salary" name="salary" value="${empInfo.salary}">
    </div>
    <div class="mb-3 mt-3">
      <label for="hire_date">hire_date:</label>
      <input type="date" class="form-control" id="hire_date" placeholder="Enter hire_date" name="hire_date" value="${empInfo.hire_date}" required>
    </div>
    <div class="mb-3 mt-3">
      <label for="commission_pct">commission_date:</label>
      <input type="text" class="form-control" id="commission_pct" placeholder="Enter commission_pct" name="commission_pct" value="${empInfo.commission_pct}">
    </div>
    <input type="submit" class="btn btn-primary" value="수정하기">
  </form>
</body>
</html>