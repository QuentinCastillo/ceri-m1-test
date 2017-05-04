package fr.univavignon.pokedex.api;


public class PokemonTrainer {

	private final String name;

	private final Team team;
	
	private final IPokedex pokedex;
	
	public PokemonTrainer(final String name, final Team team, final IPokedex pokedex) {
		this.name = name;
		this.team = team;
		this.pokedex = pokedex;
	}
	
	public String getName() {
		return name;
	}

	public Team getTeam() {
		return team;
	}
	
	public IPokedex getPokedex() {
		return pokedex;
	}
	
}
