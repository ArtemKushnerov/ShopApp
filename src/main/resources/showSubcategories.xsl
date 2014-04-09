<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/shop">

		<xsl:param name="catName" />

		<html>
			<head>
				<link href="css/style.css" rel="stylesheet" />
			</head>

			<body>
				<div id="main">
					<xsl:for-each select="category[@name=$catName]/subcategory">
						<xsl:variable name="subcatName">
							<xsl:value-of select="@name" />
						</xsl:variable>

						<p>
							<a
								href="Controller?action=showProducts&amp;catName={$catName}&amp;subcatName={$subcatName}">
								<xsl:value-of select="@name" />
								(
								<xsl:value-of select="count(.//product)" />
								)
							</a>
						</p>
					</xsl:for-each>
					<div class="buttonsBar">

						<button class="myButton">
							<a href="Controller?action=showCategories">
								Back
							</a>
						</button>

					</div>
				</div>
			</body>
		</html>
	</xsl:template>

</xsl:stylesheet>
