package de.hetzge.logexplorer.model;

import java.util.HashMap;
import java.util.Map;

public class Entity implements IF_Entity {

	private final Map<String, String> valuesByField = new HashMap<>();

	@Override
	public String get(String key) {
		return valuesByField.get(key);
	}
	
	public void set(String key, String value){
		valuesByField.put(key, value);
	}
	
}
