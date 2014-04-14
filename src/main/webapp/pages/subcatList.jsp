<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/style.css" rel="stylesheet" />

<title>Product Shop</title>
</head>
<body>
	<div id="main">
		<c:forEach
			items="${productForm.doc.rootElement.children.child(requestScope.category).children)}"
			var="subcategory">
			<c:set var="count" value="${subcategory.children.size()}" />
			<a href="#">${subcategory.getAttributeValue("name")}
				(${count}) </a>
			<br />
		</c:forEach>
	</div>
</body>
</html> --%>