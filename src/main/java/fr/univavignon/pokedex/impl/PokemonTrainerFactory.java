package fr.univavignon.pokedex.impl;

import fr.univavignon.pokedex.api.IPokedexFactory;
import fr.univavignon.pokedex.api.IPokemonTrainerFactory;
import fr.univavignon.pokedex.api.PokemonTrainer;
import fr.univavignon.pokedex.api.Team;

/**
 * Created by klovelace on 5/3/17.
 * PROJECT: pokedex
 * PACKAGE: fr.univavignon.pokedex.impl
 */

public class PokemonTrainerFactory implements IPokemonTrainerFactory {
    @Override
    public PokemonTrainer createTrainer(String name, Team team, IPokedexFactory pokedexFactory) {
        return new PokemonTrainer(name,
                team,
                pokedexFactory.createPokedex(new PokemonMetadataProvider(),
                        new PokemonFactory()));
    }
}
