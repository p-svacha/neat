package view;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import network.Connection;
import network.Genome;
import network.Node;

public class GenomeDrawer {
	
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;
	
	public static final int NODESIZE = 20;
	
	private Genome genome;
	
	public GenomeDrawer(Genome genome) {
		this.genome = genome;
	}
	
	public void draw(GraphicsContext context) {
		
		context.clearRect(0, 0, WIDTH, HEIGHT);
		
		// Input layer
		List<Node> inputNodes = genome.getInputNodes();
		List<NodeDrawer> nodeDrawer = new ArrayList<NodeDrawer>();
		int inputGap = (WIDTH - inputNodes.size()*NODESIZE) / (inputNodes.size()+1);
		for(int i = 0; i < inputNodes.size(); i++) {
			nodeDrawer.add(new NodeDrawer(inputNodes.get(i), i*NODESIZE+(i+1)*inputGap+NODESIZE/2, HEIGHT-NODESIZE+NODESIZE/2, NODESIZE, Color.GREY));
		}
		
		System.out.println("NodeDrawer contains " + nodeDrawer.size() + " nodes");
		// Output layer
		List<Node> outputNodes = genome.getOutputNodes();
		int outputGap = (WIDTH - outputNodes.size()*NODESIZE) / (outputNodes.size()+1);
		for(int i = 0; i < outputNodes.size(); i++) {
			nodeDrawer.add(new NodeDrawer(outputNodes.get(i), i*NODESIZE+(i+1)*outputGap+NODESIZE/2, NODESIZE/2, NODESIZE, Color.BLACK));
		}
		
		System.out.println("NodeDrawer contains " + nodeDrawer.size() + " nodes");
		// Hidden layer
		List<Node> hiddenNodes = genome.getHiddenNodes();
		
		int maxDepth = 0;
		for(Node n : hiddenNodes) {
			if(n.getDepth() > maxDepth) maxDepth = n.getDepth();
		}
		maxDepth++;
		System.out.println("Max depth = " + maxDepth);
		List<ArrayList<Node>> layerNodes = new ArrayList<ArrayList<Node>>();
		for(int i = 0; i < maxDepth; i++) {
			layerNodes.add(new ArrayList<Node>());
			for(Node n : hiddenNodes) {
				if(n.getDepth() == i) layerNodes.get(i).add(n);
			}
		}
		
		int hiddenStartY = NODESIZE;
		int hiddenHeight = HEIGHT - 2*NODESIZE;
		int inputYGap = (hiddenHeight - (maxDepth)*NODESIZE) / (maxDepth+1);
		
		for(int i = 0; i < maxDepth; i++) {
			int hiddenY = hiddenStartY + (i*NODESIZE+(i+1)*inputYGap+NODESIZE/2);
			int hiddenXGap = (WIDTH - layerNodes.get(i).size()*NODESIZE) / (layerNodes.get(i).size()+1);
			for(int j = 0; j < layerNodes.get(i).size(); j++) {
				nodeDrawer.add(new NodeDrawer(layerNodes.get(i).get(j), j*NODESIZE+(j+1)*hiddenXGap+NODESIZE/2, hiddenY, NODESIZE, Color.BLUE));
			}
		}
		
		// Connections
		System.out.println("NodeDrawer contains " + nodeDrawer.size() + " nodes");
		List<Connection> connections = genome.getConnections();
		for(Connection c : connections) {
			
			NodeDrawer startNode = null, endNode = null;
			for(NodeDrawer d : nodeDrawer) {
				if(d.getNode() == c.getIn()) startNode = d;
				if(d.getNode() == c.getOut()) endNode = d;
			}
			if(c.getWeight() > 0) context.setStroke(Color.GREEN);
			else context.setStroke(Color.RED);
			context.setLineWidth(Math.abs(c.getWeight())*10);
			if(c.isEnabled()) context.setLineDashes(0);
			else context.setLineDashes(10);
			context.strokeLine(startNode.x, startNode.y, endNode.x, endNode.y);
		}
		
		for(NodeDrawer drawer : nodeDrawer) drawer.draw(context);
	}
	
}
