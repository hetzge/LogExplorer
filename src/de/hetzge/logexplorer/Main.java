package de.hetzge.logexplorer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se.jbee.inject.Dependency;
import se.jbee.inject.Injector;
import se.jbee.inject.bootstrap.Bootstrap;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Injector injector = Bootstrap.injector(MainBootstrapperBundle.class);
		CreateLogThread createLogThread = injector.resolve(Dependency.dependency(CreateLogThread.class));
		createLogThread.start();

		MainStage mainStage = injector.resolve(Dependency.dependency(MainStage.class));
		stage.setScene(new Scene(mainStage, 600d, 400d));
		stage.show();
	}

}
