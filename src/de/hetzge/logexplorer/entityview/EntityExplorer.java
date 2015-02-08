package de.hetzge.logexplorer.entityview;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
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
							Platform.runLater(() -> {
								treeItem.getChildren().add(subRuleEntityExplorerNode.treeItem);
							});
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
			return rule.toString() + ": " + value;
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
		
		treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			ObservableList<IF_Entity> entities = newValue.getValue().entities;
			for (IF_Entity entity : entities) {
				System.out.println(entity);
			}
		});

		getChildren().add(treeView);
	}

	@Override
	public void accept(IF_Entity entity) {
		rootEntityExplorerNode.accept(entity);

	}

}
