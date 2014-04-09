<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:validatorHolder="xalan://com.epam.util.ValidatorHolder">
	<xsl:import href="/workspace/shopApp/src/main/resources/addProductForm.xsl" />


	<xsl:param name="catName" />
	<xsl:param name="subcatName" />

	<xsl:param name="producer" />
	<xsl:param name="model" />
	<xsl:param name="color" />
	<xsl:param name="dateOfIssue" />
	<xsl:param name="price" />
	<xsl:param name="notInStock" />
	<xsl:param name="validator" />


	<xsl:template match="/">
		<xsl:choose>
			<xsl:when
				test="validatorHolder:validate($validator,$producer,$model,$color,$dateOfIssue,$price,$notInStock)">
				<xsl:call-template name="copyNodes" />
			</xsl:when>
			<xsl:otherwise>
				<xsl:call-template name="addingPage">
					<xsl:with-param name="catName" select="$catName" />
					<xsl:with-param name="subcatName" select="$subcatName" />

					<xsl:with-param name="model" select="$model" />
					<xsl:with-param name="price" select="$price" />
					<xsl:with-param name="producer" select="$producer" />
					<xsl:with-param name="color" select="$color" />
					<xsl:with-param name="notInStock" select="$notInStock" />
					<xsl:with-param name="dateOfIssue" select="$dateOfIssue" />

					<xsl:with-param name="modelError" select="validatorHolder:getModelError($validator)" />
					<xsl:with-param name="priceError" select="validatorHolder:getPriceError($validator)" />
					<xsl:with-param name="producerError" select="validatorHolder:getProducerError($validator)" />
					<xsl:with-param name="colorError" select="validatorHolder:getColorError($validator)" />
					<xsl:with-param name="dateError" select="validatorHolder:getDateError($validator)" />
				</xsl:call-template>

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