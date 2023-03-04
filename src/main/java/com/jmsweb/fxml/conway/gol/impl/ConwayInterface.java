package com.jmsweb.fxml.conway.gol.impl;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public interface ConwayInterface {
    public final double WIDTH = 500;
    public final double HEIGHT = 500;
    public final int RATIO = 10;
    public final double PROBABILITY = .1;
    public final boolean ALIVE = true;
    public final Paint LIFE = Color.BLACK;
    public final Paint DEATH = Color.WHITE;
    public final Background BKGD_LIFE = new Background(new BackgroundFill(LIFE, CornerRadii.EMPTY, Insets.EMPTY));
    public final Background BKGD_DEATH = new Background(new BackgroundFill(DEATH, CornerRadii.EMPTY, Insets.EMPTY));
    public final boolean[][] CELLS = new boolean[(int)(WIDTH/RATIO)][(int)(HEIGHT/RATIO)];
    public final Pane[][] SQUARES = new Pane[(int)HEIGHT/RATIO][(int)WIDTH/RATIO];

    //Note no 9th square
    public final int[][] REGION = {
      {-1,-1},{0,-1},{+1,-1},
      {-1,0},        {+1,0},
      {-1,+1},{0,+1},{+1,+1}
    };
}