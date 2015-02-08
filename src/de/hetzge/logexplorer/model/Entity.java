package de.hetzge.logexplorer.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Entity implements IF_Entity {

	private final Map<String, String> valuesByField = new HashMap<>();

	@Override
	public String get(String key) {
		return valuesByField.get(key);
	}

	public void set(String key, String value) {
		valuesByField.put(key, value);
	}

	@Override
	public String toString() {
		String result = "";
		for (Entry<String, String> entry : valuesByField.entrySet()) {
			result += entry.getKey() + ":" + entry.getValue() + " | ";
		}
		return result;
	}

}
