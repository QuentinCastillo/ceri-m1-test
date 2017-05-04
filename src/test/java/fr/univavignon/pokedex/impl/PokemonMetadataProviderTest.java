package fr.univavignon.pokedex.impl;

import fr.univavignon.pokedex.api.IPokemonMetadataProviderTest;
import org.junit.Before;

import static org.junit.Assert.*;


public class PokemonMetadataProviderTest extends IPokemonMetadataProviderTest {
    @Before
    public void setUp() {
        pokemonMetadataProvider = new PokemonMetadataProvider();
    }

}