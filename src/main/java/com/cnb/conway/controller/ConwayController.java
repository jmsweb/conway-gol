package com.cnb.conway.controller;

import com.cnb.conway.impl.ConwayInterface;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


/**
 * Conway Game of Life Controller
 * 
 * The class for controlling the UI and executing set of instructions for simulator
 * 
 * @author Jason Schwebke
 *
 */
public class ConwayController implements ConwayInterface {
	
	@FXML private Button button_randomize;
	@FXML private Button button_start;
	@FXML private Button button_stop;
	@FXML private Slider slider_speed;
	@FXML private Pane gameboard;
	@FXML private TextField text_speed;
	private Thread gameLoop;
	
	/**
	 * The method that first gets called after application boots up.
	 * This set up the default value and layout, and register property listener for components within container.
	 */
    public void initialize() {
		gameboard.setPrefSize(WIDTH, HEIGHT);
    	button_stop.setDisable(true);
    	text_speed.setEditable(false);
    	text_speed.setText("0");
    	
    	VBox vbox = new VBox();
    	for(int r = 0; r < CELLS.length; r++) {
    		HBox hbox = new HBox();
			for(int c = 0; c < CELLS[r].length; c++) {
				SQUARES[r][c] = new Pane();
				SQUARES[r][c].setPrefSize(RATIO, RATIO);
				
				hbox.getChildren().add(SQUARES[r][c]);
    		}
			vbox.getChildren().add(hbox);
    	}
    	gameboard.getChildren().add(vbox);
    	
        slider_speed.valueProperty().addListener((observable, oldValue, newValue) -> {
            text_speed.setText(Integer.toString(newValue.intValue()));
        });
    }
	
	/**
	 * FXML method for Start Population button handler. 
	 * This toggle to enable/disable UI components and then call a function for simulation.
	 */
	@FXML
	protected void startGeneration() {
		button_start.setDisable(true);
		button_randomize.setDisable(true);
		button_stop.setDisable(false);
		cycleOfLife();
	}
	
	/**
	 * FXML method for Randomize button handler. 
	 * This randomizes the cell, as if seeding, with two possible background color.
	 */
	@FXML
	protected void seedOfLife() {
		double randomProbability = 0.0;
		for(int r = 0; r < CELLS.length; r++) {
			for(int c = 0; c < CELLS[r].length; c++) {
				randomProbability = ThreadLocalRandom.current().nextDouble(0, 1);
				//Seed of Life initialize CELLS with probability of life or death
				if(randomProbability < ConwayInterface.PROBABILITY) {
					SQUARES[r][c].setBackground(BKGD_LIFE);
					CELLS[r][c] = ALIVE;
				} else {
					SQUARES[r][c].setBackground(BKGD_DEATH);
					CELLS[r][c] = !ALIVE;
				}
			}
		}
	}
	
	/**
	 * FXML method for Stop Population button handler. 
	 * This toggle to enable/disable UI components and stops the animation thread.
	 */
	@FXML
	protected void stopPopulation() {
		button_randomize.setDisable(false);
		button_start.setDisable(false);
		button_stop.setDisable(true);
		gameLoop.interrupt();
	}
	
	/**
	 * The core processing function for next generation simulation.
	 * This creates a new Task thread to execute a set of instructions
	 * for iterating cells, to check for live neighbors, apply color
	 * to appropriate cell, and wait for millisecond(s) of delay. 
	 */
	private void cycleOfLife() {
		
		Task<Void> task = new Task<Void>(){
			
			@Override
			public Void call() {
				long sleep_time;
				while(!button_stop.isDisabled()) {
					sleep_time = (long) ((slider_speed.getMax() - slider_speed.getValue()) + 1) * 50;
					
					for(int r = 0; r < CELLS.length; r++) {
						for(int c = 0; c < CELLS[r].length; c++) {
							
							int neighbors = getLiveNeighbors(r, c);
							
							if(CELLS[r][c] && neighbors < 2) { 									//Underpopulation
								SQUARES[r][c].setBackground(BKGD_DEATH);
								CELLS[r][c] = !ALIVE;
							} else if (CELLS[r][c] && (neighbors == 2 || neighbors == 3)) {		//Lives on to next generation
								SQUARES[r][c].setBackground(BKGD_LIFE);
								CELLS[r][c] = ALIVE;
							} else if (CELLS[r][c] && neighbors > 3) {							//Overpopulation
								SQUARES[r][c].setBackground(BKGD_DEATH);
								CELLS[r][c] = !ALIVE;
							} else if (!CELLS[r][c] && neighbors == 3) {						//Reproduction
								SQUARES[r][c].setBackground(BKGD_LIFE);
								CELLS[r][c] = ALIVE;
							}
						}
					}
					
					try {
						TimeUnit.MILLISECONDS.sleep(sleep_time);
					} catch(InterruptedException e) {
						
					}
				}
				return null;
			}
		};
		gameLoop = new Thread(task);
		gameLoop.start();
		
	}
	
	/**
	 * This function return total live cells for a selected cell within region
	 * 
	 * @param int row
	 * @param int column
	 * 
	 * @return int neighbors
	 */
	private int getLiveNeighbors(int row, int column) {
	    int neighbors = 0;
	    for (int[] offset : REGION) {
	        if (checkRegion(row + offset[0], column + offset[1])) {
	        	if(CELLS[row + offset[0]][column + offset[1]]) {
	        		neighbors++;
	        	}
	        }
	    }
		
		return neighbors;
	}
	
	/**
	 * This function checks if coordinates are within boundary using 
	 * 
	 * @param row
	 * @param column
	 * @return boolean
	 */
	private boolean checkRegion(int row, int column) {
		return !(row < 1 || row >= WIDTH/RATIO || column < 1 || column >= HEIGHT/RATIO);
	}
}
