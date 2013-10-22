package com.eldest.old.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLFilterImpl;

public class FooFilter extends XMLFilterImpl {
	public FooFilter() {
	}

	public FooFilter(XMLReader parent) {
		super(parent);
	}

	/**
	 * Filter the Namespace URI for start-element events.
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		if (uri.equals("http://schemas.openxmlformats.org/package/2006/metadata/core-properties")) {
			uri = "http://www.myownsheme.com/core-properties/";
		}
		super.startElement(uri, localName, qName, atts);
	}

	/**
	 * Filter the Namespace URI for end-element events.
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (uri.equals("http://schemas.openxmlformats.org/package/2006/metadata/core-properties")) {
			uri = "http://www.myownsheme.com/core-properties/";
		}
		super.endElement(uri, localName, qName);
	}

}
