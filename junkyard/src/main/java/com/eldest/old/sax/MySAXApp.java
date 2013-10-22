package com.eldest.old.sax;

import java.io.FileInputStream;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class MySAXApp extends DefaultHandler {

	public static void main(String args[]) throws Exception {
		MySAXApp handler = new MySAXApp();
		XMLReader xr = XMLReaderFactory.createXMLReader();
		FooFilter filter = new FooFilter(xr);
		filter.setContentHandler(handler);
		filter.setErrorHandler(handler);
		
//		filter.setFeature("http://apache.org/xml/features/allow-java-encodings",true);

		// Parse each file provided on the command line.
		for (int i = 0; i < args.length; i++) {
			FileInputStream inputStream = new FileInputStream(args[i]);
			InputSource source = new InputSource(inputStream);
//			source.setEncoding("Windows-1251");
//			System.out.println("Encoding: " + source.getEncoding());
			filter.parse(source);
		}
	}

	public MySAXApp() {
		super();
	}

	////////////////////////////////////////////////////////////////////
	// Event handlers.
	////////////////////////////////////////////////////////////////////

	@Override
	public void startDocument() {
		System.out.println("Start document");
	}

	@Override
	public void endDocument() {
		System.out.println("End document");
	}

	@Override
	public void startElement(String uri, String name, String qName, Attributes atts) {
		if ("".equals(uri))
			System.out.println("Start element: " + qName);
		else
			System.out.println("Start element: {" + uri + "}" + name);
		//			System.out.println("Attributes: " + atts);
	}

	@Override
	public void endElement(String uri, String name, String qName) {
		if ("".equals(uri))
			System.out.println("End element: " + qName);
		else
			System.out.println("End element:   {" + uri + "}" + name);
	}

	@Override
	public void characters(char ch[], int start, int length) {
		System.out.print("Characters:    \"");
		for (int i = start; i < start + length; i++) {
			switch (ch[i]) {
			case '\\':
				System.out.print("\\\\");
				break;
			case '"':
				System.out.print("\\\"");
				break;
			case '\n':
				System.out.print("\\n");
				break;
			case '\r':
				System.out.print("\\r");
				break;
			case '\t':
				System.out.print("\\t");
				break;
			default:
				System.out.print(String.valueOf(ch[i]));
				break;
			}
		}
		System.out.print("\"\n");
	}

}
