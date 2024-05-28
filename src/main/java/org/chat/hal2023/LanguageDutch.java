package org.chat.hal2023;

public class LanguageDutch implements LanguageStrategy {

    private final String responseFile = "Documentatie.json";

    @Override
    public String getResponseFile() {
        return this.responseFile;
    }
}
