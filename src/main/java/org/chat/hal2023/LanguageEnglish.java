package org.chat.hal2023;

public class LanguageEnglish implements LanguageStrategy{

    private final String responseFile = null;

    /**
     * Getter for the {@link LanguageEnglish#responseFile responseFile} field.
     * @return the current responseFile
     */
    @Override
    public String getResponseFile() {
        return this.responseFile;
    }
}
