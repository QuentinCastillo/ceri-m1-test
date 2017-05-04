package fr.univavignon.pokedex.impl;

import fr.univavignon.pokedex.api.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



public class Pokedex implements IPokedex, Serializable {
    private List<Pokemon> list;
    private transient IPokemonMetadataProvider metadataProvider;
    private transient IPokemonFactory pokemonFactory;

    Pokedex() {
        try {
            this.read();
        } catch (IOException | ClassNotFoundException e) {
            this.list = new ArrayList<>();
        } finally {
            this.metadataProvider = null;
            this.pokemonFactory = null;
        }
    }

    Pokedex(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
        this();
        this.metadataProvider = metadataProvider;
        this.pokemonFactory = pokemonFactory;
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public int addPokemon(Pokemon pokemon) {
        this.list.add(pokemon);
        this.save();
        return this.list.size() - 1;
    }

    private void save() {
        ObjectOutputStream oos = null;

        try {
            oos = new ObjectOutputStream(new FileOutputStream("Pokedex.ser"));
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void read() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("Pokedex.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Pokedex tmp = (Pokedex) ois.readObject();
        this.list = new ArrayList<>(tmp.list);
        try {
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Pokemon getPokemon(int id) throws PokedexException {
        Pokemon pok = null;
        try {
            pok = this.list.get(id);
        } catch (IndexOutOfBoundsException e) {
            throw new PokedexException("MISSINGNO");
        }
        return pok;
    }

    @Override
    public List<Pokemon> getPokemons() {
        return Collections.unmodifiableList(this.list);
    }

    @Override
    public List<Pokemon> getPokemons(Comparator<Pokemon> order) {
        List<Pokemon> orderedList = new ArrayList<>(this.list);
        orderedList.sort(order);
        return Collections.unmodifiableList(orderedList);
    }

    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        return this.pokemonFactory.createPokemon(index, cp, hp, dust, candy);
    }

    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        return this.metadataProvider.getPokemonMetadata(index);
    }
}
