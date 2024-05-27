package org.chat.hal2023;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ChatBotResponse {

    private JsonArray documentation;

    public ChatBotResponse() {
        try {
            InputStream inputStream = getClass().getResourceAsStream("Documentatie.json");
            JsonElement jsonElement = JsonParser.parseReader(new InputStreamReader(inputStream));

            documentation = jsonElement.getAsJsonArray();

            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getResponse(String message) {
        for (JsonElement element : documentation) {
            JsonObject doc = element.getAsJsonObject();
            if (doc.get("keyword").getAsString().equalsIgnoreCase(message)) {
                return doc.get("response").getAsString();
            }
        }
        return "No documentation found for keyword: " + message;
    }
}
