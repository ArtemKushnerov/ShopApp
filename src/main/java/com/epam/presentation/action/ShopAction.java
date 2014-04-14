package com.epam.presentation.action;

import static com.epam.util.StringHolder.SHOP_REAL_PATH;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.tiles.taglib.GetAttributeTag;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.epam.presentation.form.ProductForm;

public class ShopAction extends DispatchAction {

	public ActionForward catList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		SAXBuilder builder = new SAXBuilder();
		File shopXml = new File(SHOP_REAL_PATH);
		Document doc = null;
		try {
			doc = (Document) builder.build(shopXml);
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ProductForm productForm = (ProductForm) form;
		productForm.setDoc(doc);
		return mapping.findForward("successCatList");
	}

	public ActionForward subcatList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		SAXBuilder builder = new SAXBuilder();
		File shopXml = new File(SHOP_REAL_PATH);
		Document doc = null;
		try {
			doc = (Document) builder.build(shopXml);
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ProductForm productForm = (ProductForm) form;
		productForm.setDoc(doc);
		return mapping.findForward("successCatList");
	}
}
