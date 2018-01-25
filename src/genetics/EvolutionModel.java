package genetics;

import network.Genome;

public class EvolutionModel {
	
	private Mutator mutator;
	
	public EvolutionModel() {
		mutator = new Mutator();
	}
	
	public void mutate(Genome g) {
		mutator.mutateRandom(g);
	}

}
