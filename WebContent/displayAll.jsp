<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<sf:form>
		
		<c:forEach  items="${userBean.employees}"  var="element">
    		<div>
    			<span>${element.getName()}</span>
    			<span>${element.getEid()}</span>
    			<span>${element.getMno()}</span>
    			<span>${element.getSalary()}</span>
    			
    			<span>${element.getStreet()}</span>
    			<span>${element.getArea()}</span>
    			<span>${element.getCity()}</span>
    			<span>${element.getPin()}</span>
    			
    		</div>
    		<hr>
		</c:forEach>
		<a href="memberShip.obj" style="margin: 50;">Employee Details</a>
		
	</sf:form>
</body>
</html>