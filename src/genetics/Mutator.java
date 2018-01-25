package genetics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import network.Connection;
import network.Genome;
import network.Node;

public class Mutator {
	
	private Random r = new Random();
	
	public void mutateRandom(Genome genome) {
		if(Math.random() > 0.5) addConnection(genome);
		else addNode(genome);
	}
	
	private void addConnection(Genome genome) {
		
		List<Node> candidates = new ArrayList<Node>();
		candidates.addAll(genome.getInputNodes());
		candidates.addAll(genome.getHiddenNodes());
		List<Node> candidates2 = new ArrayList<Node>();
		candidates2.addAll(genome.getHiddenNodes());
		candidates2.addAll(genome.getOutputNodes());
		
		List<Node> toRemove = new ArrayList<Node>();
		for(Node n : candidates) {
			boolean addAgain = false;
			if(candidates2.contains(n)) {
				candidates2.remove(n);
				addAgain = true;
			}
			if(n.getConnectedNodes().containsAll(candidates2)) {
				toRemove.add(n);
			}
			if(addAgain) candidates2.add(n);
		}
		candidates.removeAll(toRemove);
		
		if(candidates.size() == 0) {
			System.out.println("Couldn't mutate 'add connection' because there is no space for new connections.");
		}
		else {
			Node toMutate = candidates.get(r.nextInt(candidates.size()));
			System.out.println("Mutation candidates are:");
			for(Node n : candidates) {
				System.out.println(n.getId());
			}
			System.out.println("Mutated node id: " + toMutate.getId());
			
			List<Node> toRemove2 = new ArrayList<Node>();
			for(Node n : candidates2) {
				if(toMutate.isConnectedTo(n) || toMutate == n) toRemove2.add(n);
			}
			candidates2.removeAll(toRemove2);
			System.out.println("Connection candidates are:");
			for(Node n : candidates2) {
				System.out.println(n.getId());
			}
			Node toConnect = candidates2.get(r.nextInt(candidates2.size()));
			
			System.out.println("new connected node id: " + toConnect.getId());
			
			// Establish connection
			Connection c = new Connection(toMutate, toConnect, (float) r.nextGaussian(), true);
			genome.addConnection(c);
		}
	}
	
	private void addNode(Genome genome) {
		ArrayList<Connection> candidates = new ArrayList<Connection>();
		candidates.addAll(genome.getConnections());
		
		if(candidates.size() == 0) {
			System.out.println("Couldn't mutate 'add node' because there are no connections.");
		}
		
		Connection toMutate = candidates.get(r.nextInt(candidates.size()));
		
		Node newNode = new Node(Node.Type.Hidden);
		genome.addNode(newNode);
		
		Connection new1 = new Connection(toMutate.getIn(), newNode, 1, true);
		Connection new2 = new Connection(newNode, toMutate.getOut(), toMutate.getWeight(), true);
		
		toMutate.disable();
		genome.addConnection(new1);
		genome.addConnection(new2);
	}

}
