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
        String urlParameters = "p=" + index +
                "&dust%5B%5D=" + dust +
                "&ct=true" +
                "&ev=false" +
                "&cp%5B%5D=" + cp +
                "&v=3";
        URL obj = new URL(base_url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + base_url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        JsonParser jp = new JsonParser();
        JsonElement root = null;

        try {
            root = jp.parse(new InputStreamReader(con.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //print result
        return root.getAsJsonObject();

    }
}
