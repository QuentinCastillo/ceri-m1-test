package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class IPokemonTrainerFactoryTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    protected static IPokemonTrainerFactory pokemonTrainerFactory;

    @Mock
    protected static IPokedexFactory pokedexFactory;

    @Before
    public void setUp() throws PokedexException {
        MockitoAnnotations.initMocks(this);
        IPokedex mockPokedex = mock(IPokedex.class);
        when(mockPokedex.size()).thenReturn(0);
        when(pokemonTrainerFactory.createTrainer("Sacha", Team.VALOR, pokedexFactory))
                .thenReturn(new PokemonTrainer("Sacha", Team.VALOR, mockPokedex));
    }

    @Test
    public void testCreateTrainer() {
        PokemonTrainer sacha = pokemonTrainerFactory.createTrainer("Sacha", Team.VALOR, pokedexFactory);
        assertNotNull(sacha);
        assertEquals("Sacha", sacha.getName());
        assertEquals(Team.VALOR, sacha.getTeam());
        assertNotNull(sacha.getPokedex());
        assertEquals(0, sacha.getPokedex().size());
    }
}
