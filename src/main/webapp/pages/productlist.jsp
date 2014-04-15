<%@ include file="../includes/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<link href="css/style.css" rel="stylesheet" />
<title>Product Shop</title>
</head>
<body>
<body>
	<nested:root name="productForm">
		<nested:form
			action="/shopAction.do?method=save&catIndex=${productForm.catIndex}&subcatIndex=${productForm.subcatIndex}"
			styleId="productForm">
			<div id="block">
				<table cellpadding="6" class="table" style="width: 900px;">
					<tr>
						<th>producer</th>
						<th>model(2 letters 3 digits)</th>
						<th>color</th>
						<th>date of issue (dd-MM-yyyy)</th>
						<th>price</th>
					</tr>
					<nested:iterate id="tmpProduct"
						property="doc.rootElement.children[${productForm.catIndex}].children[${productForm.subcatIndex}].children">
						<tr>
							<td><nested:text property="children[0].text"
									styleClass="producer" /></td>
							<td><nested:text property="children[1].text"
									styleClass="model" /></td>
							<td><nested:text property="children[2].text"
									styleClass="color" /></td>
							<td><nested:text property="children[3].text"
									styleClass="dateOfIssue" /></td>
							<td><nested:text property="children[4].text"
									styleClass="price" /></td>
						</tr>
					</nested:iterate>
				</table>
				<a
					href="shopController.do?action=subcategories&catIndex=${productForm.catIndex}"
					class="btn btn-default" id="backBtn">Back</a> <input type="submit"
					value="Save" id="saveBtn" class="btn btn-default" />
					 <a href="catalog.do?action=add&catIndex=${productForm.catIndex}&subcatIndex=${productForm.subcatIndex}" class="btn btn-default">Add</a>
			</div>
		</nested:form>
	</nested:root>
	<script
		src='${pageContext.request.contextPath}/js/lib/jquery-1.11.0.min.js'></script>
	<script
		src='${pageContext.request.contextPath}/js/lib/jquery.validate.min.js'></script>
	<script src='${pageContext.request.contextPath}/js/products.js'></script>
</body>
</html>