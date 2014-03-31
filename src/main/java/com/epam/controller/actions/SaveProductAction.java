package com.epam.controller.actions;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.util.HTMLWriter;

public class SaveProductAction implements Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String catName = req.getParameter("catName");
		String subcatName = req.getParameter("subcatName");

		String model = req.getParameter("model");
		String color = req.getParameter("color");
		String dateOfIssue = req.getParameter("dateOfIssue");
		String price = req.getParameter("price");
		String producer = req.getParameter("producer");
		String notInStock = req.getParameter("notInStock");

		InputStream styleSheet = SaveProductAction.class
				.getResourceAsStream("/saveProduct.xsl");
		InputStream catalog = SaveProductAction.class
				.getResourceAsStream("/shop.xml");

		Writer resultWriter = new StringWriter();

		Map<String, String> transParams = new HashMap<String, String>();
		transParams.put("catName", catName);
		transParams.put("subcatName", subcatName);
		transParams.put("model", model);
		transParams.put("color", color);
		transParams.put("dateOfIssue", dateOfIssue);
		transParams.put("price", price);
		transParams.put("producer", producer);
		transParams.put("notInStock", notInStock);
		Map<String,String> errors = new HashMap<String,String>();
		
		HTMLWriter.save(styleSheet, catalog, resultWriter, transParams,errors);

		System.out.println(errors.entrySet());
		String pathToCatalog = req.getServletContext().getRealPath("WEB-INF/classes/shop.xml");	
		File catalogFile=new File(pathToCatalog); //"d:/catalog.xml"
		Writer fileWriter = new PrintWriter(catalogFile, "UTF-8");
		fileWriter.write(resultWriter.toString());
		fileWriter.flush();
		fileWriter.close();

		String redirect = "Controller?action=showProducts&catName="+catName+"&subcatName="+
				subcatName;
			resp.sendRedirect(redirect);
	}

}
