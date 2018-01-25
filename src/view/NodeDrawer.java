package view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import network.Node;

public class NodeDrawer {
	
	private Node node;
	
	public Color color;
	public int x;
	public int y;
	public int size;
	
	public NodeDrawer(Node node, int x, int y, int size, Color color) {
		this.node = node;
		this.x = x;
		this.y = y;
		this.size = size;
		this.color = color;
	}
	
	public void draw(GraphicsContext context) {
		context.setFill(color);
		context.fillOval(x - size/2, y - size/2, size, size);
	}
	
	public Node getNode() {
		return node;
	}

}
