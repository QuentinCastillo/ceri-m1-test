package fr.univavignon.pokedex.impl;

import fr.univavignon.pokedex.api.IPokedexFactoryTest;
import org.junit.Before;

import static org.junit.Assert.*;


public class PokedexFactoryTest extends IPokedexFactoryTest {
    @Before
    public void setUp() {
        pokedexFactory = new PokedexFactory();
    }

}