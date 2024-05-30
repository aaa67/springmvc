<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	Hello!!!!
	<h1>이름은 ${myname}</h1>
	<h1>myscore은 ${myscore}</h1>

	<h2>Get 방식 요청(요청 주소에 param 이용: 값이 일치 / field 존재 / 값 존재하지 않음)</h2>
	<form action="${pageContext.servletContext.contextPath}/sample/hello6.do">
		email : <input type="email" name="email" value="zzilre@gmail.com"><br>
		password: <input type="password" name="pwd" value="1234"><br>
		phone: <input type="text" name="phone" value="010-1234-5678"><br>
		<input type="submit" value="서버전송(get)">
	</form>
	
	<hr>
	
	<h2>Post 방식 요청(요청 주소에 param 이용: 값이 일치 / field 존재 / 값 존재하지 않음)</h2>
	<form 
	method="post"
	action="${pageContext.servletContext.contextPath}/sample/hello6.do">
		email : <input type="email" name="email" value="zzilre@gmail.com"><br>
		password: <input type="password" name="pwd" value="1234"><br>
		phone: <input type="text" name="phone" value="010-1234-5678"><br>
		<input type="submit" value="서버전송(get)">
	</form>
</body>
</html>