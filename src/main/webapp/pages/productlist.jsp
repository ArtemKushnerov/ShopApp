<%@ include file="../includes/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<link href="css/style.css" rel="stylesheet" />
<title>Product Shop</title>
</head>
<body>
<body>
	<div id="main">
		<nested:root name="productForm">
			<nested:form
				action="/shopAction.do?method=save&catIndex=${productForm.catIndex}&subcatIndex=${productForm.subcatIndex}"
				styleId="productForm">
				<div id="block">
					<table cellpadding="6" style="width: 900px;">
						<tr>
							<th>Producer</th>
							<th>Model(2 letters 3 digits)</th>
							<th>Date of issue (dd-MM-yyyy)</th>
							<th>Color</th>
							<th>Price</th>
						</tr>
						<nested:iterate id="tmpProduct"
							property="doc.rootElement.children[${productForm.catIndex}].children[${productForm.subcatIndex}].children">
							<tr>
								<td><nested:text property="children[0].text" styleClass="producer" /></td>
								<td><nested:text property="children[1].text" styleClass="model" /></td>
								<td><nested:text property="children[2].text" styleClass="date"/></td>
								<td><nested:text property="children[3].text" styleClass="color"/></td>
								<td><nested:text property="children[4].text" styleClass="price"/></td>
							</tr>
						</nested:iterate>
					</table>
					<a
						href="shopAction.do?method=subcatlist&catIndex=${productForm.catIndex}">Back</a>
					<input type="submit" value="Save" /> <a
						href="shopAction.do?method=add&catIndex=${productForm.catIndex}&subcatIndex=${productForm.subcatIndex}">Add</a>
				</div>
			</nested:form>
		</nested:root>
	</div>
	<script
		src='${pageContext.request.contextPath}/js/jquery-1.9.1.js'></script>
	<script
		src='${pageContext.request.contextPath}/js/jquery.validate.min.js'></script>
	<script src='${pageContext.request.contextPath}/js/validation.js'></script>
</body>
</html>