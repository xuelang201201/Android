package tarena.day2001;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class EmailHandler extends DefaultHandler {
	
	private StringBuilder text = new StringBuilder();
	/*
	 * startElement()
	 * endElement()
	 * characters()
	 */
	
	@Override
	public void startElement(
			String uri,
			String localName,
			String qName,//��ǩ��
			Attributes attributes) throws SAXException {
		System.out.println("��ǩ��ʼ��"+qName);
	}
	
	@Override
	public void endElement(
			String uri, 
			String localName, 
			String qName) throws SAXException {
		String s = text.toString().trim();
		if(s.length() != 0) {
			System.out.println("�ı���");
			System.out.println(s);
		}
		text.delete(0, text.length());
		
		System.out.println("��ǩ������"+qName);
	}
	
	@Override
	public void characters(
			char[] ch,
			int start,
			int length) throws SAXException {
		
		text.append(ch, start, length);
		
	}
}
