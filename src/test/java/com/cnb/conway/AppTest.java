package com.cnb.conway;

import com.cnb.conway.impl.ConwayInterface;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.testfx.api.FxAssert.verifyThat;

/**
 * Unit test for simple App.
 */
public class AppTest extends ApplicationTest implements ConwayInterface {

	App app;
	
	@Override
	public void start(Stage stage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("conway_gol.fxml"));
			Pane root = (Pane) loader.load();
			Scene scene = new Scene(root, WIDTH, HEIGHT + 100);
			
			scene.getStylesheets().add(getClass().getResource("conway_gol.css").toExternalForm());
			
			stage.setTitle("Conway Game of Life Java/JavaFX 2.0 TEST");
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();

			stage.setOnCloseRequest((new EventHandler<WindowEvent>() {
				
				@Override
				public void handle(WindowEvent event) {
					Platform.exit(); //not enough to exit.
					System.exit(0);  //confidently exit.
				}
			}));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void checkStoppedState() {
		
		Button button;
		Slider slider;
		TextField text;
		Pane gameboard;
		
		clickOn("#button_stop");

		verifyThat("#button_randomize", hasText("Randomize"));
		button = lookup("#button_randomize").query();
		assertFalse(button.disabledProperty().get());
		
		verifyThat("#button_start", hasText("Start Generation"));
		button = lookup("#button_start").query();
		assertFalse(button.disabledProperty().get());
		
		verifyThat("#button_stop", hasText("Stop Population"));
		button = lookup("#button_stop").query();
		assertTrue(button.disabledProperty().get());
		
		slider = lookup("#slider_speed").query();
		assertFalse(slider.disabledProperty().get());
		
		text = lookup("#text_speed").query();
		assertTrue(Integer.parseInt(text.getText()) == 0);
		assertFalse(text.editableProperty().get());
		
		gameboard = lookup("#gameboard").query();
		assertTrue(gameboard.isVisible());
	}
	
	@Test
	public void checkGenerationState() throws Exception {
		Button button;
		Slider slider;
		TextField text;
		Pane gameboard;
		
		clickOn("#button_randomize");
		clickOn("#button_start");

		verifyThat("#button_randomize", hasText("Randomize"));
		button = lookup("#button_randomize").query();
		assertTrue(button.disabledProperty().get());
		
		verifyThat("#button_start", hasText("Start Generation"));
		button = lookup("#button_start").query();
		assertTrue(button.disabledProperty().get());
		
		verifyThat("#button_stop", hasText("Stop Population"));
		button = lookup("#button_stop").query();
		assertFalse(button.disabledProperty().get());
		
		slider = lookup("#slider_speed").query();
		assertFalse(slider.disabledProperty().get());
		
		text = lookup("#text_speed").query();
		assertTrue(Integer.parseInt(text.getText()) == 0);
		assertFalse(text.editableProperty().get());
		
		gameboard = lookup("#gameboard").query();
		assertTrue(gameboard.isVisible());
		
		TimeUnit.SECONDS.sleep(5);
	}
}
