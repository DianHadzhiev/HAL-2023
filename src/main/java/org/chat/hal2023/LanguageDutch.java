package org.chat.hal2023;

public class LanguageDutch implements LanguageStrategy {


    /**
     * Name of the file that contains all data for this Language.
     */
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
