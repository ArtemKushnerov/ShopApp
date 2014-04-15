<%@ include file="../includes/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<link href="css/style.css" rel="stylesheet" />
<title>Product Shop</title>
</head>
<body>
	<div id="main">
		<nested:root name="productForm">
			<nested:iterate property="doc.rootElement.children" id="category"
				indexId="index">
				<nested:define id="categoryName" property="attributeValue(name)" />

				<li><a href="shopAction.do?method=subcatlist&catIndex=${index}">${categoryName}</a>
					<c:set var="sum" value="${0}" /> <c:forEach var="subcategory"
						items="${category.children}">
						<c:set var="sum" value="${sum + subcategory.children.size()}" />
					</c:forEach> (<c:out value="${sum}" />)</li>
			</nested:iterate>
		</nested:root>
	</div>
</body>
</html>