<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<h1>Enter the Group By Clause followed by Values or just Enter All example area,Guindy,Madipakkam or all or street,gandhinagar it works for any FIELD</h1>
</head>
<body>

<sf:form modelAttribute="userBean">
	
	<sf:label path="location"></sf:label>
	<sf:input path="location" />

	<input name="_eventId_submit" type="submit" value="Get" />
	
</sf:form>
</body>
</html>