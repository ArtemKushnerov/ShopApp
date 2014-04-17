package com.epam.presentation.action;

import static com.epam.util.StringHolder.CAT_NAME;
import static com.epam.util.StringHolder.SHOP_REAL_PATH;
import static com.epam.util.StringHolder.SUBCAT_NAME;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import com.epam.controller.actions.SaveProductAction;
import com.epam.presentation.form.ProductForm;
import com.epam.util.RWLockSingleton;
import com.epam.util.StringHolder;
import com.epam.util.Validator;
import com.epam.util.XSLManager;

public final class ShopAction extends DispatchAction {

	private static final String CATLIST = "catlist";
	private static final String SUBCATLIST = "subcatlist";
	private static final String PRODUCTS = "productlist";
	private static final String ADD_PRODUCT_XSL = "/addProductForm.xsl";
	private static final String SAVE_PRODUCT_XSL = "/saveProduct.xsl";


	public ActionForward catlist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws JDOMException, IOException {
		setDocument2Form(form);
		return mapping.findForward(CATLIST);
	}

	public ActionForward subcatlist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws JDOMException, IOException {
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
		ProductForm ProductForm = (ProductForm) form;
		Document document = ProductForm.getDoc();
		File catalogFile = new File(SHOP_REAL_PATH);
		Lock writeLock = RWLockSingleton.INSTANCE.writeLock();
		writeLock.lock();
		try {
				Writer fileWrite = new FileWriter(catalogFile);
				new XMLOutputter().output(document, fileWrite);
				long lastModNew = catalogFile.lastModified();
				ProductForm.setLastMod(lastModNew);
		} finally {
			writeLock.unlock();
		}
		return mapping.findForward(PRODUCTS);
	}
	
	

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductForm productForm = (ProductForm) form;
		int catIndex = productForm.getCatIndex();
		int subcatIndex = productForm.getSubcatIndex();

		Document doc = productForm.getDoc();
		// get category and subcategory names by indexes
		String catName = indexToCatName(doc, catIndex);
		String subcatName = indexToSubcatname(doc, catIndex, subcatIndex);

		PrintWriter resultWriter = response.getWriter();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put(CAT_NAME, catName);
		paramsMap.put(SUBCAT_NAME, subcatName);
		InputStream shop = SaveProductAction.class
				.getResourceAsStream(StringHolder.SHOP_XML);

		Lock readLock = RWLockSingleton.INSTANCE.readLock();
		readLock.lock();
		try {
			XSLManager.makeTransform(ADD_PRODUCT_XSL, shop, resultWriter, paramsMap);
		} finally {
			readLock.unlock();
		}
		return null;
	}
	
	public ActionForward saveProduct(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) throws IOException, JDOMException {
		String catName = req.getParameter("catName");
		String subcatName = req.getParameter("subcatName");

		InputStream shop = SaveProductAction.class
				.getResourceAsStream(StringHolder.SHOP_XML);

		Writer buffer = new StringWriter();

		Map<String, Object> paramsMap = new HashMap<String, Object>();
		fillParamsMap(paramsMap, req);
		Validator validator = new Validator();
		paramsMap.put("validator", validator);
		
		File shopFile = new File(StringHolder.SHOP_REAL_PATH);
		long lastMod = shopFile.lastModified();

		Lock readLock = RWLockSingleton.INSTANCE.readLock();
		readLock.lock();
		try {
			XSLManager.makeTransform(SAVE_PRODUCT_XSL, shop, buffer, paramsMap);
		} finally {
			readLock.unlock();
		}

		if (validator.getErrors().isEmpty()) {
			Lock writeLock = RWLockSingleton.INSTANCE.writeLock();
			writeLock.lock();
			try {
				if (lastMod != shopFile.lastModified()) {
					XSLManager.makeTransform(SAVE_PRODUCT_XSL, shop, buffer,
							paramsMap);
				}
				Writer fileWriter = new PrintWriter(shopFile, "UTF-8");
				fileWriter.write(buffer.toString());
				fileWriter.flush();
				fileWriter.close();

			} finally {
				writeLock.unlock();
			}
			setDocument2Form(form);
			return mapping.findForward(PRODUCTS);

		} else {
			Writer out = resp.getWriter();
			out.write(buffer.toString());
		}
		return null;
	}
	
	private String indexToCatName(Document document, int catIndex) {
		Element rootElement = document.getRootElement();
		Attribute catNameAttr = rootElement.getChildren().get(catIndex)
				.getAttribute("name");
		return catNameAttr.getValue();
	}

	private String indexToSubcatname(Document document, int catIndex,
			int subcatIndex) {
		Element rootElement = document.getRootElement();
		Attribute subcatNameAtr = rootElement.getChildren().get(catIndex)
				.getChildren().get(subcatIndex).getAttribute("name");
		return subcatNameAtr.getValue();
	}


	private void setDocument2Form(ActionForm form) throws JDOMException,
			IOException {
		SAXBuilder saxBuilder = new SAXBuilder();
		File catalogFile = new File(SHOP_REAL_PATH);
		Lock readLock = RWLockSingleton.INSTANCE.readLock();
		Document document = null;
		long lastMod;
		readLock.lock();
		try {
			lastMod = catalogFile.lastModified();
			document = saxBuilder.build(catalogFile);
		} finally {
			readLock.unlock();
		}
		ProductForm productsForm = (ProductForm) form;
		Element el = null;
		productsForm.setDoc(document);
		productsForm.setLastMod(lastMod);
	}
	
	private void fillParamsMap(Map<String, Object> paramsMap,
			HttpServletRequest req) {
		String catName = req.getParameter("catName");
		String subcatName = req.getParameter("subcatName");

		String model = req.getParameter("model");
		String color = req.getParameter("color");
		String dateOfIssue = req.getParameter("dateOfIssue");
		String price = req.getParameter("price") == null ? "" : req
				.getParameter("price");
		String producer = req.getParameter("producer");
		boolean notInStock = "true".equals(req.getParameter("notInStock"));

		paramsMap.put("catName", catName);
		paramsMap.put("subcatName", subcatName);
		paramsMap.put("model", model);
		paramsMap.put("color", color);
		paramsMap.put("dateOfIssue", dateOfIssue);
		paramsMap.put("price", price);
		paramsMap.put("producer", producer);
		paramsMap.put("notInStock", notInStock);
	}

}
