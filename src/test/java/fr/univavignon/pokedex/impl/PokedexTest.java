package fr.univavignon.pokedex.impl;

import fr.univavignon.pokedex.api.IPokedexTest;
import fr.univavignon.pokedex.api.PokedexException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by klovelace on 4/30/17.
 * PROJECT: pokedex
 * PACKAGE: fr.univavignon.pokedex.impl
 */

public class PokedexTest extends IPokedexTest {
    @Before
    public void setUp() {
        pokedex = new Pokedex();
    }

    @Test
    public void testReadAndWriteFromFile() throws PokedexException {
        Pokedex tmpPokedex = new Pokedex();
        assertEquals(0, tmpPokedex.size());
        tmpPokedex.addPokemon(bulbizarre);
        Pokedex otherPokedex = new Pokedex();
        assertEquals(1, otherPokedex.size());
        assertEquals(tmpPokedex.getPokemon(0).getName(), otherPokedex.getPokemon(0).getName());
    }

    @After
    public void cleanUp() {
        File file = new File("Pokedex.ser");
        file.delete();
    }
}