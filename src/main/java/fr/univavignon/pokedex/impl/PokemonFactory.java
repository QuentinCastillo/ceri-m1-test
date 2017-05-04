package fr.univavignon.pokedex.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.Pokemon;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;


public class PokemonFactory implements IPokemonFactory {
    private final String base_url = "https://pokemon.gameinfo.io/en/tools/iv-calculator";
    private final String USER_AGENT = "Mozilla/5.0";
    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        JsonObject jsonObject = null;
        try
		{
            jsonObject = recupStats(index + 1, cp, dust);
        } catch (Exception e) {
            e.printStackTrace();
        }
  
        JsonArray stats = jsonObject.get("stats").getAsJsonArray();
        return new Pokemon( index,
                jsonObject.get("pokemon").getAsJsonArray().get(0).toString().replace("\"", ""),
                stats.get(1).getAsInt(),
                stats.get(2).getAsInt(),
                stats.get(0).getAsInt(),
                cp,
                hp,
                dust,
                candy,
                jsonObject.get("levels").getAsJsonObject().get("50").getAsDouble() * 100.0
                );
    }

    private JsonObject recupStats(int index, int cp, int dust) throws Exception {
        // TODO
    }
}
