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
import com.epam.util.StringHolder;
import com.epam.util.XSLManager;


public final class AddNewProductAction implements Action {

	
	private static final String ADD_PRODUCT_XSL = "/addProductForm.xsl";
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		PrintWriter resultWriter = resp.getWriter();
		InputStream shop = AddNewProductAction.class
				.getResourceAsStream(StringHolder.SHOP_XML);
		String catName = req.getParameter(StringHolder.CAT_NAME);
		String subcatName = req.getParameter(StringHolder.SUBCAT_NAME);
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put(StringHolder.CAT_NAME, catName);
		paramsMap.put(StringHolder.SUBCAT_NAME, subcatName);
		Lock readLock = RWLockSingleton.INSTANCE.readLock();
		readLock.lock();
		try {
			XSLManager.makeTransform(ADD_PRODUCT_XSL, shop, resultWriter,
					paramsMap);
		} finally {
			readLock.unlock();
		}
	}

}
