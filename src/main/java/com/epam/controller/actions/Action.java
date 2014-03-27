package com.epam.controller.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	
	void execute(HttpServletRequest req, HttpServletResponse resp);
			
}
