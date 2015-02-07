package de.hetzge.logexplorer.model;

import java.util.LinkedList;
import java.util.List;

public class RootRule implements IF_Rule {

	private final List<IF_Rule> subRules = new LinkedList<>();

	@Override
	public String value(IF_Entity entity) {
		return "Root";
	}

	@Override
	public boolean check(IF_Entity entity) {
		return true;
	}

	@Override
	public List<IF_Rule> getSubRules() {
		return subRules;
	}

	public void addSubRule(IF_Rule rule) {
		subRules.add(rule);
	}

}
