package com.epam.util;

import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

public class TemplatesCache {

	private static Map<String, Templates> cache = new HashMap<String, Templates>();
	private static TransformerFactory transformerFactory = TransformerFactory
			.newInstance();

	static Transformer getTransformer(String xslPath) throws TransformerConfigurationException {
		if (cache.containsKey(xslPath))
			return cache.get(xslPath).newTransformer();
		else {
			Source source = new StreamSource(
					TemplatesCache.class.getResourceAsStream(xslPath));
			Templates templates = null;
			try {
				templates = transformerFactory.newTemplates(source);
			} catch (TransformerConfigurationException e) {
				e.printStackTrace();
			}
			 cache.put(xslPath, templates);
			return cache.get(xslPath).newTransformer();
		}

	}
}
