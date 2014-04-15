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
			<nested:nest property="doc.rootElement.children[${productForm.catIndex}]">
					<nested:iterate property="children" id="subcategory"
						indexId="subcatIndex">
						<li>
							<nested:define id="subcategoryName" property="attributeValue(name)" />
							<a href="shopAction.do?method=productlist&catIndex=${productForm.catIndex}&subcatIndex=${subcatIndex}">
							<bean:write name="subcategoryName" /></a>
						 (${subcategory.children.size()})</li>
					</nested:iterate>
				</nested:nest>
		</nested:root>
	</div>
</body>
</html>
