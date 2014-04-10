package com.epam.util;

import java.util.concurrent.ConcurrentHashMap;

import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

class TemplatesCache {

	private TemplatesCache(){
		
	}
	private static ConcurrentHashMap<String, Templates> cache = new ConcurrentHashMap<String, Templates>();
	private static TransformerFactory transformerFactory = TransformerFactory
			.newInstance();

	static Transformer getTransformer(String xslPath)
			throws TransformerConfigurationException {

		Source source = new StreamSource(
				TemplatesCache.class.getResourceAsStream(xslPath));
		cache.putIfAbsent(xslPath, transformerFactory.newTemplates(source));

		return cache.get(xslPath).newTransformer();

	}
}
