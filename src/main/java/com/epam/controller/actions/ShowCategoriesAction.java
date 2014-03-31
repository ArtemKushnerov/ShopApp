package com.epam.controller.actions;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.util.HTMLWriter;

public class ShowCategoriesAction implements Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter resultWriter = resp.getWriter();

		InputStream styleSheet = ShowCategoriesAction.class
				.getResourceAsStream("/showCategories.xsl");
		InputStream shop = ShowCategoriesAction.class
				.getResourceAsStream("/shop.xml");
		HTMLWriter.write(styleSheet, shop, resultWriter);
	}

}
