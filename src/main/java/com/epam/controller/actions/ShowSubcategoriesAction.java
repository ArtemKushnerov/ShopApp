package com.epam.controller.actions;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class ShowSubcategoriesAction implements Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		Writer writer;
		try {
			writer = resp.getWriter();
			resp.setContentType("text/html; charset=UTF-8");
			TransformerFactory factory = TransformerFactory.newInstance();
			InputStream xslStream = ShowCategoriesAction.class
					.getResourceAsStream("/showSubcategories.xsl");
			InputStream xmlStream = ShowCategoriesAction.class
					.getResourceAsStream("/shop.xml");
			Source xslt = new StreamSource(xslStream);
			Transformer transformer = factory.newTransformer(xslt);
			Source text = new StreamSource(xmlStream);
			transformer.transform(text, new StreamResult(writer));
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
