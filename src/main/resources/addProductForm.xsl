<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/shop">

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
				<form
					action="Controller?action=saveProduct&amp;catName={$catName}&amp;subcatName={$subcatName}"
					method="POST">
					<div>
						<label class="lab" for="model">Model</label>

						<input type="text" id="model" name="model" value="{$model}"></input>

						<label class="error">
							<xsl:value-of select="$modelError" />
						</label>
					</div>
					<div>
						<label class="lab" for="color">Color</label>

						<input type="text" id="color" name="color" value="{$color}"></input>

						<label class="error">
							<xsl:value-of select="$colorError" />
						</label>
					</div>
					<div>
						<label class="lab" for="dateOfIssue">Date of issue</label>

						<input type="text" id="dateOfIssue" name="dateOfIssue"
							value="{$dateOfIssue}"></input>

						<label class="error">
							<xsl:value-of select="$dateError" />
						</label>
					</div>
					<div>
						<label class="lab" for="producer">Producer</label>

						<input type="text" id="producer" name="producer" value="{$producer}"></input>

						<label class="error">
							<xsl:value-of select="$producerError" />
						</label>
					</div>
					<div>
						<label class="lab" for="price">Price</label>

						<input type="radio" name="notInStock" value="false"
							onchange="enableTxt()" checked="checked" />
						<input type="text" name="price" id="price" value="{$price}" />

						<label class="error">
							<xsl:value-of select="$priceError" />
						</label>
					</div>
					<div>
						<label class="lab">Not in Stock</label>

						<input type="radio" name="notInStock" value="true" onchange="disableTxt()" />

						<input type="submit" />
					</div>
				</form>
			</body>
		</html>
	</xsl:template>

</xsl:stylesheet>
