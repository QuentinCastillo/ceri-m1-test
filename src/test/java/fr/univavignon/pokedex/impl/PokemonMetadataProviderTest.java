package fr.univavignon.pokedex.impl;

import fr.univavignon.pokedex.api.IPokemonMetadataProviderTest;
import org.junit.Before;

import static org.junit.Assert.*;

/**
 * Created by klovelace on 4/5/17.
 * PROJECT: pokedex
 * PACKAGE: fr.univavignon.pokedex.impl
 */

public class PokemonMetadataProviderTest extends IPokemonMetadataProviderTest {
    @Before
    public void setUp() {
        pokemonMetadataProvider = new PokemonMetadataProvider();
    }

}