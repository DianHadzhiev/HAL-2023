package org.chat.hal2023;

public class LanguageEnglish implements LanguageStrategy{

    private final String responseFile = null;

    @Override
    public String getResponseFile() {
        return this.responseFile;
    }
}
