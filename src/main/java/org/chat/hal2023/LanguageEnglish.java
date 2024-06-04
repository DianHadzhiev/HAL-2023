package org.chat.hal2023;

public class LanguageEnglish implements LanguageStrategy{

    /**
     * Name of the file that contains all data for this Language.
     */
    private final String responseFile = "DocumentatieENG.json";

    /**
     * Getter for the {@link LanguageEnglish#responseFile responseFile} field.
     * @return the current responseFile
     */
    @Override
    public String getResponseFile() {
        return this.responseFile;
    }
}
