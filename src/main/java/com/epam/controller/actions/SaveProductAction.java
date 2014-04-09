package com.epam.controller.actions;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.util.RWLockSingleton;
import com.epam.util.StringHolder;
import com.epam.util.Validator;
import com.epam.util.XSLManager;

public class SaveProductAction implements Action {

	private static final String SAVE_PRODUCT_XSL = "/saveProduct.xsl";

	private void fillParamsMap(Map<String, Object> paramsMap,
			HttpServletRequest req) {
		String catName = req.getParameter("catName");
		String subcatName = req.getParameter("subcatName");

		String model = req.getParameter("model");
		String color = req.getParameter("color");
		String dateOfIssue = req.getParameter("dateOfIssue");
		String price = req.getParameter("price") == null ? "" : req
				.getParameter("price");
		String producer = req.getParameter("producer");
		boolean notInStock = "true".equals(req.getParameter("notInStock"));

		paramsMap.put("catName", catName);
		paramsMap.put("subcatName", subcatName);
		paramsMap.put("model", model);
		paramsMap.put("color", color);
		paramsMap.put("dateOfIssue", dateOfIssue);
		paramsMap.put("price", price);
		paramsMap.put("producer", producer);
		paramsMap.put("notInStock", notInStock);
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		String catName = req.getParameter("catName");
		String subcatName = req.getParameter("subcatName");

		InputStream shop = SaveProductAction.class
				.getResourceAsStream(StringHolder.SHOP_XML);

		Writer buffer = new StringWriter();

		Map<String, Object> paramsMap = new HashMap<String, Object>();
		fillParamsMap(paramsMap, req);
		Validator validator = new Validator();
		paramsMap.put("validator", validator);
		
		File shopFile = new File(StringHolder.SHOP_REAL_PATH);
		long lastMod = shopFile.lastModified();

		Lock readLock = RWLockSingleton.INSTANCE.readLock();
		readLock.lock();
		try {
			XSLManager.makeTransform(SAVE_PRODUCT_XSL, shop, buffer, paramsMap);
		} finally {
			readLock.unlock();
		}

		if (validator.getErrors().isEmpty()) {
			Lock writeLock = RWLockSingleton.INSTANCE.writeLock();
			writeLock.lock();
			try {
				if (lastMod != shopFile.lastModified()) {
					XSLManager.makeTransform(SAVE_PRODUCT_XSL, shop, buffer,
							paramsMap);
				}
				Writer fileWriter = new PrintWriter(shopFile, "UTF-8");
				fileWriter.write(buffer.toString());
				fileWriter.flush();
				fileWriter.close();

			} finally {
				writeLock.unlock();
			}
			resp.sendRedirect("Controller?action=showProducts&catName="
					+ catName + "&subcatName=" + subcatName);

		} else {
			Writer out = resp.getWriter();
			out.write(buffer.toString());
		}

	}

}
