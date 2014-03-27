package com.epam.controller.Factory;

import java.util.HashMap;
import java.util.Map;

import com.epam.controller.actions.Action;
import com.epam.controller.actions.ShowCategoriesAction;
import com.epam.controller.actions.ShowSubcategoriesAction;

public class ActionFactory {
	public static Map<String, Action> actions = new HashMap<String, Action>();

	static {
		actions.put("showCategories", new ShowCategoriesAction());
		actions.put("showSubcategories", new ShowSubcategoriesAction());
	}

	public static Action getAction(String action) {

		return actions.get(action);
	}

}
