package com.epam.controller.actions;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.util.HTMLWriter;

public class ShowSubcategoriesAction implements Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter resultWriter = resp.getWriter();

		InputStream styleSheet = ShowSubcategoriesAction.class
				.getResourceAsStream("/showSubcategories.xsl");
		InputStream catalog = ShowSubcategoriesAction.class
				.getResourceAsStream("/shop.xml");
		String catName = req.getParameter("catName");
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("catName", catName);
		HTMLWriter.write(styleSheet, catalog, resultWriter, paramsMap);
	}

}
