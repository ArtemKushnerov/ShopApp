<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
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
		<c:forEach items="${productForm.doc.rootElement.children}"
			var="category">
			<a href="#">${category.getAttributeValue("name")}
				(${category.children.size()}) </a>
			<br />
		</c:forEach>
	</div>

</body>
</html>