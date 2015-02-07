package de.hetzge.logexplorer.entityview;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import de.hetzge.logexplorer.model.Entity;
import de.hetzge.logexplorer.model.IF_Entity;
import de.hetzge.logexplorer.model.IF_Rule;
import de.hetzge.logexplorer.model.RootRule;
import de.hetzge.logexplorer.model.Rule;

public class EntityExplorer extends VBox implements Consumer<IF_Entity> {

	private class EntityExplorerTreeItem extends TreeItem<EntityExplorerNode> {

		public EntityExplorerTreeItem(EntityExplorerNode entityExplorerNode) {
			super(entityExplorerNode);
			setOnMouseClicked(this::onMouseClicked);
		}

		private void onMouseClicked(MouseEvent mouseEvent) {
			System.out.println("clicked");
		}

	}

	private class EntityExplorerNode implements Consumer<IF_Entity> {

		private final EntityExplorerTreeItem treeItem;
		private final IF_Rule rule;
		private final String value;
		private final Map<String, EntityExplorerNode> childsByValue = new HashMap<>();
		private final ObservableList<IF_Entity> entities = FXCollections.observableList(new LinkedList<IF_Entity>());

		public EntityExplorerNode(IF_Rule rule, String value) {
			this.treeItem = new EntityExplorerTreeItem(this);
			this.rule = rule;
			this.value = value;
		}

		@Override
		public void accept(IF_Entity entity) {
			if (rule.check(entity)) {
				List<IF_Rule> subRules = rule.getSubRules();
				for (IF_Rule subRule : subRules) {
					entities.add(entity);
					String subRuleValue = subRule.value(entity);
					if (hasChildByValue(subRuleValue)) {
						EntityExplorerNode childByValue = childByValue(subRuleValue);
						childByValue.accept(entity);
					} else {
						if (subRule.check(entity)) {
							EntityExplorerNode subRuleEntityExplorerNode = new EntityExplorerNode(subRule, subRuleValue);
							treeItem.getChildren().add(subRuleEntityExplorerNode.treeItem);
							childsByValue.put(subRuleValue, subRuleEntityExplorerNode);
							subRuleEntityExplorerNode.accept(entity);
						}
					}
				}
			}
		}

		public boolean hasChildByValue(String value) {
			return childsByValue.containsKey(value);
		}

		public EntityExplorerNode childByValue(String value) {
			return childsByValue.get(value);
		}
		
		@Override
		public String toString() {
			return value;
		}

	}

	private final EntityExplorerNode rootEntityExplorerNode;

	public EntityExplorer() {
		RootRule rootRule = new RootRule();

		Rule rule = new Rule("Entity");
		rootRule.addSubRule(rule);
		rule.addSubRule(new Rule("Id"));

		rootEntityExplorerNode = new EntityExplorerNode(rootRule, "Root");

		TreeView<EntityExplorerNode> treeView = new TreeView<EntityExplorerNode>();
		treeView.setRoot(rootEntityExplorerNode.treeItem);

		getChildren().add(treeView);

		Entity entity = new Entity();
		entity.set("Entity", "Tree");
		entity.set("Id", "10");
		accept(entity);

		Entity entity2 = new Entity();
		entity2.set("Entity", "Building");
		entity2.set("Id", "10");
		accept(entity2);

		Entity entity3 = new Entity();
		entity3.set("Entity", "Building");
		entity3.set("Id", "11");
		accept(entity3);
	}

	@Override
	public void accept(IF_Entity entity) {
		rootEntityExplorerNode.accept(entity);
	}

}
