package de.hetzge.logexplorer.model;

import java.util.List;

public interface IF_Rule {

	public String value(IF_Entity entity);

	public boolean check(IF_Entity entity);
	
	public List<IF_Rule> getSubRules();

}
