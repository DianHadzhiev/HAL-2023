package org.chat.hal2023;

public interface LanguageStrategy {

    /**
     * Method to return the name of the file that contains all information
     * about the language.
     * @return file name
     */
    String getResponseFile();
}
