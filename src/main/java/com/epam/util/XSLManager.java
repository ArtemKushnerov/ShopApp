package com.epam.util;

import java.io.InputStream;
import java.io.Writer;
import java.util.Map;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XSLManager {

	
	@SafeVarargs
	public static void makeTransform(String stylesheet, InputStream data,
			Writer resultWriter, Map<String, Object>... transParams) {
		Transformer t = null;
		try {
			t = TemplatesCache.getTransformer(stylesheet);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}
		Source text = new StreamSource(data);
		StreamResult streamResult = new StreamResult(resultWriter);
		if (transParams.length != 0) {
			for (String key : transParams[0].keySet()) {
				t.setParameter(key, transParams[0].get(key));
			}
		}
		try {
			t.transform(text, streamResult);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
