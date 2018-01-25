package view;

import genetics.EvolutionModel;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import network.Connection;
import network.Genome;
import network.Node;

/**
 * The application UI is the base for all UI components, it structures them.
 */
public class ApplicationUI extends StackPane {

  /***************************************************************************
   *                                                                         *
   * Fields                                                                  *
   *                                                                         *
   **************************************************************************/

	//run loop
	private final static int TICK_RATE = 60; //per second
	private final static int TICK_INTERVAL = 1000/TICK_RATE;
	private static long tickStart;
	
	private Button mutateButton;
	
	private Canvas canvas;
	private EvolutionModel model;

  /***************************************************************************
   *                                                                         *
   * Constructor                                                             *
   *                                                                         *
   **************************************************************************/

	public ApplicationUI() {
		initializeSelf();
		layoutViews();
	}
  
	public void update() {
	}

  /***************************************************************************
   *                                                                         *
   * Private methods                                                         *
   *                                                                         *
   **************************************************************************/

	private void initializeSelf() {
		String stylesheet = getClass().getResource("style.css").toExternalForm();
		getStylesheets().add(stylesheet);
		
		canvas = new Canvas(1000, 1000);
		Genome genome = exampleGenome();
		GenomeDrawer drawer = new GenomeDrawer(genome);
		mutateButton = new Button("Mutate");
		mutateButton.setPrefSize(200, 50);
		mutateButton.setOnAction(event -> {
			model.mutate(genome);
			drawer.draw(canvas.getGraphicsContext2D());
			refresh();
		});
		model = new EvolutionModel();
		drawer.draw(canvas.getGraphicsContext2D());
	}
	
	private void refresh() {
		getChildren().clear();
		VBox vbox = new VBox();
		vbox.getChildren().addAll(canvas, mutateButton);
		getChildren().add(vbox);
	}
	  
	private void layoutViews() {
		VBox vbox = new VBox();
		vbox.getChildren().addAll(canvas, mutateButton);
		getChildren().add(vbox);
	}
	  
	private Genome exampleGenome() {
		Genome genome = new Genome();
		Node n1 = new Node(Node.Type.Sensor);
		Node n2 = new Node(Node.Type.Sensor);
		Node n3 = new Node(Node.Type.Sensor);
		Node n4 = new Node(Node.Type.Output);
		Node n5 = new Node(Node.Type.Hidden);
		genome.addNode(n1);
		genome.addNode(n2);
		genome.addNode(n3);
		genome.addNode(n4);
		genome.addNode(n5);
		
		Connection c1 = new Connection(n1, n4, 0.7f, true);	
		Connection c2 = new Connection(n2, n4, -0.5f, false);	
		Connection c3 = new Connection(n3, n4, 0.5f, true);	
		Connection c4 = new Connection(n2, n5, 0.2f, true);	
		Connection c5 = new Connection(n5, n4, 0.4f, true);	
		Connection c6 = new Connection(n1, n5, 0.6f, true);
		genome.addConnection(c1);
		genome.addConnection(c2);
		genome.addConnection(c3);
		genome.addConnection(c4);
		genome.addConnection(c5);
		genome.addConnection(c6);
		
		
		return genome;
	}
}
