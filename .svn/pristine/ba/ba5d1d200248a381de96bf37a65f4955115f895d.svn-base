
package cn.emagsoftware.common;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonSerializer {
	
	public static String serializer(Object obj)
	{
		ObjectMapper objectMapper = new  ObjectMapper(); 
		String resultStr = "";
		try {
			resultStr = objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} 
		return resultStr;
	}
		
	public static Object deserializer(String jsonStr,TypeReference<?> typeReference)
	{
		ObjectMapper objectMapper = new  ObjectMapper(); 
		Object resultPerson = null;
		try {
			 objectMapper.configure(Feature.ALLOW_COMMENTS, true);
	        objectMapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
//	        objectMapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
//	        objectMapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
//	        objectMapper.configure(Feature.INTERN_FIELD_NAMES, true);
//	        objectMapper.configure(Feature.CANONICALIZE_FIELD_NAMES, true);
//	        objectMapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			resultPerson = objectMapper.readValue(jsonStr, typeReference);
		} catch (JsonParseException e) {
			e.printStackTrace();
			
		} catch (JsonMappingException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		return resultPerson;
	}
}

