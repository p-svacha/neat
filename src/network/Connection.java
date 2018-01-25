package network;

public class Connection {
	
	private int innovationNumber;
	private Node in;
	private Node out;
	private float weight;
	private boolean enabled = true;
	
	public Connection(Node in, Node out, float weight, boolean enabled) {
		this.in = in;
		this.out = out;
		this.weight = weight;
		this.enabled = enabled;
	}
	
	public int getInnovationNumber() {
		return innovationNumber;
	}
	public Node getIn() {
		return in;
	}
	public Node getOut() {
		return out;
	}
	public float getWeight() {
		return weight;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void disable() {
		enabled = false;
	}
	
	public void setInnovationNumber(int innovationNumber) {
		this.innovationNumber = innovationNumber;
	}

}
