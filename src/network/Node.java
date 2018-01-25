package network;

import java.util.ArrayList;

public class Node {
	
	public enum Type {
		Sensor,
		Hidden,
		Output
	}
	
	private ArrayList<Connection> incomingConnections = new ArrayList<Connection>();
	private ArrayList<Connection> outgoingConnections = new ArrayList<Connection>();
	private int id;
	private int depth; 
	private Type type;
	
	public Node(Type type) {
		this.type = type;
	}
	
	
	public Type getType() {
		return type;
	}
	
	public int getId() {
		return id;
	}
	
	/**
	 * In hidden nodes, the depth is equal to the max number of other hidden nodes an input passes before arriving on this node.<br>
	 * In input and output nodes, the depth is always equal to 0.
	 */
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void addIncomingConnection(Connection c) {
		incomingConnections.add(c);
	}
	public void addOutgoingConnection(Connection c) {
		outgoingConnections.add(c);
	}


}
