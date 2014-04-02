package com.epam.controller.actions;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.util.HTMLWriter;

public class AddNewProductAction implements Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		PrintWriter resultWriter = resp.getWriter();
		InputStream styleSheet = AddNewProductAction.class
				.getResourceAsStream("/addProductForm.xsl");
		InputStream shop = AddNewProductAction.class
				.getResourceAsStream("/shop.xml");
		String catName = req.getParameter("catName");
		String subcatName = req.getParameter("subcatName");
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("catName", catName);
		paramsMap.put("subcatName", subcatName);
		addPreviousValues(req, paramsMap);
		HTMLWriter.write(styleSheet, shop, resultWriter, paramsMap);
	}

	private void addPreviousValues(HttpServletRequest req,
			Map<String, String> paramsMap) {
		Map<String,String> errors = (Map<String, String>) req.getAttribute("errors");
		if (errors != null) {
			paramsMap.put("model", req.getParameter("model"));
			paramsMap.put("color", req.getParameter("color"));
			paramsMap.put("dateOfIssue", req.getParameter("dateOfIssue"));
			paramsMap.put("price", req.getParameter("price"));
			paramsMap.put("producer", req.getParameter("producer"));
			paramsMap.put("notInStock", req.getParameter("notInStock"));
			paramsMap.putAll(errors);
		}
	}

}
