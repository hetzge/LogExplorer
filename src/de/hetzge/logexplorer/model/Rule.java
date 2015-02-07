package de.hetzge.logexplorer.model;

import java.util.LinkedList;
import java.util.List;

public class Rule implements IF_Rule {

	private Rule parent;
	private final String field;
	private final List<IF_Rule> subRules = new LinkedList<>();

	public Rule(String field) {
		this.field = field;
	}

	public void addSubRule(Rule rule) {
		rule.parent = this;
		subRules.add(rule);
	}

	public boolean hasParent() {
		return parent != null;
	}

	public boolean check(IF_Entity entity) {
		return entity.contains(field);
	}

	public String value(IF_Entity entity) {
		if (!check(entity)){
			throw new IllegalAccessError();
		}
		return entity.get(field);
	}

	public List<IF_Rule> getSubRules() {
		return subRules;
	}

}
