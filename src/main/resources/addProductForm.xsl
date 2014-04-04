<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

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
					<form
						action="Controller?action=saveProduct&amp;catName={$catName}&amp;subcatName={$subcatName}"
						method="POST">

						<label class="lab" for="model">Model</label>

						<div class="cont">
							<input type="text" id="model" name="model" value="{$model}" />
						</div>

						<label class="error">
							<xsl:value-of select="$modelError" />
							
						</label>


						<label class="lab" for="color">Color</label>

						<div class="cont">
							<input type="text" id="color" name="color" value="{$color}" />
						</div>

						<label class="error">
							<xsl:value-of select="$colorError" />
						</label>


						<label class="lab" for="dateOfIssue">Date of issue</label>
						<div class="cont">
							<input type="text" id="dateOfIssue" name="dateOfIssue"
								value="{$dateOfIssue}" />
						</div>
						<label class="error">
							<xsl:value-of select="$dateError" />
						</label>


						<label class="lab" for="producer">Producer</label>
						<div class="cont">
							<input type="text" id="producer" name="producer" value="{$producer}"></input>
						</div>
						<label class="error">
							<xsl:value-of select="$producerError" />
						</label>


						<label class="lab" for="price">Price</label>
						<div class="cont">

							<input type="text" name="price" value="{$price}" />
							<input type="radio" name="notInStock" value="false"
								onchange="enableTxt()" checked="checked" />
						</div>
						<label class="error">
							<xsl:value-of select="$priceError" />
						</label>


						<label class="lab">Not in Stock</label>
						<div class="cont">
							<input type="radio" name="notInStock" value="true"
								onchange="disableTxt()" />
						</div>


						<div class="buttonsBar">
							<input class="myButton" type="submit" value="Add" />
							<button class="myButton" onclick="javascript:history.back(); return false;">
								Back
							</button>
						</div>
					</form>
				</div>
			</body>
		</html>
	</xsl:template>

</xsl:stylesheet>
