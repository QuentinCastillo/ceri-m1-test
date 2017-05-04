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
import java.net.URL;


public class PokemonMetadataProvider implements IPokemonMetadataProvider {
    private JsonArray data = null;

    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        if (this.data == null)this.parseData();

        JsonObject pokemon;
		
        try 
		{
            pokemon = data.get(index).getAsJsonObject();
        } catch(IndexOutOfBoundsException e) {
            throw new PokedexException("MISSINGNO");
        }
        return new PokemonMetadata(index,pokemon.get("Name").getAsString(),pokemon.get("BaseAttack").getAsInt(),pokemon.get("BaseDefense").getAsInt(),pokemon.get("BaseStamina").getAsInt());
    }

    private void parseData() {
        HttpURLConnection request = null;
        URL url = null;

        try 
		{
            String res = "https://raw.githubusercontent.com/PokemonGoF/PokemonGo-Bot/master/data/pokemon.json";
            url = new URL(res);
			request = (HttpURLConnection) url.openConnection();
			request.connect();
			JsonParser jp = new JsonParser(); 
			JsonElement root = null;
			root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
			data = root.getAsJsonArray();
        } catch (IOException e) 
		{
            e.printStackTrace();
        }
    }
}
