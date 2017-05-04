package fr.univavignon.pokedex.impl;

import fr.univavignon.pokedex.api.IPokemonFactoryTest;
import org.junit.Before;


public class PokemonFactoryTest extends IPokemonFactoryTest {
    @Before
    public void setUp() {
        pokemonFactory = new PokemonFactory();
    }

}