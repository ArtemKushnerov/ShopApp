package com.epam.controller.Factory;

import java.util.HashMap;
import java.util.Map;

import com.epam.controller.actions.Action;
import com.epam.controller.actions.AddNewProductAction;
import com.epam.controller.actions.SaveProductAction;
import com.epam.controller.actions.ShowCategoriesAction;
import com.epam.controller.actions.ShowProductsAction;
import com.epam.controller.actions.ShowSubcategoriesAction;

public class ActionFactory {
	public static Map<String, Action> actions = new HashMap<String, Action>();

	static {
		actions.put("showCategories", new ShowCategoriesAction());
		actions.put("showSubcategories", new ShowSubcategoriesAction());
		actions.put("showProducts", new ShowProductsAction());
		actions.put("addNewProduct", new AddNewProductAction());
		actions.put("saveProduct", new SaveProductAction());
	}

	public static Action getAction(String action) {

		return actions.get(action);
	}

}
