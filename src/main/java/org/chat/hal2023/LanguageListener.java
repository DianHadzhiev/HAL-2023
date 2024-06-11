package org.chat.hal2023;

public interface LanguageListener {

    /**
     * Change the language that the class is currently using.
     * @param languageContext The object that checks which language is
     *                        currently used
     */
    void updateLanguage(LanguageContext languageContext);

}
