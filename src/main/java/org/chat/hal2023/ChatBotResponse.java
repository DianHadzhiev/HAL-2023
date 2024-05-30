package org.chat.hal2023;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.String;

public class ChatBotResponse implements LanguageListener {

    private JsonArray documentation;

    private LanguageManager languageManager;

    private LanguageStrategy languageStrategy;

    public ChatBotResponse(LanguageManager languageManager) {
        this.languageManager = languageManager;
        this.languageManager.subscribe(this);
        this.updateJSONStream(this.languageStrategy.getResponseFile());
    }

    /**
     * Takes the message sent by the user and responds with an appropriate
     * answer.
     * @param message The user input
     * @return The response according to user input.
     */
    public String getResponse(String message) {
        // Split the message into words (optional: convert to lowercase for case-insensitive matching)
        String[] messageWords = message.toLowerCase().split("\\s+");

        for (JsonElement element : documentation) {
            JsonObject doc = element.getAsJsonObject();
            String keyword = doc.get("keyword").getAsString().toLowerCase();

            // Check if any word in the message matches the keyword
            for (String word : messageWords) {
                if (word.equals(keyword)) {
                    return doc.get("response").getAsString();
                }
            }
        }
        return "No documentation found for: " + message;
    }

    /**
     * Changes this objects own language strategy to the one of the
     * LanguageContext. Afterward, requests the JSONStream to be updated
     * according to the new strategy.
     * @param languageContext
     */
    @Override
    public void updateLanguage(LanguageContext languageContext) {
        this.languageStrategy = languageContext.getStrategy();
        this.updateJSONStream(languageStrategy.getResponseFile());
    }

    private void updateJSONStream(String fileName) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(fileName);
            JsonElement jsonElement = JsonParser.parseReader(
                    new InputStreamReader(inputStream));

            documentation = jsonElement.getAsJsonArray();

            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
