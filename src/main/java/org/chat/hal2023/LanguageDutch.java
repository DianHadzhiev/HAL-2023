package org.chat.hal2023;

public class LanguageDutch implements LanguageStrategy {

    private final String responseFile = "DocumentatieNL.json";

    /**
     * Getter for the {@link LanguageDutch#responseFile responseFile} field.
     * @return the current responseFile
     */
    @Override
    public String getResponseFile() {
        return this.responseFile;
    }
}
