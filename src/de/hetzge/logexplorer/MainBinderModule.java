package de.hetzge.logexplorer;

import se.jbee.inject.bind.BinderModule;
import de.hetzge.logexplorer.entityview.EntityExplorer;
import de.hetzge.logexplorer.entityview.EntityTable;
import de.hetzge.logexplorer.entityview.EntityView;

public class MainBinderModule extends BinderModule{

	@Override
	protected void declare() {
		add(Main.class);
		add(MainStage.class);
		
		add(EntityExplorer.class);
		add(EntityTable.class);
		add(EntityView.class);
	}
	
	private <T> void add(Class<T> clazz){
		bind(clazz).to(clazz);
	}

}
