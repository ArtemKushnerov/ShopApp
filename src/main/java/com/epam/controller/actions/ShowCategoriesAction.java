package com.epam.controller.actions;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.concurrent.locks.Lock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.util.RWLockSingleton;
import com.epam.util.XSLManager;

public class ShowCategoriesAction implements Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter resultWriter = resp.getWriter();

		InputStream shop = ShowCategoriesAction.class
				.getResourceAsStream("/shop.xml");
		
		
		Lock readLock = RWLockSingleton.INSTANCE.readLock();
		readLock.lock();
		try {
			XSLManager.makeTransform("/showCategories.xsl", shop, resultWriter);
		} finally {
			readLock.unlock();

		}

	}

}
