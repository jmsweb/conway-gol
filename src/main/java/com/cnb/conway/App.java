package com.cnb.conway;

import com.cnb.conway.impl.ConwayInterface;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.application.Platform;

/**
 * Conway Game of Life Application
 * 
 * The class for starting up the application and inject FXML and CSS files.
 * 
 * @author Jason Schwebke
 *
 */
public class App extends Application implements ConwayInterface {
	
    public static void main( String[] args ) {
    	launch(args);
    }
    
	@Override
	public void start(Stage stage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("conway_gol.fxml"));
			Pane root = (Pane) loader.load();

			Scene scene = new Scene(root, WIDTH, HEIGHT + 100);
			
			scene.getStylesheets().add(getClass().getResource("conway_gol.css").toExternalForm());
			
			stage.setTitle("Conway Game of Life Java/JavaFX 2.0");
			stage.setScene(scene);
			stage.setResizable(true);
			stage.show();

			stage.setOnCloseRequest((new EventHandler<WindowEvent>() {
				
				@Override
				public void handle(WindowEvent event) {
					Platform.exit(); //not enough to exit.
					System.exit(0);  //confidently exit.
				}
			}));
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}