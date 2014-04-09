<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/shop">
		<html>
			<head>
				<link href="css/style.css" rel="stylesheet" />
			</head>

			<body>
				<div id="main">
					<xsl:for-each select="category">
						<xsl:variable name="catName">
							<xsl:value-of select="@name" />
						</xsl:variable>
						<p>
							<a href="Controller?action=showSubcategories&amp;catName={$catName}">
								<xsl:value-of select="@name" />
								(
								<xsl:value-of select="count(.//product)" />
								)
							</a>
						</p>
					</xsl:for-each>
				</div>
			</body>
		</html>
	</xsl:template>

</xsl:stylesheet>
