package fr.univavignon.pokedex.impl;

import fr.univavignon.pokedex.api.IPokemonTrainerFactoryTest;
import org.junit.Before;

import static org.junit.Assert.*;


public class PokemonTrainerFactoryTest extends IPokemonTrainerFactoryTest {
    @Before
    public void setUp() {
        pokedexFactory = new PokedexFactory();
        pokemonTrainerFactory = new PokemonTrainerFactory();
    }

}