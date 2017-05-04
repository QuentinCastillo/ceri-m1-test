package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

public class IPokemonMetadataProviderTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    protected static IPokemonMetadataProvider pokemonMetadataProvider;

    @Before
    public void setUp() throws PokedexException {
        MockitoAnnotations.initMocks(this);
        when(pokemonMetadataProvider.getPokemonMetadata(0))
                .thenReturn(new PokemonMetadata(
                        0,
                        "Bulbasaur",
                        118,
                        118,
                        90));
        when(pokemonMetadataProvider.getPokemonMetadata(2000))
                .thenThrow(new PokedexException("Le professeur Castillo déclare que ce Pokemon est inconnu !");
    }

    @Test
    public void testGetPokemonMetadata() {
        PokemonMetadata metadata = null;
        try {
            metadata = pokemonMetadataProvider.getPokemonMetadata(0);
        } catch (PokedexException e) {
            e.printStackTrace();
        }
        assertNotNull(metadata);
        assertEquals("Bulbasaur", metadata.getName());
        assertEquals(118, metadata.getAttack());
        assertEquals(118, metadata.getDefense());
        assertEquals(90, metadata.getStamina());
    }

    @Test(expected = PokedexException.class)
    public void testGetPokemonMetadataError() throws PokedexException {
        pokemonMetadataProvider.getPokemonMetadata(2000);
    }
}
