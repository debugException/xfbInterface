package cn.emagsoftware.common;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XmlSerializer {
	public static String serializer(Object obj,Class<?> objclass)
	{
		String xml = "";
		JAXBContext context;  
		try {  
			context = JAXBContext.newInstance(objclass);  
			Marshaller mar = context.createMarshaller();  
			mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
			mar.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");  
			  
			StringWriter writer = new StringWriter();  
			mar.marshal(obj, writer);  
			xml = writer.toString();
			writer.close();
		} 
		catch (JAXBException e)
		{
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xml;
	}
		
	public static Object deserializer(String xml,Class<?> objclass)
	{
		Object result = null;
		JAXBContext context;  
		try {  
			context = JAXBContext.newInstance(objclass);  
			Unmarshaller unmar = context.createUnmarshaller();
			StringReader sr = new StringReader(xml);
			result = unmar.unmarshal(sr);
			sr.close();
		}
		catch (JAXBException e)
		{
			e.printStackTrace();
		}
		return result;
	}
}

