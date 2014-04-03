<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:validator="xalan://com.epam.util.Validator">
	<xsl:import href="addProductForm.xsl" />

	<xsl:param name="catName" />
	<xsl:param name="subcatName" />

	<xsl:param name="producer" />
	<xsl:param name="model" />
	<xsl:param name="color" />
	<xsl:param name="dateOfIssue" />
	<xsl:param name="price" />
	<xsl:param name="notInStock" />
	<xsl:param name="errors" />


	<xsl:output method="xml" indent="yes" />

	<xsl:template match="/">
		<xsl:choose>
			<xsl:when
				test="validator:validate($producer,$model,$color,$dateOfIssue,$price,$notInStock,$errors)">
				<xsl:call-template name="copyNodes" />
			</xsl:when>
			<xsl:otherwise>
				<xsl:apply-imports />
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

	<xsl:template name="copyNodes" match="/|node()|@*">
		<xsl:copy>
			<xsl:apply-templates select="node()|@*" />
		</xsl:copy>
	</xsl:template>

	<xsl:template name="addProduct"
		match="/shop/category[@name=$catName]/subcategory[@name=$subcatName]">
		<xsl:element name="subcategory">
			<xsl:attribute name="name">
				<xsl:value-of select="$subcatName" />
			</xsl:attribute>

			<xsl:apply-templates />
			<xsl:element name="product">
				<xsl:element name="producer">
					<xsl:value-of select="$producer" />
				</xsl:element>
				<xsl:element name="model">
					<xsl:value-of select="$model" />
				</xsl:element>
				<xsl:element name="date_of_issue">
					<xsl:value-of select="$dateOfIssue" />
				</xsl:element>
				<xsl:element name="color">
					<xsl:value-of select="$color" />
				</xsl:element>
				<xsl:choose>
					<xsl:when test="not($notInStock)">
						<xsl:element name="price">
							<xsl:value-of select="$price" />
						</xsl:element>
					</xsl:when>
					<xsl:otherwise>
						<xsl:element name="not_in_stock" />
					</xsl:otherwise>
				</xsl:choose>
			</xsl:element>
		</xsl:element>
	</xsl:template>
</xsl:stylesheet>