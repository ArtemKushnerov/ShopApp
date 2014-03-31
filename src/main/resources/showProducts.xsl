<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/shop">

		<xsl:param name="catName" />
		<xsl:param name="subcatName" />
		<html>
			<body>
				<table border="1" cellpadding="6">
					<tr>
						<th>producer</th>
						<th>model</th>
						<th>date of issue</th>
						<th>color</th>
						<th>price</th>
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
				<a
					href="Controller?action=addNewProduct&amp;catName={$catName}&amp;subcatName={$subcatName}">
					Add new product
				</a>

			</body>
		</html>
	</xsl:template>

</xsl:stylesheet>
