package com.epam.util;

import java.io.InputStream;
import java.io.Writer;
import java.util.Map;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class HTMLWriter {

	public static void write(InputStream stylesheet, InputStream data,
			Writer resultWriter, Map<String, String>... paramsMap) {
		StreamSource styleSource = new StreamSource(stylesheet);
		Transformer t = null;
		try {
			t = TransformerFactory.newInstance().newTransformer(styleSource);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}
		Source text = new StreamSource(data);
		StreamResult streamResult = new StreamResult(resultWriter);
		if (paramsMap.length != 0) {
			for (String key : paramsMap[0].keySet()) {
				t.setParameter(key, paramsMap[0].get(key));
			}
		}
		try {
			t.transform(text, streamResult);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void save(InputStream stylesheet, InputStream data,
			Writer resultWriter, Map<String, Object> paramsMap,
			Map<String, String> errors) {
		StreamSource styleSource = new StreamSource(stylesheet);
		Transformer t = null;
		try {
			t = TransformerFactory.newInstance().newTransformer(styleSource);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}
		Source text = new StreamSource(data);
		StreamResult streamResult = new StreamResult(resultWriter);

		for (String key : paramsMap.keySet()) {
			t.setParameter(key, paramsMap.get(key));
		}

		t.setParameter("validator", new Validator());
		t.setParameter("errors",  errors);
		try {
			t.transform(text, streamResult);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
