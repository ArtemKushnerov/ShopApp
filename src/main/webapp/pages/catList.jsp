<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/style.css" rel="stylesheet" />

<title>Product Shop</title>
</head>
<body>
	<div id="main">
			<nested:root name="productForm">
				<nested:iterate property="doc.rootElement.children"
					id="category" indexId="index">
					<nested:define id="categoryName" property="attributeValue(name)" />

					<li><a href="catalog.do?method=subcatList&catIndex=${index}">${categoryName}</a>
						<c:set var="sum" value="${0}" /> 
						<c:forEach var="subcategory" items="${category.children}">
							<c:set var="sum" value="${sum + subcategory.children[0].children.size()}" />
						</c:forEach> (<c:out value="${sum}" />)</li>
				</nested:iterate>
			</nested:root>
	</div>
</body>
</html>