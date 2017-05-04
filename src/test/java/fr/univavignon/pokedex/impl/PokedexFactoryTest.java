package fr.univavignon.pokedex.impl;

import fr.univavignon.pokedex.api.IPokedexFactoryTest;
import org.junit.Before;

import static org.junit.Assert.*;

/**
 * Created by klovelace on 5/3/17.
 * PROJECT: pokedex
 * PACKAGE: fr.univavignon.pokedex.impl
 */

public class PokedexFactoryTest extends IPokedexFactoryTest {
    @Before
    public void setUp() {
        pokedexFactory = new PokedexFactory();
    }

}