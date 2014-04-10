<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/shop">

		<xsl:param name="catName" />
		<xsl:param name="subcatName" />
		<html>
			<head>
				<link href="css/style.css" rel="stylesheet" />
			</head>
			<body>
				<div id="main">
					<table border="1" cellpadding="6">
						<tr>
							<th>Producer</th>
							<th>Model</th>
							<th>Date of issue</th>
							<th>Color</th>
							<th>Price</th>
						</tr>

						<xsl:for-each

							select="category[@name=$catName]/subcategory[@name=$subcatName]/product">
							<tr>
								<td>
									<xsl:value-of select="producer" />
								</td>
								<td>
									<xsl:value-of select="model" />
								</td>
								<td>
									<xsl:value-of select="date_of_issue" />
								</td>
								<td>
									<xsl:value-of select="color" />
								</td>
								<td>
									<xsl:choose>
										<xsl:when test="not_in_stock">
											not in stock
										</xsl:when>
										<xsl:otherwise>
											<xsl:value-of select="price" />
										</xsl:otherwise>
									</xsl:choose>
								</td>
							</tr>
						</xsl:for-each>
					</table>
					<div class="buttonsBar">

						<a class="myButton"
							href="Controller?action=showSubcategories&amp;catName={$catName}">
							Back
						</a>
						<a class="myButton"
							href="Controller?action=addNewProduct&amp;catName={$catName}&amp;subcatName={$subcatName}">
							Add
						</a>
					</div>
				</div>
			</body>
		</html>
	</xsl:template>

</xsl:stylesheet>
