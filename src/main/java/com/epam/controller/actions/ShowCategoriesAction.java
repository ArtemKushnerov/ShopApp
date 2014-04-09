package com.epam.controller.actions;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.concurrent.locks.Lock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.util.RWLockSingleton;
import com.epam.util.StringHolder;
import com.epam.util.XSLManager;

public class ShowCategoriesAction implements Action {

	private static final String SHOW_CATEGORIES_XSL = "/showCategories.xsl";

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter resultWriter = resp.getWriter();

		InputStream shop = ShowCategoriesAction.class
				.getResourceAsStream(StringHolder.SHOP_XML);

		Lock readLock = RWLockSingleton.INSTANCE.readLock();
		readLock.lock();
		try {
			XSLManager.makeTransform(SHOW_CATEGORIES_XSL, shop, resultWriter);
		} finally {
			readLock.unlock();
		}

	}

}
