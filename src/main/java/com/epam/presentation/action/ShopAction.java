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
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import com.epam.presentation.form.ProductForm;

public final class ShopAction extends DispatchAction {

	private static final String CATLIST = "catlist";
	private static final String SUBCATLIST = "subcatlist";
	private static final String PRODUCTS = "productlist";

	public ActionForward catlist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws JDOMException, IOException {
		setDocument2Form(form);
		return mapping.findForward(CATLIST);
	}

	public ActionForward subcatlist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws JDOMException, IOException {
		setDocument2Form(form);
		return mapping.findForward(SUBCATLIST);
	}
	
	public ActionForward productlist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setDocument2Form(form);
		return mapping.findForward(PRODUCTS);
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward(PRODUCTS);
	}

	
	private void setDocument2Form(ActionForm form) throws JDOMException, IOException{
		SAXBuilder builder = new SAXBuilder();
		File shopXml = new File(SHOP_REAL_PATH);
		Document doc = (Document) builder.build(shopXml);
		ProductForm productForm  = (ProductForm) form;
		productForm.setDoc(doc);
	}
}
