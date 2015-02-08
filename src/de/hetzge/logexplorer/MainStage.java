package de.hetzge.logexplorer;

import javafx.scene.layout.BorderPane;
import de.hetzge.logexplorer.entityview.EntityView;
import de.hetzge.logexplorer.model.Entity;

public class MainStage extends BorderPane {

	public MainStage(EntityView entityView) {
		setCenter(entityView);
	}
	
}
