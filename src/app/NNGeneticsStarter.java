package app;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.ApplicationUI;

public class NNGeneticsStarter extends Application{
	
	//default is 1920x1080
	public static final int SCALE = 1;
	public static final int WIDTH = 1920 / SCALE;
	public static final int HEIGHT = 1080 / SCALE;
	
	//run loop
	private final static int TICK_RATE = 300; //per second
	private final static int TICK_INTERVAL = 1000/TICK_RATE;
	private static long tickStart;
	
	private static ApplicationUI rootPanel;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		rootPanel = new ApplicationUI();

		Scene scene = new Scene(rootPanel);

		primaryStage.titleProperty().setValue("Map Generator");
		primaryStage.setScene(scene);
		if(SCALE == 1) primaryStage.setFullScreen(true);
		primaryStage.setHeight(HEIGHT);
		primaryStage.setWidth(WIDTH);

		primaryStage.show();
		
	    new AnimationTimer()
	    {
	        public void handle(long currentNanoTime)
	        {
				if(System.currentTimeMillis() - tickStart > TICK_INTERVAL) {
					tickStart = System.currentTimeMillis();
					rootPanel.update();
				}
	        }
	    }.start();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
