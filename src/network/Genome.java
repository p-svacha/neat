package network;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Genome {
	
	private int currentNodeId;
	private static int currentInnovationNumber;
	
	private List<Node> nodes = new ArrayList<Node>();
	private List<Connection> connections = new ArrayList<Connection>();
	
	public Genome() {
	}
	
	public List<Node> getInputNodes() {
		return nodes.stream().filter(n -> n.getType() == Node.Type.Sensor).collect(Collectors.toList());
	}
	public List<Node> getHiddenNodes() {
		return nodes.stream().filter(n -> n.getType() == Node.Type.Hidden).collect(Collectors.toList());
	}
	public List<Node> getOutputNodes() {
		return nodes.stream().filter(n -> n.getType() == Node.Type.Output).collect(Collectors.toList());
	}
	
	public List<Connection> getConnections() {
		return connections;
	}
	
	public void addNode(Node n) {
		n.setId(currentNodeId++);
		nodes.add(n);
		findCandidates();
	}
	
	public void addConnection(Connection c) {
		c.setInnovationNumber(currentInnovationNumber++);
		c.getIn().addOutgoingConnection(c);
		c.getOut().addIncomingConnection(c);
		connections.add(c);
		findCandidates();
	}
	
	public void findCandidates() {
		ArrayList<ArrayList<Node>> nodeList = new ArrayList<ArrayList<Node>>();
		nodeList.add(new ArrayList<Node>());
		
		nodeList.get(0).addAll(getInputNodes());
		depthStep(0, nodeList);
	}
	
	private void depthStep(int depth, ArrayList<ArrayList<Node>> nodeList) {
		nodeList.add(new ArrayList<Node>());
		boolean addOne = false;
		for(Node n : nodeList.get(depth)) {
			for(Connection c : connections) {
				if(c.getIn() == n) {
					nodeList.get(depth+1).add(c.getOut());
					c.getOut().setDepth(depth);
					addOne = true;
				}
			}
		}
		if(addOne) {
			depthStep(depth+1, nodeList);
		}
	}

}
