package de.hetzge.logexplorer.entityview;

import javafx.scene.layout.BorderPane;

public class EntityView extends BorderPane {

	public EntityView(EntityExplorer entityExplorer, EntityTable entityTable) {
		setLeft(entityExplorer);
		setCenter(entityTable);
	}
	
}
