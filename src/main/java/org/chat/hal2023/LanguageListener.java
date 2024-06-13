package org.chat.hal2023;

public interface LanguageListener {

    /**
     * Change the language that the class is currently using.
     * @param languageStrategy
     */
    void updateLanguage(LanguageStrategy languageStrategy);

}
