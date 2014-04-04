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
import com.epam.util.Validator;
import com.epam.util.XSLManager;

public class SaveProductAction implements Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		String catName = req.getParameter("catName");
		String subcatName = req.getParameter("subcatName");

		String model = req.getParameter("model");
		String color = req.getParameter("color");
		String dateOfIssue = req.getParameter("dateOfIssue");
		String price = req.getParameter("price") == null ? "" : req
				.getParameter("price");
		String producer = req.getParameter("producer");
		boolean notInStock = "true".equals(req.getParameter("notInStock"));

		String styleSheet = "/saveProduct.xsl";
		InputStream catalog = SaveProductAction.class
				.getResourceAsStream("/shop.xml");

		Writer resultWriter = new StringWriter();

		Map<String, Object> transParams = new HashMap<String, Object>();
		transParams.put("catName", catName);
		transParams.put("subcatName", subcatName);
		transParams.put("model", model);
		transParams.put("color", color);
		transParams.put("dateOfIssue", dateOfIssue);
		transParams.put("price", price);
		transParams.put("producer", producer);
		transParams.put("notInStock", notInStock);
		Validator validator = new Validator();
		transParams.put("validator", validator);

		String pathToCatalog = req.getServletContext().getRealPath(
				"WEB-INF/classes/shop.xml");
		File catalogFile = new File(pathToCatalog);
		long lastMod = catalogFile.lastModified();

		Lock readLock = RWLockSingleton.INSTANCE.readLock();
		readLock.lock();
		try {
			XSLManager.makeTransform(styleSheet, catalog, resultWriter,
					transParams);
		} finally {
			readLock.unlock();
		}

		if (validator.getErrors().isEmpty()) {
			Lock writeLock = RWLockSingleton.INSTANCE.writeLock();
			writeLock.lock();
			try {
				if (lastMod != catalogFile.lastModified()) {
					XSLManager.makeTransform(styleSheet, catalog, resultWriter,
							transParams);
				}
				Writer fileWriter = new PrintWriter(catalogFile, "UTF-8");
				fileWriter.write(resultWriter.toString());
				fileWriter.flush();
				fileWriter.close();

			} finally {
				writeLock.unlock();
			}
			String redirect = "Controller?action=showProducts&catName="
					+ catName + "&subcatName=" + subcatName;
			resp.sendRedirect(redirect);

		} else {
			String fwd = "Controller?action=addNewProduct&catName=" + catName
					+ "&subcatName=" + subcatName;

			req.setAttribute("errors", validator.getErrors());
			req.getRequestDispatcher(fwd).forward(req, resp);
		}

	}
}
