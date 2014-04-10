<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" />

	<xsl:template match="/shop" name="addingPage">

		<xsl:param name="catName" />
		<xsl:param name="subcatName" />

		<xsl:param name="model" />
		<xsl:param name="color" />
		<xsl:param name="dateOfIssue" />
		<xsl:param name="price" />
		<xsl:param name="producer" />
		<xsl:param name="notInStock" />


		<xsl:param name="modelError" />
		<xsl:param name="colorError" />
		<xsl:param name="dateError" />
		<xsl:param name="priceError" />
		<xsl:param name="producerError" />


		<html>
			<head>
				<link href="css/addProduct.css" rel="stylesheet" />
				<link href="css/style.css" rel="stylesheet" />

				<script>
					function disableTxt() {
					document.getElementById("price").disabled = true;
					}
					function
					enableTxt() {
					document.getElementById("price").disabled =
					false;
					}
				</script>
			</head>
			<body>
				<div id="main">
					<form id="product_form"
						action="Controller?action=saveProduct&amp;catName={$catName}&amp;subcatName={$subcatName}"
						method="POST">

						<div class="cont">
							<label class="lab" for="model">Model(2 letters and 3 digits)</label>
							<input type="text" id="model" name="model" value="{$model}" />
							<div class="error">
								<xsl:value-of select="$modelError" />

							</div>
						</div>

						<div class="cont">
							<label class="lab" for="color">Color</label>
							<input type="text" id="color" name="color" value="{$color}" />
							<label class="error">
								<xsl:value-of select="$colorError" />
							</label>
						</div>

						<div class="cont">
							<label class="lab" for="dateOfIssue">Date of issue(dd-mm-yyyy)</label>
							<input type="text" id="dateOfIssue" name="dateOfIssue"
								value="{$dateOfIssue}" />
							<div class="error">
								<xsl:value-of select="$dateError" />
							</div>
						</div>

						<div class="cont">
							<label class="lab" for="producer">Producer</label>
							<input type="text" id="producer" name="producer" value="{$producer}"></input>
							<label class="error">
								<xsl:value-of select="$producerError" />
							</label>
						</div>

						<div class="cont">
							<label class="lab" for="price">Price(float number)</label>
							<input type="text" name="price" value="{$price}" id="price" />
							<input type="radio" name="notInStock" checked="checked"
								value="false" onchange="enableTxt()" />
							<label class="error">
								<xsl:value-of select="$priceError" />
							</label>
						</div>

						<div class="cont">
							<label class="lab">Not in stock</label>
							<input type="radio" name="notInStock" value="true"
								onchange="disableTxt()" />
						</div>

						<div class="buttonsBar">

							<a class="myButton"
								href="Controller?action=showProducts&amp;catName={$catName}&amp;subcatName={$subcatName}">Back</a>

							<a class="myButton" href="" onclick="document.getElementById('product_form').submit(); return false;">Add</a>

						</div>
					</form>
				</div>
			</body>
		</html>
	</xsl:template>

</xsl:stylesheet>
