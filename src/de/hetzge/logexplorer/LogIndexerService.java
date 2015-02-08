package de.hetzge.logexplorer;

import de.hetzge.logexplorer.entityview.EntityExplorer;
import de.hetzge.logexplorer.model.IF_Entity;

public class LogIndexerService {

	private final EntityExplorer entityExplorer;
	
	public LogIndexerService(EntityExplorer entityExplorer) {
		this.entityExplorer = entityExplorer;
	}

	public void index(IF_Entity entity){
		entityExplorer.accept(entity);
	}
	
}
