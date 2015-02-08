package de.hetzge.logexplorer;

import de.hetzge.logexplorer.model.Entity;

public class CreateLogThread extends Thread {

	private static final String[] ENTITY_VALUES = { "ONE", "TWO", "THREE" };

	private final LogIndexerService logIndexerService;

	public CreateLogThread(LogIndexerService logIndexerService) {
		this.logIndexerService = logIndexerService;
	}

	@Override
	public void run() {
		
		while (true) {
			Entity entity = new Entity();
			entity.set("Entity", ENTITY_VALUES[(int) Math.floor(Math.random() * ENTITY_VALUES.length)]);
			entity.set("Id", "" + (int) (Math.random() * 10));
			
			logIndexerService.index(entity);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
