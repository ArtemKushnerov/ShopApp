package com.epam.controller.actions;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.util.RWLockSingleton;
import com.epam.util.XSLManager;

public class ShowProductsAction implements Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter resultWriter = resp.getWriter();

		InputStream shop = ShowSubcategoriesAction.class
				.getResourceAsStream("/shop.xml");
		String catName = req.getParameter("catName");
		String subcatName = req.getParameter("subcatName");
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("catName", catName);
		paramsMap.put("subcatName", subcatName);
		Lock readLock = RWLockSingleton.INSTANCE.readLock();
		readLock.lock();
		try {
			XSLManager.makeTransform("/showProducts.xsl", shop, resultWriter, paramsMap);
		} finally {
			readLock.unlock();
		}

		
	}

}
