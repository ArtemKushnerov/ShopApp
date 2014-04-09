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

public class ShowProductsAction implements Action {

	private static final String SHOW_PRODUCT_XSL = "/showProducts.xsl";

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter resultWriter = resp.getWriter();

		InputStream shop = ShowSubcategoriesAction.class
				.getResourceAsStream(StringHolder.SHOP_XML);

		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put(StringHolder.CAT_NAME,
				req.getParameter(StringHolder.CAT_NAME));
		paramsMap.put(StringHolder.SUBCAT_NAME,
				req.getParameter(StringHolder.SUBCAT_NAME));
		Lock readLock = RWLockSingleton.INSTANCE.readLock();
		readLock.lock();
		try {
			XSLManager.makeTransform(SHOW_PRODUCT_XSL, shop, resultWriter,
					paramsMap);
		} finally {
			readLock.unlock();
		}

	}

}
