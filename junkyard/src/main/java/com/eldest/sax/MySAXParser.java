package com.eldest.sax;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MySAXParser extends DefaultHandler {
	private static StringBuilder displayText = new StringBuilder();

	static String currentEllement, currentVallue;

	public static void main(String args[]) throws Exception {
		MySAXParser mySAXParser = new MySAXParser();
		mySAXParser.childLoop(new FileInputStream(args[0]));
		writeToFile("new.xml");
	}

	public static void writeToFile(String name) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(name);
			fileOutputStream.write(displayText.toString().getBytes("UTF-8"));
			fileOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}

	public void childLoop(FileInputStream fileInputStream) {
		try {
			XMLReader reader = XMLReaderFactory.createXMLReader();
			
			reader.setFeature("http://xml.org/sax/features/namespaces", true);
			reader.setFeature("http://xml.org/sax/features/namespace-prefixes", true);
			
			reader.setContentHandler(this);
			reader.setEntityResolver(this);
			reader.setErrorHandler(this);
			reader.setDTDHandler(this);

			reader.parse(new InputSource(fileInputStream));
		} catch (Throwable t) {
		}
	}

	@Override
	public void startDocument() {
		displayText.append("<?xml version=\"1.0\" encoding=\"" + "UTF-8\"" + "?>" + "\n");
	}

	@Override
	public void startElement(String uri, String localName, String qualifiedName, Attributes attributes) {
		displayText.append("<" + qualifiedName);
		currentEllement = qualifiedName;
		if (attributes != null) {
			int numberAttributes = attributes.getLength();
			for (int loopIndex = 0; loopIndex < numberAttributes; loopIndex++) {
				displayText.append(' ' + attributes.getQName(loopIndex) + "=\"" + attributes.getValue(loopIndex) + '"');
			}
		}
		displayText.append(">");
	}

	@Override
	public void endElement(String uri, String localName, String qualifiedName) {
		displayText.append("</" + qualifiedName + ">");
	}

	@Override
	public void characters(char characters[], int start, int length) {
		String characterData = (new String(characters, start, length)).trim();
		currentVallue = characterData;
		if (characterData.indexOf("\n") < 0 && characterData.length() > 0) {
			displayText.append(characterData);
		}
	}

	@Override
	public void processingInstruction(String target, String data) {
		displayText.append("<?" + target);
		if (data != null && data.length() > 0) {
			displayText.append(" " + data);
		}
		displayText.append("?>");
	}

	@Override
	public void ignorableWhitespace(char characters[], int start, int length) {
		//characters(characters, start, length);
	}

	@Override
	public void warning(SAXParseException exception) {
		System.err.println("Warning: " + exception.getMessage());
	}

	@Override
	public void error(SAXParseException exception) {
		System.err.println("Error: " + exception.getMessage());
	}

	@Override
	public void fatalError(SAXParseException exception) {
		System.err.println("Fatal error: " + exception.getMessage());
	}
}
