package de.hetzge.logexplorer;

import se.jbee.inject.Dependency;
import se.jbee.inject.bootstrap.Bootstrap;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		MainStage mainStage = Bootstrap.injector(MainBootstrapperBundle.class).resolve(Dependency.dependency(MainStage.class));
		stage.setScene(new Scene(mainStage, 600d, 400d));
		stage.show();
	}
	
}
