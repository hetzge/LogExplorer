package de.hetzge.logexplorer;

import se.jbee.inject.bootstrap.BootstrapperBundle;

public class MainBootstrapperBundle extends BootstrapperBundle  {

	@Override
	protected void bootstrap() {
		install(MainBinderModule.class);
	}

}
