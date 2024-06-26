package org.chat.hal2023;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ChatBotResponse implements LanguageListener {

    /**
     * Array that contains all responses for user inputs.
     */
    private JsonArray documentation;

    /**
     * Object that contains the current used language.
     */
    private LanguageStrategy languageStrategy;

    private static ChatBotResponse instance;

    /**
     * Constructor. Makes sure that when this object is made the language is
     * ready to be used.
     */
    private ChatBotResponse(LanguageStrategy languageStrategy) {
        this.languageStrategy = languageStrategy;
        this.updateJSONStream(this.languageStrategy.getResponseFile());
    }

    public static ChatBotResponse getInstance(LanguageStrategy languageStrategy) {
        if (instance == null) {
            instance = new ChatBotResponse(languageStrategy);
        }
        return instance;
    }

    /**
     * Takes the message sent by the user and responds with an appropriate
     * answer.
     * @param message The user input
     * @return The response according to user input.
     */
    public String getResponse(String message) {
        String[] messageWords = message.toLowerCase().split("\\s+");

        for (JsonElement element : this.documentation) {
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
     * @param languageStrategy
     */
    @Override
    public void updateLanguage(LanguageStrategy languageStrategy) {
        this.languageStrategy = languageStrategy;
        this.updateJSONStream(this.languageStrategy.getResponseFile());
    }

    public void updateJSONStream(String fileName) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(fileName);
            JsonElement jsonElement = JsonParser.parseReader(
                    new InputStreamReader(inputStream));

            this.documentation = jsonElement.getAsJsonArray();

            inputStream.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public LanguageStrategy getLanguageStrategy() {
        return languageStrategy;
    }

}
