package cn.emagsoftware.utils.json;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IntegerValueProcessor implements JsonValueProcessor {

	public IntegerValueProcessor() {

	}

	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		return process(value, jsonConfig);
	}

	public Object processObjectValue(String key, Object value,
			JsonConfig jsonConfig) {
		return process(value, jsonConfig);
	}

	private Object process(Object value, JsonConfig jsonConfig) {
		if (value instanceof Integer) {
			String str = String.valueOf(value);
			return str;
		}
		return value == null ? "" : String.valueOf(value);
	}
}
