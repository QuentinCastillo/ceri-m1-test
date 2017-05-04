package fr.univavignon.pokedex.impl;

import fr.univavignon.pokedex.api.IPokedex;
import fr.univavignon.pokedex.api.IPokedexFactory;
import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.IPokemonMetadataProvider;

/**
 * Created by klovelace on 4/30/17.
 * PROJECT: pokedex
 * PACKAGE: fr.univavignon.pokedex.api
 */

public class PokedexFactory implements IPokedexFactory {
    @Override
    public IPokedex createPokedex(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
        return new Pokedex(metadataProvider, pokemonFactory);
    }
}
