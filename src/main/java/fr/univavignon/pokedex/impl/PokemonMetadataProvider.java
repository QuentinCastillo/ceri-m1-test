package fr.univavignon.pokedex.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fr.univavignon.pokedex.api.IPokemonMetadataProvider;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.PokemonMetadata;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by klovelace on 4/5/17.
 * PROJECT: pokedex
 * PACKAGE: fr.univavignon.pokedex.api
 */

public class PokemonMetadataProvider implements IPokemonMetadataProvider {

    private JsonArray data = null;

    /**
     * {@inheritDoc}
     **/
    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        if (this.data == null)
            this.parseData();

        JsonObject pokemon;
        try {
            pokemon = data.get(index).getAsJsonObject();
        } catch(IndexOutOfBoundsException e) {
            throw new PokedexException("No Pokemon at this index!");
        }

        return new PokemonMetadata(
                index,
                pokemon.get("Name").getAsString(),
                pokemon.get("BaseAttack").getAsInt(),
                pokemon.get("BaseDefense").getAsInt(),
                pokemon.get("BaseStamina").getAsInt()
        );
    }

    private void parseData() {
        HttpURLConnection request = null;
        URL url = null;

        // Connect to the URL using java's native library
        try {
            String url1 = "https://raw.githubusercontent.com/PokemonGoF/PokemonGo-Bot/master/data/pokemon.json";
            url = new URL(url1);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            request = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            request.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convert to a JSON object to get data
        JsonParser jp = new JsonParser(); //from gson
        JsonElement root = null;
        try {
            root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        data = root.getAsJsonArray();
    }
}
