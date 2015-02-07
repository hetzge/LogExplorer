package de.hetzge.logexplorer.model;

public interface IF_Entity {

	public String get(String key);
	
	public default boolean contains(String key){
		return get(key) != null;
	}

}
