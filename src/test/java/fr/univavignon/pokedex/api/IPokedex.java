package fr.univavignon.pokedex.api;

import java.util.Comparator;
import java.util.List;


public interface IPokedex extends IPokemonMetadataProvider, IPokemonFactory {
	
	int size();
	
	int addPokemon(Pokemon pokemon);
	

	Pokemon getPokemon(int id) throws PokedexException;
	

	List<Pokemon> getPokemons();

	List<Pokemon> getPokemons(Comparator<Pokemon> order);
	
}
